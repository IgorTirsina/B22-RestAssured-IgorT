package com.cybertek.day05;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ORDSHamcrestApiTest extends HRTestBase {

    List<String> names = Arrays.asList("Alexander", "Bruce" , "David", "Valli", "Diana");

    @DisplayName("Get request to Employees IT_PROG endpoint and chaining")
    @Test
    public void test01() {
        //send get request to employees endpoint with query param job_id IT_PROG
        //verify each job_is is IT_PROG
        //verify first names are ... (find a proper method to check list against list)
        //verify emails without checking order (provide emails in different order, just make sure it has same emails)

        given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q" , "{\"job_id\":\"IT_PROG\"}")
        .when()
                .get("/employees")
        .then()
                .statusCode(200)
                .body("items.job_id" , everyItem(is("IT_PROG")))
                .body("items.first_name" , containsInRelativeOrder("Alexander", "Bruce", "David", "Valli"))
                .body("items.email" , containsInAnyOrder("VPATABAL", "DAUSTIN", "BERNST", "AHUNOLD" , "DLORENTZ"))
                .body("items.first_name" , equalToObject(names));


    }

    @Test
    public void test02() {

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(is("IT_PROG")))
                .extract()
                .response();

        int statusCode = given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(is("IT_PROG")))
                .extract()
                .response().statusCode();
        //extract().response() / extract().jsonPath() / etc

        JsonPath jsonPath = given().log().all()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(is("IT_PROG")))
                .extract().jsonPath();

        response.prettyPrint();

        assertThat(jsonPath.getList("items.first_name"), hasSize(5));

        assertThat(jsonPath.getList("items.first_name"), equalToObject(names));


    }

}
