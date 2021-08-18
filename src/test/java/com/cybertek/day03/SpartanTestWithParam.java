package com.cybertek.day03;

import static io.restassured.RestAssured.*;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanTestWithParam {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.< 54.92.248.102 >
        baseURI = "http://54.92.248.102:8000";
    }

    /*
          Given accept type is Json
          And Id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "Blythe" should be in response payload
       */

    @DisplayName("GET request to /api/spartans/{id} with ID 5")
    @Test
    public void test1() {

        Response response = given()
                                    .accept(ContentType.JSON)
                                    .and().pathParam("id", 5)
                            .when()
                                    .get("/api/spartans/{id}");
        //verify status code
        assertEquals(200 , response.statusCode());
        //verify content type
        assertEquals("application/json" , response.contentType());
        //verify Blythe is the json payload/body
        assertFalse(response.body().asString().contains("Blythe"));
    }
     /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */
    @DisplayName("Get request /api/spartans/{id} negative test")
    @Test
    public void test2() {

        Response response = given()
                                    .accept(ContentType.JSON)
                                    .and().pathParam("id", 500)
                            .when()
                                    .get("/api/spartans/{id}");

        //verify status code 404
        assertEquals(404 , response.statusCode());
        //verify content type
        assertEquals("application/json" , response.contentType());
        //verify NotFound in the json payload/body
        assertTrue(response.body().asString().contains("Not Found"));

    }

    @DisplayName("Get request to /api/spartans/serach with Quer Params")
    @Test
    public void test3() {
        //to receive you logs use .log().all(), or .log().parameters(params)
        Response response = given().log().all()
                                    .accept(ContentType.JSON)
                                    .and().queryParam("nameContains" , "e")
                                    .and().queryParam("gender" , "Female")
                            .when()
                                    .get("/api/spartans/search");

        //verify status code 200
        assertEquals(200 , response.statusCode());
        //verify content type
        assertEquals("application/json" , response.contentType());
        //verify Female in the json payload/body
        assertTrue(response.body().asString().contains("Female"));
        //janette should be in
        assertTrue(response.body().asString().contains("Janette"));

        System.out.println(response.body().asPrettyString());


    }

    @DisplayName("Get")
    @Test
    public void test4() {

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("nameContains", "e");
        queryMap.put("gender", "Female");

        Response response = given().log().all()
                                    .accept(ContentType.JSON)
                                    .and().queryParams(queryMap)
                                    .when()
                                    .get("/api/spartans/search");
        //verify status code 200
        assertEquals(200 , response.statusCode());
        //verify content type
        assertEquals("application/json" , response.contentType());
        //verify Female in the json payload/body
        assertTrue(response.body().asString().contains("Female"));
        //janette should be in
        assertTrue(response.body().asString().contains("Janette"));

        System.out.println(response.body().asPrettyString());


    }

}
