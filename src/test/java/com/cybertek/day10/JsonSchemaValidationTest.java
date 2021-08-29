package com.cybertek.day10;

import com.cybertek.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class JsonSchemaValidationTest extends SpartanAuthTestBase {
    @DisplayName("Get request to verify one spartan agains to schema")
    @Test
    public void schemaValidation() {

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id" , 194)
                .and()
                .auth().basic("admin", "admin")
        .when()
                .get("/api/spartans/{id}")
        .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"))
                .log().all();

    }

}
