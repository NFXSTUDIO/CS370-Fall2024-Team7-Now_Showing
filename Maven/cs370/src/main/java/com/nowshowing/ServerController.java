package com.nowshowing;

import java.util.ArrayList;
import java.util.List;

import com.nowshowing.UI.UIElements.ViewController;
import com.nowshowing.user.*;
import com.nowshowing.wrappers.Movie;



public class ServerController {
    
     MediaHandler mediaHandler;
     ViewController viewController;
     
     public ServerController (ViewController vh){
        viewController = vh;
        mediaHandler = new MediaHandler();
     }
     

    public List<Movie> searchRequest(String input){
        return mediaHandler.findMultipleByTitle(input);
    }

    public Movie recommendationRequest(List<String> input){
        Recommendation rec = new Recommendation(input, mediaHandler);
        
        return rec.getRecommendation();
    }
    
    public void removeFromWL(String username, String mediaID){
        UserHandler.add_data_to_database(STR."w,u,\{username},\{mediaID}");
    }

    public void addToWL(String mediaID){
        UserHandler.add_data_to_database(STR."w,a,username,\{mediaID}");
    }
   
    public Movie getDetails(int id){
        return mediaHandler.findById(id);
    }
    
    public ArrayList<String> getWatchlist(String username){
        ArrayList<String> export = UserHandler.add_data_to_database(STR."w,d,\{username}");
        return export;
    }

    public ArrayList<String> loginRequest(String user, String password){
        ArrayList<String> export = LogIn.log_in(user,password);
        return export;
    }

    public ArrayList<String> signupRequest(String user, String password){
         String firstName = "test";
         String lastName = "test";
         ArrayList<String> export = LogIn.sign_up(firstName,lastName,user,password);
         return export;
    }
}
