package com.cybertek.day11;

import com.cybertek.utilities.ZippopotamTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestTaskZip extends ZippopotamTestBase {
    /**
     * // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
     *     // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
     *     // check status code 200
     */
    @ParameterizedTest
    @ValueSource(ints = {22030,22031, 22032, 22033 , 22034, 22035, 22036})
    public void testStatusCode200(int zipcode) {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zipcode", zipcode)
                .when().get("/US/{zipcode}")
                .then().statusCode(200)
                .and().extract().response();

        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getString("places.'place name'"));

        given()
                .pathParam("zipcode", zipcode)
                .log().all()
        .when()
                .get("/us/{zipcode}")
        .then()
                .statusCode(200)
                .log().all();


    }




}
