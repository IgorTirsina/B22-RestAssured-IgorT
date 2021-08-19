package com.cybertek.day04;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanWithJsonPath extends SpartanTestBase {

    @DisplayName("GET one spartan with JsonPath")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                                    .and().pathParam("id", 10)
                            .when().get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json" , response.contentType());

        //print name with path method
        System.out.println("response.path(\"name\").toString() = " + response.path("name").toString());

        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);



    }


}
