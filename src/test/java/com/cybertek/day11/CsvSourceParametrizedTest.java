package com.cybertek.day11;

import com.cybertek.utilities.ZippopotamTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CsvSourceParametrizedTest extends ZippopotamTestBase {

    @ParameterizedTest
    @CsvSource({"1, 3 , 4",
            "2, 3 , 5",
            "8, 7 , 15",
            "10, 9 , 19"})
    public void testAddition(int num1, int num2, int sum){

        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("sum = " + sum);

        assertThat(num1+num2,equalTo(sum));
    }

    // Write a parameterized test for this request
    // GET https://api.zippopotam.us/us/{state}/{city}
    /*
        "NY, New York",
        "CO, Denver",
        "VA, Fairfax",
        "VA, Arlington",
        "MA, Boston",
        "NY, New York",
        "MD, Annapolis"
     */
    //verify place name contains your city name
//    //print number of places for each request
//     "state": "New York",
//             "state abbreviation": "NY"

    @ParameterizedTest
    @CsvSource({"NY, New York",
            "CO, Denver",
            "VA, Fairfax",
            "VA, Arlington",
            "MA, Boston",
            "NY, New York",
            "MD, Annapolis"})
    public void test1(String state, String city) {
        System.out.println("state = " + state);
        System.out.println("city = " + city);

        int placeNumber = given()
                .accept(ContentType.JSON)
                .and().pathParam("state", state)
                .and().pathParam("city", city)
                .when()
                .get("/us/{state}/{city}")
                .then()
                .statusCode(200)
                .and().body("places.'place name'", everyItem(containsStringIgnoringCase(city)))
                .log().body()
                .extract().jsonPath().getList("places").size();

        System.out.println("placeNumber = " + placeNumber);

    }


}
