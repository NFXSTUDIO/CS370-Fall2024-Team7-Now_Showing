package com.nowshowing;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.nowshowing.wrappers.Movie;

public class MediaHandler {
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection; 
    MongoCursor<Document> cursor;
    Gson gson;

    public MediaHandler(){
        String url = "mongodb+srv://admin:93O3ZrWNpHer4nGT@nowshowing.55wf9.mongodb.net/";
        
        mongoClient = MongoClients.create(url);                   
        database = mongoClient.getDatabase("nowshowing");                    
        collection = database.getCollection("media");
        gson = new Gson();
    }

    public List<Movie> findMultipleByTitle(String input){
        List<Movie> results = new ArrayList<Movie>();
        collection.createIndex(Indexes.text("title"));
        Bson filter = Filters.text("\"" + input + "\"");
        Bson metaTextScoreSort = Sorts.metaTextScore("score");
        Bson metaTextScoreProj = Projections.metaTextScore("score");
        cursor = collection.find(filter).projection(metaTextScoreProj).sort(metaTextScoreSort).iterator();

        try{
            while (cursor.hasNext()){
                results.add(gson.fromJson(cursor.next().toJson(), Movie.class));
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.println(results);

        collection.dropIndexes();
        return results;
    }

    public Movie findById(int id){
        Document result = collection.find(Filters.eq("id", id)).first();
        return gson.fromJson(result.toJson(), Movie.class);
    }

    public Movie findByTitleMatch(String title){
        Document result = collection.find(Filters.eq("title", title)).first();
        return gson.fromJson(result.toJson(), Movie.class);
    }

    public List<Movie> findMultipleByGenre(int input){

        try{
            List<Movie> results = new ArrayList<Movie>();
            cursor = collection.find({genre_ids: input}).iterator();


            while (cursor.hasNext()){
                results.add(gson.fromJson(cursor.next().toJson(), Movie.class));
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        
        return results;
    }

}
