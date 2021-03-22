package com.arshia.simpleapi;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PingTest {

    @Autowired
    private BlogController controller;

    @Test
    void hasPing() throws JSONException {
        assertThat(controller.ping().equals("{\"success\":\"true\"}"));
    }

}
