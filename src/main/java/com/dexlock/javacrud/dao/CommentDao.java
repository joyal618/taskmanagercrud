package com.dexlock.javacrud.dao;

import com.dexlock.javacrud.db.mongodb.MongoDBConnection;
import com.dexlock.javacrud.models.Comment;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import javax.print.Doc;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Updates.set;

public class CommentDao {
    Gson gson = new Gson();
    MongoDBConnection mongoDBConnection = MongoDBConnection.getInstance();
    MongoCollection<Document> commentsCollection =  mongoDBConnection.getCommentCollection();
    MongoCollection<Document> taskCollection =  mongoDBConnection.getTaskCollection();
    MongoCollection<Document> activeUsersCollection =  mongoDBConnection.getActiveUsersCollection();


    public boolean addComment(Comment comment, String taskNo, String token) {
        String tokenId = token.split("Bearer ")[1];
        Document commentAuthor = activeUsersCollection.find(eq("tokenId",tokenId)).first();
        String commentAuthorEmail= commentAuthor.get("email").toString();
        Document commentToAdd = Document.parse(gson.toJson(comment));
        commentToAdd.append("commentedUser",commentAuthorEmail);
        commentsCollection.insertOne(commentToAdd);
        taskCollection.updateOne(eq("taskNo",taskNo),addToSet("comments",commentToAdd.get("_id")));
        return true;
    }

    public boolean editComment(Comment comment) {
        Document editComment = Document.parse(gson.toJson(comment));
        ObjectId oid =  new ObjectId(editComment.get("id").toString());
        Bson filter = eq("_id",oid);
        for (String s:editComment.keySet()
        ) {
            if(!(s.equals("id")))
                 commentsCollection.updateOne(filter,set(s,editComment.get(s)));
        }

        return true;
    }

    public Boolean deleteComment(String commentId) {
        ObjectId oid = new ObjectId(commentId);
        commentsCollection.deleteOne(eq("_id",oid));
        return true;
    }
}
