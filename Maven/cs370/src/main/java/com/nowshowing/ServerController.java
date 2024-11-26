package com.nowshowing;

import java.util.List;
import com.nowshowing.user.*;

public class ServerController {
    /*
     MediaHandler mediaHandler;
     ViewController viewController;
     
     ServerController (ViewController vh){
        viewController = vh;
        mediaHandler = new MediaHandler();
     }
     */

    public List<String> searchRequest(String input){

    }

    public List<String> recommendationRequest(List<String> input){

    }
    
    public void removeFromWL(String message){
        UserHandler.add_data_to_database(message);
    }

    public void addToWL(String message){
        UserHandler.add_data_to_database(message);
    }

    public ArrayList<String> getWatchlist(String message){
        ArrayList<String> export = UserHandler.add_data_to_database(message);
        return export;
    }

    public ArrayList<String> loginRequest(String user, String password){
        ArrayList<String> export = LogIn.log_in(user,password);
        return export;
    }

    public ArrayList<String> signupRequest(String firstName,String lastName,String user, String password){
        ArrayList<String> export = LogIn.sign_up(firstName,lastName,user,password);
        return export;
    }

}
