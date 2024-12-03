package com.nowshowing;

import java.util.ArrayList;
import java.util.List;

import com.nowshowing.wrappers.Movie;

public class Recommendation {
    List<Movie> userMovies;
    MediaHandler mh;
    double genreMultiplier;
    double directorMultiplier;
    double castMultiplier;
    double decadeMultiplier;

    public Recommendation(List<Integer> input, MediaHandler mediaHandler) {
        mh = mediaHandler;
        userMovies = new ArrayList<Movie>();

        genreMultiplier = 1;
        directorMultiplier = 2;
        castMultiplier = 0.5;
        decadeMultiplier = 0.5;
        
        for(String current : input){
            userMovies.add(mh.findByTitleMatch(current));
        }

    }

    public Movie getRecommendation(){
        List<Integer> resultIds = new ArrayList<Integer>();
        List<Integer> resultScores = new ArrayList<Integer>();

        List<Integer> genreIdList = new ArrayList<Integer>();
        List<String> directorList = new ArrayList<String>();
        List<String> castList = new ArrayList<String>();
        List<String> decadeList = new ArrayList<String>();
        String decade;

        for (Movie m : userMovies){
            for(int currId : m.getGenre_ids()){
                if(!genreIdList.contains(currId)){
                    genreIdList.add(currId);
                }
            }

            if(!directorList.contains(m.getDirector())){
                directorList.add(m.getDirector());
            }

            for(String currCast : m.getCast()){
                if(!castList.contains(currCast)){
                    castList.add(currCast);
                }
            }

            decade = m.getRelease_date().substring(0, 3);
            if(!decadeList.contains(decade)){
                decadeList.add(decade);
            }
            
        }

        

        return mh.findById(238);
    }
    
}
