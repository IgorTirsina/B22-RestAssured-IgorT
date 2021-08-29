package com.cybertek.day10;

import com.cybertek.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
public class ResponseTimeTest extends SpartanAuthTestBase {

    @Test
    public void test1() {
        Response response = given().auth().basic("admin", "admin")
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()
//                .time(lessThanOrEqualTo(1100L))
                .time(both(greaterThan(300L)).and(lessThan(1000L)))
                .extract().response();

        System.out.println("response.getTime() = " + response.getTime());

    }

}
