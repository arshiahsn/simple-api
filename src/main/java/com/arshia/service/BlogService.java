package com.arshia.service;

import com.arshia.consumer.Posts;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;


/**
 * A service class to the Blog.
 * Enables caching.
 * <p>
 * @author Arshia Hosseini
 */
@Service
public class BlogService {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public BlogService(){

    }

    @Cacheable("posts")
    public ResponseEntity<Posts> getPostsByTag(String tag){
        LOGGER.warning("Cache miss.");
        RestTemplate restTemplate = new RestTemplate();
        String apiCall = "api_call";                                                                           /*TODO: API goes here*/
        return restTemplate.exchange(                                                                         /* Send a HTTP GET Request for each tag*/
                String.format(apiCall,
                        tag), HttpMethod.GET,null,
                new ParameterizedTypeReference<Posts>(){});
    }
}
