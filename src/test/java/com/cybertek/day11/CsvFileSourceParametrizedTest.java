package com.cybertek.day11;

import com.cybertek.utilities.ZippopotamTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CsvFileSourceParametrizedTest extends ZippopotamTestBase {

    @ParameterizedTest
    @CsvFileSource(resources = "/zipcode.csv", numLinesToSkip = 1)
    public void test1(String stateArg, String cityArg, int zipCountArg) {

        System.out.println("stateArg = " + stateArg);
        System.out.println("cityArg = " + cityArg);
        System.out.println("zipCountArg = " + zipCountArg);


        given()
                .accept(ContentType.JSON)
                .and().pathParam("state", stateArg)
                .and().pathParam("city", cityArg)
                .when()
                .get("/us/{state}/{city}")
                .then()
                .statusCode(200)
                .and().body("places", hasSize(zipCountArg));


    }

}
