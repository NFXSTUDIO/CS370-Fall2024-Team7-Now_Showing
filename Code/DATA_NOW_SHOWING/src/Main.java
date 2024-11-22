import java.util.ArrayList;

public class Main {
    public void main(String[] args) {
        ArrayList<String> data = UserHandler.add_data_to_database("u,a,first_name,last_name,username,password");
        //ArrayList<String> exit = UserHandler.add_data_to_database(data);
        System.out.println(data);
    }
}