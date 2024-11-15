import java.util.Objects;
import java.util.Scanner;

class User extends AccessUserData{
    public static void create_user(){
        Scanner myObj = new Scanner(System.in);
        boolean exist = false;
        String password1 = "", password2 = "",username = "";

        // Verify if username already exist
        do {
            System.out.println("Enter username: ");
            username = myObj.nextLine();
            exist = AccessUserData.user_verification(username);
        }while (exist);

        do{
            System.out.println("Enter password: ");
            password1 = myObj.nextLine();
            System.out.println("Enter password again: ");
            password2 = myObj.nextLine();
        }while (!Objects.equals(password1, password2));

        System.out.println("Enter first name: ");
        String firstName = myObj.nextLine();
        System.out.println("Enter last name: ");
        String lastName = myObj.nextLine();

        AddUser(firstName,lastName,username,password1);
    }

    public static void log_in(){
        Scanner myObj = new Scanner(System.in);
        boolean exist,correct;

        String password = "",username = "";
        do {
            System.out.println("Enter username: ");
            username = myObj.nextLine();
            exist = AccessUserData.user_verification(username);
        }while (!exist);

        do {
            System.out.println("Enter password: ");
            password = myObj.nextLine();
            correct = AccessUserData.password_verification(password,username);
        }while (!correct);

        System.out.println(STR."Welcome \{username}");
    }

    public static void delete_user(){
        Scanner myObj = new Scanner(System.in);
        boolean exist,correct;
        String password = "",username = "";

        do {
            System.out.println("Enter username: ");
            username = myObj.nextLine();
            exist = AccessUserData.user_verification(username);
        }while (!exist);

        do {
            System.out.println("Enter password: ");
            password = myObj.nextLine();
            correct = AccessUserData.password_verification(password,username);
        }while (!correct);

        deleteUser(username);
    }

    public static void read_user(){
        Scanner myObj = new Scanner(System.in);
        boolean exist,correct;
        String password = "",username = "";

        do {
            System.out.println("Enter username: ");
            username = myObj.nextLine();
            exist = AccessUserData.user_verification(username);
        }while (!exist);

        do {
            System.out.println("Enter password: ");
            password = myObj.nextLine();
            correct = AccessUserData.password_verification(password,username);
        }while (!correct);

        String[] data = getUserData(username);
        System.out.println(STR."""
-------------------------------
First name: \{data[0]}
Last name: \{data[1]}
Username: \{data[2]}
Password: \{data[3]}
-------------------------------
""");
    }

    public static void update_user(){
        Scanner myObj = new Scanner(System.in);
        boolean exist,correct;
        String username = "",password = "";

        do {
            System.out.println("Enter username: ");
            username = myObj.nextLine();
            exist = AccessUserData.user_verification(username);
        }while (!exist);

        do {
            System.out.println("Enter password: ");
            password = myObj.nextLine();
            correct = AccessUserData.password_verification(password,username);
        }while (!correct);

        System.out.println("Press enter if you don't want to modify the parameter.");
        System.out.println("Enter new username: ");
        String new_username = myObj.nextLine();
        System.out.println("Enter new password: ");
        String new_password = myObj.nextLine();
        System.out.println("Enter new first name: ");
        String new_fname = myObj.nextLine();
        System.out.println("Enter new last name: ");
        String new_lname = myObj.nextLine();

        UpdateUser(username,new_username,new_password,new_fname,new_lname);
    }
}
