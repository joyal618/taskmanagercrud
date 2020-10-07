package com.dexlock.javacrud.dao;

import com.dexlock.javacrud.db.mongodb.MongoDBConnection;
import com.dexlock.javacrud.models.Task;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Updates.*;


public class TaskDao {
    MongoDBConnection mongoDBConnection = MongoDBConnection.getInstance();
    MongoCollection<Document> taskCollection =  mongoDBConnection.getTaskCollection();
    MongoCollection<Document> activeUsersCollection =  mongoDBConnection.getActiveUsersCollection();
    Gson gson = new Gson();

    public Document addTask(Task taskToBeAdded, String token) {
        String tokenId = token.split("Bearer ")[1];
        Document reporter = activeUsersCollection.find(eq("tokenId",tokenId)).first();
        String reporterEmail = reporter.get("email").toString();
        Document taskAdded = Document.parse(gson.toJson(taskToBeAdded));
        taskAdded.append("taskReporter",reporterEmail);
        taskCollection.insertOne(taskAdded);
        String k = taskAdded.get("taskTitle").toString();
        return taskAdded;
    }

    public Document updateTask(Task taskToBeUpdated) {
        Document updateTask = Document.parse(gson.toJson(taskToBeUpdated));
        Document updateTarget = taskCollection.find(new Document("taskNo",updateTask.get("taskNo"))).first();
        String updateTaskNo = updateTarget.get("taskNo").toString();
        Bson filter = eq("taskNo",updateTaskNo);
        for (String s:updateTask.keySet()
        ) {
            taskCollection.updateOne(filter,set(s,updateTask.get(s)));
        }

        return updateTask;
    }

    public Boolean attachFile(String taskNo, String path) {
        taskCollection.updateOne(eq("taskNo",taskNo),addToSet("file",path));
        return true;
    }

    public String deleteFile(String filename, String deletionFileLocation) {
        File deleteFile = new File(deletionFileLocation);
        if (deleteFile.exists()){
            deleteFile.delete();
            return "File Deleted";
        }
        else {
            return "File not Found!";
        }
    }
    public Object downloadFile(String downloadFileLocation) {
        File downloadFile = new File(downloadFileLocation);
        if (downloadFile.exists())
            return downloadFile;
        else return "File not Found!!";
    }

    public List<Document> getTasks(String pageNo) {
        int limit = 2;
        int skip = (Integer.parseInt(pageNo) -1) *2;
        List<Document> tasks = taskCollection.find().limit(limit).skip(skip).into(new ArrayList<Document>());
        return tasks;
    }
}
