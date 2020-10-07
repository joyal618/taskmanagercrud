package com.dexlock.javacrud.models;

public class Comment {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private String commentedUser;
    private String commentText;
    private String commentUpdateTime;

    public Comment() {
    }
    public Comment( String id, String commentedUser, String commentText, String commentUpdateTime) {

        this.id = id;
        this.commentedUser = commentedUser;
        this.commentText = commentText;
        this.commentUpdateTime = commentUpdateTime;
    }


    public String getCommentedUser() {
        return commentedUser;
    }
    public void setCommentedUser(String commentedUser) {
        this.commentedUser = commentedUser;
    }
    public String getCommentText() {
        return commentText;
    }
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
    public String getCommentUpdateTime() {
        return commentUpdateTime;
    }
    public void setCommentUpdateTime(String commentUpdateTime) {
        this.commentUpdateTime = commentUpdateTime;
    }

   
}
