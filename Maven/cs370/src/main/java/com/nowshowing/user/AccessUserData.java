package com.nowshowing.user;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.StringTemplate.STR;


interface AccessUserData {

    public static final String MONGO_URI = "mongodb+srv://arthurcourbevoie:90oBVtWbT3weWqea@usernowshowing.8tofy.mongodb.net/?retryWrites=true&w=majority&appName=UserNowShowing";

    private static Boolean AddUser(String fname, String lname, String username, String password){
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------
        // Add User method
        // Input :
        //        - fname : first name of the user (string)
        //        - lname : last name of the user (string)
        //        - username : username of the user account (string)
        //        - password : password of the user account (string)
        // Explanation of the code :
        // The code connect to mongodb database with the URL and add a new row inside it (in mongodb row is symbolize by document
        // If we can't have access to the database, the program send an error message and if he can access and add the user, it put in the console a confirmation.
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------

        ArrayList<String> watchlist = new ArrayList<>();                                    // create a new watchlist (list of string) for the user
        try (MongoClient mongoClient = (MongoClient) MongoClients.create(MONGO_URI)) {                    // try to connect to the database (create a client of the database with the URL)
            MongoDatabase database = mongoClient.getDatabase("User");                    // Name of the database
            MongoCollection<Document> collection = database.getCollection("UserData");   // Name of the collection where the database is stored
            Document doc = new Document("first name",fname)                                 // Create a new row
                    .append("last name",lname)                                              // Add last name
                    .append("username",username)                                            // Add username
                    .append("password",password)                                            // Add password
                    .append("watchlist",watchlist);                                         // Add watchlist
            collection.insertOne(doc);                                                      // Insert the row in the database
            System.out.println("Inserted into user data !");                                // Console out to confirm the action done
            return false;
        } catch (Exception e) {
            System.err.println(STR."Error : \{e.getMessage()}");                            // If error, error message
            return true;
        }
    }

    private void ReadUsers(){
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------
        // Read User method
        // Explanation of the code :
        // The code connect to mongodb database with the URL and read all the row of the
        // If we can't have access to the database, the program send an error message and if he can access and add the user, it put in the console a confirmation.
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------

        try (MongoClient mongoClient = (MongoClient) MongoClients.create(MONGO_URI)) {                    // try to connect to the database (create a client of the database with the URL)
            MongoDatabase database = mongoClient.getDatabase("User");                    // Name of the database
            MongoCollection<Document> collection = database.getCollection("UserData");   // Name of the collection where the database is stored
            FindIterable<Document> Documents = collection.find();                           // Extract all the row of the database
            for (Document doc : Documents) {                                                // For each row, we print the user data in the console (for now)
                String fname = doc.getString("first name");
                String lname = doc.getString("last name");
                String username = doc.getString("username");
                String password = doc.getString("password");
                System.out.println(STR."""
-------------------------------
First name: \{fname}
Last name: \{lname}
Username: \{username}
Password: \{password}
-------------------------------
""");
            }
        } catch (Exception e) {                                                             // If error, error message
            System.err.println(STR."Error : \{e.getMessage()}");
        }
    }

    private static ObjectId getUserId(String username){
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------
        // get User ID method
        // Input :
        //        - username : username of the user account (string)
        // Output :
        //        - id : row id in the database of the user (ObjectId)
        // Explanation of the code :
        // The code connect to mongodb database with the URL and search for the username
        // If we can't have access to the database, the program send an error message and if he can access and have the user, it put in the console a confirmation and if he have not he sends a don't find message.
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------

        ObjectId id = null;                                                                 // Create an ObjectId that will contain the row ID of the user
        try (MongoClient mongoClient = (MongoClient) MongoClients.create(MONGO_URI)) {                    // try to connect to the database (create a client of the database with the URL)
            MongoDatabase database = mongoClient.getDatabase("User");                    // Name of the database
            MongoCollection<Document> collection = database.getCollection("UserData");   // Name of the collection where the database is stored
            Document query = new Document("username", username);                            // Looking for the row which contains the username in the database
            Document result = collection.find(query).first();                               // Store the result in a variable
            if (result != null) {                                                           // If he finds someone, he returns the ID else, he says don't find the user
                id = result.getObjectId("_id");
            } else {
                System.out.println(STR."Don't find \{username}");
            }
        } catch (Exception e) {                                                             // If error, error message
            System.err.println(STR."Error : \{e.getMessage()}");
        }
        return id;
    }

    private static ArrayList<String> getUserData(String username) {
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------
        // get User data method
        // Input :
        //        - username : username of the user account (string)
        // Output :
        //        - data : information in the database of the user (String[])
        // Explanation of the code :
        // The code connect to mongodb database with the URL and search for the username
        // If we can't have access to the database, the program send an error message and if he can access and have the user, it put in the console a confirmation and if he have not he sends a don't find message.
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------

        ArrayList<String> data = new ArrayList<String>();                                                              // Create an array of string
        try (MongoClient mongoClient = (MongoClient) MongoClients.create(MONGO_URI)) {                    // try to connect to the database (create a client of the database with the URL)
            MongoDatabase database = mongoClient.getDatabase("User");                    // Name of the database
            MongoCollection<Document> collection = database.getCollection("UserData");   // Name of the collection where the database is stored
            Document query = new Document("username", username);                            // Looking for the row which contains the username in the database
            Document result = collection.find(query).first();                               // Store the result in a variable
            if (result != null) {                                                           // If he finds someone, he store the user data
                data.add("good");
                String fname = result.getString("first name");
                data.add(fname);
                String lname = result.getString("last name");
                data.add(lname);
                String password = result.getString("password");
                data.add(password);
            }
        } catch (Exception e) {                                                             // If error, error message
            System.err.println(STR."Error : \{e.getMessage()}");
            data.add("error");
        }
        return data;
    }

    private static ArrayList<String> getUsernames(){
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------
        // get usernames method
        // Output :
        //        - data : all the username stored in the database (ArrayList<String>)
        // Explanation of the code :
        // The code connect to mongodb database with the URL and search for the username
        // If we can't have access to the database, the program send an error message and if he can access, he put all the username stored in the database into a list
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------

        ArrayList<String> data = new ArrayList<>();
        try (MongoClient mongoClient = (MongoClient) MongoClients.create(MONGO_URI)) {
            MongoDatabase database = mongoClient.getDatabase("User");
            MongoCollection<Document> collection = database.getCollection("UserData");
            FindIterable<Document> Documents = collection.find();
            for (Document doc : Documents) {
                String fname = doc.getString("username");
                data.add(fname);
            }
        } catch (Exception e) {
            System.err.println(STR."Error : \{e.getMessage()}");
        }
        return data;
    }

    private static String getUserPassword(String username){
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------
        // get user password method
        // Input :
        //        - username : username of the user (String)
        // Output :
        //        - password : password of the user (String)
        // Explanation of the code :
        // The code connect to mongodb database with the URL and search for the username
        // If we can't have access to the database, the program send an error message and if he can access, he return the password of the user
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------

        String password = "";
        try (MongoClient mongoClient = (MongoClient) MongoClients.create(MONGO_URI)) {
            MongoDatabase database = mongoClient.getDatabase("User");
            MongoCollection<Document> collection = database.getCollection("UserData");
            Document query = new Document("username", username);
            Document result = collection.find(query).first();

            if (result != null) {
                password = result.getString("password");
            }
        } catch (Exception e) {
            System.err.println(STR."Error : \{e.getMessage()}");
        }
        return password;
    }

    private static Boolean deleteUser(String username){
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------
        // delete user method
        // Input :
        //        - username : username of the user (String)
        // Explanation of the code :
        // The code connect to mongodb database with the URL and search for the username
        // If we can't have access to the database, the program send an error message and if he can access, he deletes the user from the database
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------

        try (MongoClient mongoClient = (MongoClient) MongoClients.create(MONGO_URI)) {
            MongoDatabase database = mongoClient.getDatabase("User");
            MongoCollection<Document> collection = database.getCollection("UserData");
            Document query = new Document("username", username);
            Document result = collection.find(query).first();

            if (result != null) {
                collection.deleteOne(result);
                System.out.println("User deleted");
            } else {
                System.out.println(STR."Don't find \{username}");
            }
            return false;
        } catch (Exception e) {
            System.err.println(STR."Error : \{e.getMessage()}");
            return true;
        }
    }

    private static Boolean UpdateUser(String username, String new_username, String new_password, String new_fname, String new_lname){
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------
        // upate user method
        // Input :
        //        - username : username of the user (String)
        //        - new_username : new username (can be empty / String)
        //        - new_password : new password (can be empty / String)
        //        - new_fname : new first name (can be empty / String)
        //        - new_lname : new last name (can be empty / String)
        // Explanation of the code :
        // The code connect to mongodb database with the URL and search for the username
        // If we can't have access to the database, the program send an error message and if he can access, he updates the non-empty new parameter in the database
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------

        ObjectId id = getUserId(username);
        try (MongoClient mongoClient = (MongoClient) MongoClients.create(MONGO_URI)) {
            MongoDatabase database = mongoClient.getDatabase("User");
            MongoCollection<Document> collection = database.getCollection("UserData");
            Document query = new Document("username", username);
            Document result = collection.find(query).first();
            if (result != null) {
                Document updateDocument = new Document();
                if (!new_fname.isEmpty()) {
                    updateDocument.append("first name", new_fname);
                }
                if (!new_lname.isEmpty()) {
                    updateDocument.append("last name", new_lname);
                }
                if (!new_username.isEmpty()) {
                    updateDocument.append("username", new_username);
                }
                if (!new_password.isEmpty()) {
                    updateDocument.append("password", new_password);
                }
                collection.updateOne(new Document("_id", id), new Document("$set", updateDocument));
                System.out.println("User updated");
            }
            return false;
        } catch (Exception e) {
            System.err.println(STR."Une erreur s'est produite : \{e.getMessage()}");
            return true;
        }
    }

    private static ArrayList<String> getWatchlist(String username){
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------
        // get watchlist method
        // Input :
        //        - username : username of the user (String)
        // Output :
        //        - watchlist : watchlist of the user (ArrayList<String>)
        // Explanation of the code :
        // The code connect to mongodb database with the URL and search for the username
        // If we can't have access to the database, the program send an error message and if he can access, extract the watchlist
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------

        ArrayList<String> exported_data = new ArrayList<>();
        try (MongoClient mongoClient = (MongoClient) MongoClients.create(MONGO_URI)) {
            MongoDatabase database = mongoClient.getDatabase("User");
            MongoCollection<Document> collection = database.getCollection("UserData");
            Document query = new Document("username", username);
            Document result = collection.find(query).first();

            if (result != null) {
                exported_data.add("good");
                exported_data.addAll(result.get("watchlist",exported_data));
            }
        } catch (Exception e) {
            System.err.println(STR."Error : \{e.getMessage()}");
            exported_data.add("error");
        }
        return exported_data;
    }

    private static Boolean updateWatchlist(String username, ArrayList<String> watchlist) {
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------
        // update watchlist method
        // Input :
        //        - username : username of the user (String)
        //        - watchlist : watchlist of the user (ArrayList<String>)
        // Explanation of the code :
        // The code connect to mongodb database with the URL and search for the username
        // If we can't have access to the database, the program send an error message and if he can access, he updates the list inside the database
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------

        ObjectId id = getUserId(username);
        try (MongoClient mongoClient = (MongoClient) MongoClients.create(MONGO_URI)) {
            MongoDatabase database = mongoClient.getDatabase("User");
            MongoCollection<Document> collection = database.getCollection("UserData");
            Document query = new Document("username", username);
            Document result = collection.find(query).first();
            if (result != null) {
                Document updateDocument = new Document();
                updateDocument.append("watchlist", watchlist);
                collection.updateOne(new Document("_id", id), new Document("$set", updateDocument));
                System.out.println("Watchlist updated");
            }
            return false;
        } catch (Exception e) {
            System.err.println(STR."Une erreur s'est produite : \{e.getMessage()}");
            return true;
        }
    }

    static Boolean add_user_to_database(String fname, String lname, String username, String password){
        return AddUser(fname, lname, username, password);
    }

    static Boolean remove_user_from_database(String username){
        return deleteUser(username);
    }

    static Boolean update_user_info(String username, String new_username, String new_password, String new_fname, String new_lname){
        return UpdateUser(username, new_username, new_password, new_fname, new_lname);
    }

    static ArrayList<String> get_user_info(String username){
        return getUserData(username);
    }

    static ArrayList<String> get_user_watchlist(String username){
        return getWatchlist(username);
    }

    static Boolean update_user_watchlist(String username, ArrayList<String> watchlist){
        return updateWatchlist(username, watchlist);
    }

    static Boolean user_verification(String username){
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------
        // user verification method
        // Input :
        //        - username : username of the user (String)
        // Output :
        //        - exist : bool to see if the username is in the database (Boolean)
        // Explanation of the code :
        // The code use getUsernames method to have all the username of the database
        // If he found the same username twice, he send exist become true else exist still be false
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------

        boolean exist = false;
        ArrayList<String> users_data = getUsernames();

        for (String usersDatum : users_data) {
            if (usersDatum.equals(username)) {
                exist = true;
                System.out.println("User already exists");
                break;
            }
        }
        System.out.println(STR."User \{username} does not exist");
        return exist;
    }

    static boolean password_verification(String password, String username){
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------
        // password verification method
        // Input :
        //        - username : username of the user (String)
        //        - password : password enter by the user (String)
        // Output :
        //        - bool : bool to see if the password is correct (Boolean)
        // Explanation of the code :
        // The code use getUsernamePassword method to have the password
        // If the password enter and inside the database is the same he return true else he return false
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------

        if(Objects.equals(getUserPassword(username), password)){
            System.out.println("Password correct");
            return true;
        }
        else {
            System.out.println("Password incorrect");
            return false;
        }
    }
}
