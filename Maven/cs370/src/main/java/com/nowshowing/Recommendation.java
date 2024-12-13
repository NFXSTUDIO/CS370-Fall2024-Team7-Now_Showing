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

    public Recommendation(List<String> input, MediaHandler mediaHandler) {
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
        List<Movie> results = new ArrayList<Movie>();
        List<Double> resultScores = new ArrayList<Double>();

        List<Integer> genreIdList = new ArrayList<Integer>();
        List<String> directorList = new ArrayList<String>();
        List<String> castList = new ArrayList<String>();
        List<String> decadeList = new ArrayList<String>();
        String decade;

        try{
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

            for(int id : genreIdList){
                List<Movie> genreMatches = mh.findMultipleByGenre(id);
                for(Movie match : genreMatches){
                    if(!results.contains(match)){
                        results.add(match);
                        resultScores.add(genreMultiplier);
                    }
                }
            }

            for(int i = 0; i < results.size(); i++){
                Movie current = results(i);
                if(directorList.contains(current.getDirector())){
                    resultScores.get(i) = resultScores.get(i) + directorMultiplier;
                }
                for(String currCastMember : current.getCast()){
                    if(castList.contains(currCastMember)){
                        resultScores.get(i) = resultScores.get(i) + castMultiplier;
                    }
                }
                decade = current.getRelease_date().substring(0, 3);
                if(decadeList.contains(decade)){
                    resultScores.get(i) = resultScores.get(i) + decadeMultiplier;
                }
            }

            int max = 0;
            double highScore = 0; 

            for(int i = 0; i < resultScores.size(); i++){
                if(resultScores.get(i) > highScore){
                    max = i;
                }
            }

            return results.get(max);
        } catch (Exception e){
            return mh.findById(238);
        }
 
    }
    
}
