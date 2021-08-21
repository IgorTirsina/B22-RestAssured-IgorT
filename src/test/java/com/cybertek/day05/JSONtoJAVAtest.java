package com.cybertek.day05;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class JSONtoJAVAtest extends SpartanTestBase {

    @DisplayName("Get one Spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .extract().response();

        Map<String, Object> jsonMap = response.as(Map.class);


    }


}
