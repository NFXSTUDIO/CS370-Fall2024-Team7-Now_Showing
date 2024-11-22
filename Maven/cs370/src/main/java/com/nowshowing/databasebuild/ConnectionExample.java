package com.nowshowing.databasebuild;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ConnectionExample {
    public static void main(String[] args) {
        String url = "mongodb+srv://admin:93O3ZrWNpHer4nGT@nowshowing.55wf9.mongodb.net/";
        
        MongoClient mongoClient = MongoClients.create(url);                   
        MongoDatabase database = mongoClient.getDatabase("nowshowing");                    
        MongoCollection<Document> collection = database.getCollection("media");
    }
}
