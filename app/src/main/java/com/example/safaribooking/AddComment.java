package com.example.safaribooking;

public class AddComment {
    private String comment,userID,commentKey,email;

    public AddComment(){

    }

    public AddComment(String comment, String userID, String commentKey, String email) {
        this.comment = comment;
        this.userID = userID;
        this.commentKey = commentKey;
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public String getUserID() {
        return userID;
    }

    public String getCommentKey() {
        return commentKey;
    }

    public String getEmail() {
        return email;
    }
}
