package com.arshia.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONArray;

import java.util.List;
import java.util.Objects;

/**
 * The post class.
 * Used to consume a REST API
 * <p>
 * @author Arshia Hosseini
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {


    //Getters and setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getReads() {
        return reads;
    }

    public void setReads(int reads) {
        this.reads = reads;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    //The equals function to determine whether two objects of Post are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id &&
                authorId == post.authorId &&
                likes == post.likes &&
                popularity == post.popularity &&
                reads == post.reads &&
                Objects.equals(author, post.author) &&
                Objects.equals(tags, post.tags);
    }

    //Generates hashcode for the objects of Post
    @Override
    public int hashCode() {
        return Objects.hash(id, author, authorId, likes, popularity, reads, tags);
    }

    //Convert the tag array to the correct JSON format
    public String arrayToString(){
        String str = "[";
        Boolean flag = false;
        for(String tag : tags) {
            if(!flag){
                str += "\""+tag.toString()+"\"";
                flag = true;
            }

            else{
                str += ",\""+tag.toString()+"\"";
            }
        }
        str += "]";
        return str;
    }

    @Override
    public String toString() {
        return "{" +
                "\"author\":"  + "\"" + author + "\"" +
                ", \"authorId\":"  + authorId  +
                ", \"id\":"  +  id +
                ", \"likes\":"  + likes   +
                ", \"popularity\":"  + popularity +
                ", \"reads\":"  + reads  +
                ", \"tags\":"  + new JSONArray(tags) +
                "}";
    }

    //JsonProperty parameters
    @JsonProperty("author")
    private String author;
    @JsonProperty("authorId")
    private int authorId;
    @JsonProperty("id")
    private int id;
    @JsonProperty("likes")
    private int likes;
    @JsonProperty("popularity")
    private double popularity;
    @JsonProperty("reads")
    private int reads;
    @JsonProperty("tags")
    private List<String> tags;

}
