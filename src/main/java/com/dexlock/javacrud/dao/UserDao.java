package com.dexlock.javacrud.dao;

import com.dexlock.javacrud.db.mongodb.MongoDBConnection;
import com.dexlock.javacrud.models.User;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDao {
    Gson gson = new Gson();
    MongoDBConnection mongoDBConnection = MongoDBConnection.getInstance();
    MongoCollection<Document> usersCollection =  mongoDBConnection.getUsersCollection();
    MongoCollection<Document> activeUsersCollection = mongoDBConnection.getActiveUsersCollection();
    public UserDao() {;


    }

    public Document loginUser( String email, String password) {
        Document userExist = usersCollection.find().first();
        return userExist;
    }

    public List<Document> getUsers() {
        List<Document> allUsers = usersCollection.find().into(new ArrayList<Document>());
        return allUsers;
    }

    public Document addUser(User userToBeAdded) {
        Document user = Document.parse(gson.toJson(userToBeAdded));
        usersCollection.insertOne(user);
        return user;
    }
    public String deleteUser(String userName) {
        usersCollection.deleteOne(new Document("name",userName));
        return "Success";
    }


    public boolean getActiveUsers(String token) {
        Document loggedInUser = activeUsersCollection.find(new Document("tokenId",token)).first();
        if(loggedInUser == null)
            return false;
        else
            return true;
    }

    public User getUser(String token) {
        Document loggedInUser = activeUsersCollection.find(new Document("tokenId",token)).first();
        String email = loggedInUser.get("email").toString();
        Document loggedUser= usersCollection.find(new Document("email",email)).first();
        User user =new User();
        user.setName(loggedUser.get("name").toString());
        user.setName(loggedUser.get("email").toString());
        user.setName(loggedUser.get("password").toString());
        user.setUserRole(User.role.valueOf(loggedUser.get("userRole").toString()));

        return user;
    }

    public Boolean logout(String token) {
        String tokenId = token.split("Bearer ")[1];
        activeUsersCollection.deleteOne(new Document("tokenId",tokenId));
        return true;

    }

    public boolean addActiveUsers(String tokenId, String email, String userRole) {
        Document activeUser = new Document("tokenId", tokenId).append("email", email).append("userRole", userRole);
        activeUsersCollection.insertOne(activeUser);
        return true;
    }
}