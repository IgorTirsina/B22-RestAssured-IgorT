package com.cybertek.day03;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithParam extends HRTestBase {

//    @BeforeAll
//    public static void init(){
//        //save baseurl inside this variable so that we dont need to type each http method.< 54.92.248.102 >
//        baseURI = "http://54.92.248.102:1000/ords/hr";
//    }

    @DisplayName("GET request to /countries with Query Param")
    @Test
    public void test1(){
        Response response= given().accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\":2}")
                .log().all()
                .when()
                .get("/countries");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("United States of America"));

        response.prettyPrint();

    }

    @DisplayName("GET request /employees/ who works in IT_PROG")
    @Test
    public void test2(){
        Response response= given().log().all()
                                    .accept(ContentType.JSON)
                                    .and().queryParam("q","{\"job_id\":\"IT_PROG\"}")
                           .when()
                                    .get("/employees");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));
        response.prettyPrint();

    }







}
