package com.example.project;

public class Post {
    long postID;
    String title;
    String description;
    String price;

    public Post(long pID, String t, String d, String p) {
        postID = pID;
        title = t;
        description = d;
        price = p;
    }
}
