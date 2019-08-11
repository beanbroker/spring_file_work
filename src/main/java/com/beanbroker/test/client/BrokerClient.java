package com.beanbroker.test.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;


@Component
public class BrokerClient {


    private RestTemplate restTemplate;

    private RestTemplate getRestTemplate() {

        return new RestTemplateBuilder().build();
    }

    public UserResponse getUserInfo() {

        String host = "https://jsonplaceholder.typicode.com/todos/{id}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");

        String uri = UriComponentsBuilder.fromHttpUrl(host)
                .buildAndExpand(params)
                .toUriString();

        ResponseEntity<UserResponse> userResponse = getRestTemplate().getForEntity(
                uri,
                UserResponse.class

        );
        return userResponse.getBody();
    }
}
