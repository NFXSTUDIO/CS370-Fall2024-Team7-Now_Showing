package com.nowshowing;

import java.util.ArrayList;
import java.util.List;

import com.nowshowing.wrappers.Movie;

public class Recommendation {
    List<Movie> userMovies;
    MediaHandler mh;

    public Recommendation(List<String> input, MediaHandler mediaHandler) {
        mh = mediaHandler;
        userMovies = new ArrayList<Movie>();
        
        for(String current : input){
            userMovies.add(mh.findByTitleMatch(current));
        }
    }

    public Movie getRecommendation(){
        return mh.findById(238);
    }
    
}
