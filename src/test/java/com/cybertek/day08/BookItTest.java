package com.cybertek.day08;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class BookItTest {
    @BeforeAll
    public static void init () {
        baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";
    }

    String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMzkiLCJhdWQiOiJzdHVkZW50LXRlYW0tbGVhZGVyIn0._vM1-eRoS7SsHu6T-QPdJoEdA8LSwnxUvvTTbhV-8ms";

    @DisplayName("Get ")
    @Test
    public void test01() {

       given()
               .header("Authorization" , accessToken)
               .and().accept(ContentType.JSON)
       .when()
               .get("/api/campuses")
       .then()
               .statusCode(200)
               .log().all();

       JsonPath jsonPath =  given().accept(ContentType.JSON)
                .and().auth().basic("sbirdbj@fc2.com", "asenorval")
                .when()
                .get("/sign")
                .then()
                .extract().jsonPath();

       jsonPath.prettyPrint();
//        String token ="Bearer " + jsonPath.getString("accessToken");
//
//        System.out.println("token = " + token);

    }

    @Test
    public void test02() {
        System.out.println(getBearerToken("sbirdbj@fc2.com", "asenorval"));


    }

    public static String getBearerToken(String email, String password) {
        baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";

        JsonPath jsonPath =  given().accept(ContentType.JSON)
                .and().queryParam("email", email)
                .and().queryParam("password", password)
                .when()
                .get("/sign")
                .then()
                .extract().jsonPath();

        String token ="Bearer " + jsonPath.getString("accessToken");

        return token;


    }

}
