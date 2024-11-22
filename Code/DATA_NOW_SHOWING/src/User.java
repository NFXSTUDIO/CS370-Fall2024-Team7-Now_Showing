import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

interface User extends AccessUserData{
    public static Boolean create_user(String firstName,String lastName,String username, String password){
        return AccessUserData.add_user_to_database(firstName,lastName,username,password);
    }

    public static Boolean delete_user(String username){
        return AccessUserData.remove_user_from_database(username);
    }

    public static ArrayList<String> read_user(String username){
        return AccessUserData.get_user_info(username);
    }

    public static Boolean update_user(String username,String new_username,String new_password,String new_fname,String new_lname){
        return AccessUserData.update_user_info(username,new_username,new_password,new_fname,new_lname);
    }

    public static Boolean password_verification(String username, String password){
        return AccessUserData.password_verification(username,password);
    }

    public static Boolean username_verification(String username){
        return AccessUserData.user_verification(username);
    }
}
