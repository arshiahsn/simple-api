package com.arshia.service;

import com.arshia.consumer.Posts;
import org.springframework.http.ResponseEntity;
import java.util.concurrent.*;

/**
 * A worker class for the Blog.
 * Implements Callable.
 * <p>
 * @author Arshia Hosseini
 */
public class BlogWorker implements Callable {


    private BlogService blogService;                                                                                    /* Blog Cache service class */
    private ResponseEntity<Posts> responseEntity;                                                                       /* Holds the GET response */
    private String tag;                                                                                                 /* The main query parameter */

    public ResponseEntity<Posts> getResponseEntity() {                                                                  /* Returns the response entity */
        return responseEntity;
    }


    //Making oncurrent API calls
    @Override
    public Object call() throws Exception {
        this.responseEntity = blogService.getPostsByTag(tag);
        return this;
    }

    public BlogWorker(){

    }

    /**
     * The Constructor.
     * <p>
     * @author Arshia Hosseini
     * @param tag_ the main query parameter,
     * @param  blogService_ maintaining cache
     */
    public BlogWorker(String tag_, BlogService blogService_)  {
        this.blogService = blogService_;
        tag = tag_;
    }

}
