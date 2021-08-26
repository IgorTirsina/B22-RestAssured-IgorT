package com.cybertek.day08;

import com.cybertek.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SpartanWithAuthTest extends SpartanAuthTestBase {

    @DisplayName("Get /api/spartans as a public user(guest) expect 401")
    @Test
    public void test01() {
        given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then().statusCode(401).log().all();

    }

    @DisplayName("Get /api/spartans as admin user expect 200")
    @Test
    public void test02() {

        given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .and().log().all();

    }

    @DisplayName("Delete /api/spartans/{id} as editor user expect 403 ")
    @Test
    public void test03() {

        given()
                .auth().basic("editor", "editor")
                .and().accept(ContentType.JSON)
                .and().pathParam("id", "35")
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(403)
                .log().all();
    }
    //TODO : write a detailed test for Role Base Access Control(RBAC)
    //spartan.7000
    //admin should be able to take all CRUD
    //editor should be able to all CRUD, but DELETE
    //user should be able to only READdata , not update, delete, create(Post, Put, Patch, Delete)
    //can guest even read data??? 401 for all

}
