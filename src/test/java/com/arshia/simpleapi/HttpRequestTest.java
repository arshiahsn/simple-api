package com.arshia.simpleapi;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testForSortByTech() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/posts?tags=A",
                String.class)).contains("A");
    }

    @Test
    public void testForSortByHistory() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/posts?tags=B",
                String.class)).contains("B");
    }

    @Test
    public void testForSortByHealth() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/posts?tags=C",
                String.class)).contains("C");
    }

    @Test
    public void testForSortByHealthAndHistoryAndTech() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/posts?tags=A,B,C",
                String.class)).contains("A","B","C");
    }

    @Test
    public void testForNoTag() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/posts?direction=desc",
                JSONObject.class)).toString().equals("{\"error\":\"Tags parameter is required\"}");
    }
    @Test
    public void testForInvalidDirection() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/posts?tags=tech&direction=foo",
                JSONObject.class)).toString().equals("{\"error\":\"direction parameter is invalid\"}");
    }
    @Test
    public void testForInvalidSortBy() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/posts?tags=tech&sortBy=bar",
                JSONObject.class)).toString().equals("{\"error\":\"sortBy parameter is invalid\"}");
    }
}