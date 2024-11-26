package com.nowshowing;

import java.util.List;

import com.nowshowing.UI.UIElements.ViewController;
import com.nowshowing.user.*;
import com.nowshowing.wrappers.Movie;

public class ServerController {
    
     MediaHandler mediaHandler;
     ViewController viewController;
     
     ServerController (ViewController vh){
        viewController = vh;
        mediaHandler = new MediaHandler();
     }
     

    public List<Movie> searchRequest(String input){
        return mediaHandler.findMultipleByTitle(input);
    }

    public Movie recommendationRequest(List<String> input){
        Recommendation rec = new Recommendation(input);
    }
    
    public void removeFromWL(int id, int userId){

    }

    public void addToWL(int id, int userId){

    }

    public List<String> getWatchlist(int userId){

    }

    public User loginRequest(String user, String password){

    }

    public Movie getDetails(int id){
        return mediaHandler.findById(id);
    }
    

}
