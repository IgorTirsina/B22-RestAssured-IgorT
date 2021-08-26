package com.cybertek.day07;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PutAndPatchRequestDemo extends SpartanTestBase {

    @DisplayName("PUT request from spartans")
    @Test
    public void PUTRequest() {

        Map<String,Object> putRequestMap = new HashMap<>();
        putRequestMap.put("name", "SpartacusJr");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 8877440003l);

        given()/*.accept(ContentType.JSON)*/    //for PUT request we dont need to add .accept(), because it will not return any body, just the status code 204
                .and().contentType("application/json")
                .body(putRequestMap).log().all()
                .and().pathParam("id", 194)
                .when()
                    .put("/api/spartans/{id}")
                .then().statusCode(204);

                //send a GET request to verify if the phone got changed

        Response response = given().accept(ContentType.JSON)
                .and().contentType("application/json")
                .and().pathParam("id", 194)
                .when().get("/api/spartans/{id}")
                .then().extract().response();

        assertThat(putRequestMap.get("phone"), is((Long)response.path("phone")));


    }

    @DisplayName("PATCH request to one spartan for partial update with Map")
    @Test
    public void PATCHRequest(){
        //just like post request we have different options to send body, we will go with map
        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("phone",8811111111L);
        putRequestMap.put("name","Peter");

        given().contentType(ContentType.JSON) //hey api I am sending JSON body
                .body(putRequestMap).log().all()
                .and().pathParam("id",112)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);

        //send a GET request after update, make sure updated field changed, or the new info matching
        //with requestBody that we send


    }

    @DisplayName("DELETE one spartan")
    @Test
    public void deleteSpartan(){
        int idToDelete= 258;


        given().pathParam("id",idToDelete)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

        //send a get request after you delete make sure you are getting 404

    }




}
