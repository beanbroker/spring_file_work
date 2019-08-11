package com.beanbroker.test.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class UserResponse {


    @JsonProperty("completed")
    private boolean completed;
    @JsonProperty("title")
    private String title;
    @JsonProperty("id")
    private int id;
    @JsonProperty("userId")
    private int userId;
}
