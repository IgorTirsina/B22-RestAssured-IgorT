package com.cybertek.day05;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest extends SpartanTestBase {


    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test01() {
        //chaining with Hamcrest Matchers
        given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
        .when()
                .get("/api/spartans/{id}")
        .then()
                .assertThat()
                .statusCode(200)
                .and().assertThat()
                .contentType("application/json")
                .and().assertThat()
                .body("id", equalTo(15),
                        "name", is("Meta"),
                        "gender", is("Female"),
                        "phone", is(1938695106));

    }

    @DisplayName("CBTraining Teacher request 10423")
    @Test
    public void test02() {

        given()
                .accept(ContentType.JSON)
                .and().pathParam("id" , 10423)
        .when()
                .get("http://api.cybertektraining.com/teacher/{id}")
        .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Content-Length", is("236"))
                .and()
                .header("Date", notNullValue())
                .and().assertThat()
                .body("teachers[0].firstName" , is("Alexander"),
                        "teachers[0].lastName" , is("Syrup"),
                        "teachers[0].gender" , is("male"));
    }

    @DisplayName("get request to teacher/all and chaining")
    @Test
    public void test03() {

        given()
                .accept(ContentType.JSON)
        .when()
                .get("http://api.cybertektraining.com/teacher/all")
        .then()
                .statusCode(200)
                .and().assertThat()
                .body("teachers.firstName" , hasItems("Alexander", "Clyde", "ZX"));

    }


}
