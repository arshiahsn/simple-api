package com.arshia.utility;

import com.arshia.consumer.Post;

import java.util.*;
import java.util.stream.Collectors;


/**
 * A general utility class.
 * <p>
 * Has parameter validators and set sort
 */
public final class GeneralUtility {

    //Validates the direction parameter
    public static boolean validateDirection(String direction){
        if(!direction.equals("asc")  && !direction.equals("desc"))
            return false;
        return true;

    }

    //Splits the tag request by the ","
    public static String [] parseTag(String tags){
        return tags.split(",");
    }

    //Validates the sortBy parameter
    public static boolean validateSortBy(String sortBy){

        if(!sortBy.equals("id")
                && !sortBy.equals("A")
                && !sortBy.equals("B")
                && !sortBy.equals("C"))
            return false;
        return true;

    }

    //Validates the tags parameter
    public static boolean validateTags(String tags){
        if (!textHasContent(tags))
            return false;
        return true;
    }

    //Helper function
    private static boolean textHasContent(String text){
        String EMPTY_STRING = "";
        return (text != null) && (!text.trim().equals(EMPTY_STRING));
    }

    //Sorts the posts according to the direction and sortBy
    public static List<Post> sortPosts(Set<Post> posts, String direction, String sortBy){
        List<Post> sortedPosts;
        if(sortBy.equals("A")){
            sortedPosts = posts.stream()
                    .sorted(Comparator.comparing(Post::getReads)) //comparator - how you want to sort it
                    .collect(Collectors.toList());
        }
        else if(sortBy.equals("B")){
            sortedPosts = posts.stream()
                    .sorted(Comparator.comparing(Post::getLikes))
                    .collect(Collectors.toList());
            }

        else if(sortBy.equals("C")){
            sortedPosts = posts.stream()
                    .sorted(Comparator.comparing(Post::getPopularity))
                    .collect(Collectors.toList());
            }
        else{
            sortedPosts = posts.stream()
                    .sorted(Comparator.comparing(Post::getId))
                    .collect(Collectors.toList());
        }
        //Reverse the array list if the direction is descending
        if(direction.equals("desc"))
            Collections.reverse(sortedPosts);
        return sortedPosts;




    }

}
