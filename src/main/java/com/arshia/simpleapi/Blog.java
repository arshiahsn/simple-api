package com.arshia.simpleapi;

import com.arshia.utility.GeneralUtility;

/**
 * The Blog class used to create the REST API.
 * <p>
 * @author Arshia Hosseini
 */
public class Blog {

    //Getters and setters
    public String getTag() {
        return tag;
    }
    public String getSortBy() {
        return sortBy;
    }
    public String getDirection() {
        return direction;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }

    //Request parameters
    private String tag;
    private String sortBy;
    private String direction;

    /**
     * The Constructor.
     * <p>
     * @author Arshia Hosseini
     * @param tag_ the tags parameter
     * @param sortBy_ the sortBy parameter,
     * determines the value on which the posts are sorted.
     * @param  direction_ determines the order of the posts (ascending/descending)
     */
    public Blog(String tag_, String sortBy_, String direction_) {
        GeneralUtility.validateTags(tag_);
        this.tag = tag_;

        GeneralUtility.validateSortBy(sortBy_);
        this.sortBy = sortBy_;

        GeneralUtility.validateDirection(direction_);
        this.direction = direction_;



    }

    public Blog(){

    }


}
