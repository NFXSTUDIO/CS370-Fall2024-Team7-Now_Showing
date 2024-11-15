import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void user(){
        int choice;
        Scanner myObj = new Scanner(System.in);
        do {
            System.out.println("\nUser:");
            System.out.println("1. Add User");
            System.out.println("2. Read User information");
            System.out.println("3. Update User information");
            System.out.println("4. Delete User information");
            System.out.println("5. Exit");
            System.out.print("> ");
            choice = myObj.nextInt();

            switch (choice) {
                case 1:
                    User.create_user();
                    user();
                    break;
                case 2:
                    User.read_user();
                    user();
                    break;
                case 3:
                    User.update_user();
                    user();
                    break;
                case 4:
                    User.delete_user();
                    user();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice > 5);
    }

    public void main(String[] args) {
        user();
        ObjectId media_id = new ObjectId();
        Watchlist.add_to_watchlist("atouati",media_id);
        ArrayList<String> watchlist = Watchlist.get_watchlist("atouati");
        System.out.println(watchlist);
    }
}