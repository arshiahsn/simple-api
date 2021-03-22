package com.arshia.simpleapi;

import com.arshia.consumer.Post;
import com.arshia.service.BlogService;
import com.arshia.service.BlogWorker;
import com.arshia.utility.GeneralUtility;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * Blog Controller
 *
 *
 * @author Arshia Hosseini
 *
 */
@RestController
public class BlogController {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    BlogService blogService;

    @Bean                                                                                                               /* To consume RESTful API */
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * Sends a ping to the server.
     * If the server is running, it should reply with
     * with a 200 OK and a JSON Object containing {success:true}
     * <p>
     * @return 200 OK and success:true
     * @throws JSONException
     *
     */
    @RequestMapping(value = "/api/ping", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<String> ping() throws JSONException {
        return new ResponseEntity<>("{\"success\":\"true\"}", HttpStatus.OK);
    }

    /**
     * Exception handler for the case where the tags parameter
     * does not exist.
     * <p>
     * @param  ex  exception thrown when a require parameter, MissingServletRequestParameterException
     * does not exist.
     * @return      {error:Tags parameter is required}
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object>  handleMissingParams(MissingServletRequestParameterException ex) {
        LOGGER.severe(ex.getMessage());
        return new ResponseEntity<>("{\"error\":\"Tags parameter is required\"}", HttpStatus.BAD_REQUEST);
    }

    /**
     * Consumes a REST API.
     * <p>
     * @param  tags  tags of the posts seprated by ",".
     * @param  sortBy indicates the parameter with which the posts should be sorted
     * @param direction indicates the order to which the posts should be sorted
     * @return      posts
     */
    @RequestMapping(value = "/api/posts", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Object> getPosts(@RequestParam(value = "tags" ) String tags,
                                           @RequestParam(value = "sortBy", required=false, defaultValue = "id") String sortBy,
                                           @RequestParam(value = "direction", required=false, defaultValue = "asc") String direction) throws InterruptedException, ExecutionException {


        if (!GeneralUtility.validateTags(tags))
            return new ResponseEntity<>("{\"error\":\"Tags parameter is required\"}", HttpStatus.BAD_REQUEST);
        if (!GeneralUtility.validateDirection(direction))
            return new ResponseEntity<>("{\"error\":\"direction parameter is invalid\"}", HttpStatus.BAD_REQUEST);
        if (!GeneralUtility.validateSortBy(sortBy))
            return new ResponseEntity<>("{\"error\":\"sortBy parameter is invalid\"}", HttpStatus.BAD_REQUEST);


        String tagList [] = GeneralUtility.parseTag(tags);                                                              /* Split the tags by "," */
        List<Callable<BlogWorker>> blogList = new ArrayList<Callable<BlogWorker>>();
        Set<Post> posts = new HashSet<>();                                                                              /* Creating a HashSet to avoid duplicates */
        final ExecutorService execServ = Executors.newFixedThreadPool(tagList.length);                                  /* Executor service to handle concurrency */
        for(String tagStr : tagList){
            blogList.add(new BlogWorker(tagStr,blogService));
        }
        List<Future<BlogWorker>> futureObjects = execServ.invokeAll(blogList);                                          /* Making concurrent requests */
        execServ.shutdown();                                                                                            /* Accept no new requests */
        try {
            execServ.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);                                            /* Wait for all requests to finish */
        } catch (InterruptedException e) {
        }
        for(Future<BlogWorker> blogWorker : futureObjects){
            posts.addAll(blogWorker.get().getResponseEntity().getBody().getPosts());                                    /* Add the result to the HashSet*/

        }

        return new ResponseEntity<>("{\"posts\": "+GeneralUtility.sortPosts(posts,direction,sortBy)               /* Sort and return the posts*/
                .toString()+"}", HttpStatus.OK);
    }
}





