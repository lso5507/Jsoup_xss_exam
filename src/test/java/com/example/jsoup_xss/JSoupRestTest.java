package com.example.jsoup_xss;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JSoupRestTest {
    int port;

    @Autowired
    TestRestTemplate restTemplate;



    @Test
    void create_test() {

    }
}
