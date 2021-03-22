package com.arshia.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;


/**
 * The posts class.
 * Holds a list of all posts.
 * <p>
 * @author Arshia Hosseini
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Posts {
    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public Posts(ArrayList posts_){
        posts = posts;
    }

    public Posts(){

    }

    @Override
    public String toString() {
        return "{" +
                "posts=" + posts +
                '}';
    }



    @JsonProperty("posts")
    private ArrayList<Post> posts;
}
