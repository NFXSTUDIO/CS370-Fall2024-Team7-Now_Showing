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
        return new Movie();
    }
    
    public void removeFromWL(int id, int userId){

    }

    public void addToWL(int id, int userId){

    }

    public List<String> getWatchlist(int userId){
        return new ArrayList<String>();
    }

    public String loginRequest(String user, String password){
        return "result here";
    }

    public Movie getDetails(int id){
        return mediaHandler.findById(id);
    }
    

}
