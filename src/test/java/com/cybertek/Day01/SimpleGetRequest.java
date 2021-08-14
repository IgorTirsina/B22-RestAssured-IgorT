package com.cybertek.Day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://54.92.248.102:8000/api/spartans";

    @Test
    public void test1() {
        //send a get request and save response insede the Response object
        Response response = RestAssured.get(url);

        //print the response status code
        System.out.println(response.statusCode());

        //print response body
        response.prettyPrint();


    }

}
