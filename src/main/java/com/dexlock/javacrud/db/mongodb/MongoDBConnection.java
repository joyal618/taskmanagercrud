package com.dexlock.javacrud.db.mongodb;

import com.dexlock.javacrud.application.ApplicationConfig;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public class MongoDBConnection {
    private static MongoDBConnection instance = null;
    private MongoClient mongoClient;
    private MongoDatabase taskManagerDB;
    private MongoCollection<Document> usersCollection , taskCollection,commentCollection,activeUsersCollection;

    private  MongoDBConnection(){
        this.mongoClient = new MongoClient("localhost",27017);
        this.taskManagerDB = mongoClient.getDatabase("TaskManagerDB");
        this.taskCollection = taskManagerDB.getCollection("tasks");
        this.commentCollection= taskManagerDB.getCollection("comments");
        this.usersCollection = taskManagerDB.getCollection("users");
        this.activeUsersCollection = taskManagerDB.getCollection("activeUsers");
    }
    public MongoCollection<Document> getUsersCollection() {
        return usersCollection;
    }

    public MongoCollection<Document> getTaskCollection() {
        return taskCollection;
    }

    public MongoCollection<Document> getCommentCollection() {
        return commentCollection;
    }

    public MongoCollection<Document> getActiveUsersCollection() {
        return activeUsersCollection;
    }


    public MongoClient getMongoClient() {
        return mongoClient;
    }



    public static synchronized MongoDBConnection getInstance() {
        if (instance == null)
            instance =  new MongoDBConnection();
        return instance;
    }

}

