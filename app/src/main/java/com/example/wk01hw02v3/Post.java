package com.example.wk01hw02v3;

import com.google.gson.annotations.SerializedName;


//Object for each post given by API
public class Post {
    @SerializedName("userId")
    private int userID;
    @SerializedName("id")
    private int postID;

    private String title;
    @SerializedName("body")
    private String text;



    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
