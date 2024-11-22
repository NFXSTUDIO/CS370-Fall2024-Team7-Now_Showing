import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class UserHandler implements User,Watchlist {

    public static ArrayList<String> add_data_to_database(ArrayList<String> message) {
        ArrayList<String> export_data = new ArrayList<String>();
        Boolean error;

        if (Objects.equals(message.get(0), "u")) {
            switch (message.get(1)){
                case "a":
                    error = User.create_user(message.get(2),message.get(3),message.get(4),message.get(5));
                    if (error) {
                        export_data.add("error");
                    }
                    else{
                        export_data.add("good");
                    }
                    break;
                case "r":
                    error = User.delete_user(message.get(2));
                    if (error) {
                        export_data.add("error");
                    }
                    else{
                        export_data.add("good");
                    }
                    break;
                case "u":
                    error = User.update_user(message.get(2),message.get(3),message.get(4),message.get(5),message.get(6));
                    if (error) {
                        export_data.add("error");
                    }
                    else{
                        export_data.add("good");
                    }
                    break;
                case "d":
                    ArrayList<String> exported_data = User.read_user(message.get(2));
                    if (Objects.equals(exported_data.getFirst(), "error")) {
                        export_data.add("error");
                    }
                    else{
                        export_data.addAll(exported_data);
                    }
                    break;
                case "uv":
                    Boolean exist = User.username_verification(message.get(2));
                    if (exist) {
                        export_data.add("good");
                        export_data.add("exist");
                    }
                    else{
                        export_data.add("good");
                        export_data.add("not_exist");
                    }
                    break;
                case "pv":
                    Boolean correct = User.password_verification(message.get(2),message.get(3));
                    if (correct) {
                        export_data.add("good");
                        export_data.add("correct");
                    }
                    else{
                        export_data.add("good");
                        export_data.add("incorrect");
                    }
                default:
                    System.out.println("Command invalid, try again");
                    export_data.add("error");
            }
        }

        else if (Objects.equals(message.get(0), "w")) {
            switch (message.get(1)){
                case "a":
                    ObjectId ID = new ObjectId(message.get(3));
                    error = Watchlist.add_to_watchlist(message.get(2), ID);
                    if (error) {
                        export_data.add("error");
                    }
                    else{
                        export_data.add("good");
                    }
                    break;
                case "r":
                    error = Watchlist.remove_watchlist(message.get(2));
                    if (error) {
                        export_data.add("error");
                    }
                    else{
                        export_data.add("good");
                    }
                    break;
                case "u":
                    ObjectId ID2 = new ObjectId(message.get(3));
                    error = Watchlist.remove_from_watchlist(message.get(2),ID2);
                    if (error) {
                        export_data.add("error");
                    }
                    else{
                        export_data.add("good");
                    }
                    break;
                case "d":
                    ArrayList<String> exported_data = Watchlist.get_watchlist(message.get(2));
                    if (Objects.equals(exported_data.getFirst(), "error")) {
                        export_data.add("error");
                    }
                    else{
                        export_data.addAll(exported_data);
                    }
                default:
                    System.out.println("Command invalid, try again");
                    export_data.add("error");
            }
        }

        else {
            export_data.add("error");
        }
        return export_data;
    }

    public static ArrayList<String> add_data_to_database(String message) {
        String[] decoder = message.split(",");
        ArrayList<String> command = new ArrayList<>();
        Collections.addAll(command, decoder);
        return add_data_to_database(command);
    }
}
