package com.nowshowing.databasebuild;
import com.nowshowing.wrappers.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.lang.reflect.Type;

public class MovieImporter {
    
    MovieImporter(){}

    public void run(){
        int c = 0;
        Type movieType;
        List<Movie> list;
        String str;
        FileWriter writer;
        HttpResponse<String> response;
        HttpRequest request;
        Gson gson;

        try{           
            writer = new FileWriter("media_data.json");
            gson = new Gson();
            
            // For loop determines which pages are requested (currently 1-15 of top rated)
            for(int i = 1; i <= 15; i++){
                // Build request and execute
                request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=" + i))
                .header("accept", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzYmE2MzdhOTdjYmMwMjc3MmM2NjE3NDM4OGJmMDJjZSIsIm5iZiI6MTczMjEzNzMzNi4yNzk3Nzk3LCJzdWIiOiI2NzI5NWM1ZDE0ZDRhMzk5NzIwMzNkOWIiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.LNSRIqNJYUGiMI1f_HMrLnqLbV69qJudcSLuVYwl5Wk")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                str = response.body();
                // Just get list of movies separate from page data
                str = str.substring(str.indexOf("\"results\":[") + 10, str.indexOf(",\"total_pages\""));

                // Convert JSON to list of movies
                movieType = new TypeToken<List<Movie>>() {}.getType();
                list = gson.fromJson(str, movieType);

                for(Movie m : list){
                    // Request credits data for each movie
                    request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.themoviedb.org/3/movie/" + m.getId() + "/credits?language=en-US"))
                    .header("accept", "application/json")
                    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzYmE2MzdhOTdjYmMwMjc3MmM2NjE3NDM4OGJmMDJjZSIsIm5iZiI6MTczMjEzNzMzNi4yNzk3Nzk3LCJzdWIiOiI2NzI5NWM1ZDE0ZDRhMzk5NzIwMzNkOWIiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.LNSRIqNJYUGiMI1f_HMrLnqLbV69qJudcSLuVYwl5Wk")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
                    response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                    
                    // Convert credits to MovieCrew object
                    str = response.body();                  
                    MovieCrew crew = gson.fromJson(str, MovieCrew.class);

                    // Load separate lists
                    List<CrewMember> cast = crew.getCast();
                    List<CrewMember> myCrew = crew.getCrew();

                    // Instantiate cast list and add top 5 actors to movie
                    Iterator<CrewMember> it = cast.iterator();
                    List<String> currList = new ArrayList<String>();
                    c = 0;
                    while(it.hasNext() && c < 5){
                        currList.add(it.next().getName());
                        c++;
                    }
                    m.setCast(currList);

                    // Find director in crew list and add to movie 
                    for(CrewMember curr : myCrew){
                        if(curr.getJob().equals("Director")){
                            m.setDirector(curr.getName());
                        }
                    }
                    writer.write(gson.toJson(m) + ","); // Add movie to file back as JSON object
                }
            }
            writer.close();

        } 
        catch(Exception e){
            System.out.println("Request failed.");
            e.printStackTrace();
        }

        
    }

    public static void main(String[] args) {
        MovieImporter movieImporter = new MovieImporter();
        movieImporter.run();
    }
}
