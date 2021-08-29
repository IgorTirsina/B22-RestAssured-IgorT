package com.cybertek.day10;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SSLTest {

    @Test
    public void test1() {

        given().relaxedHTTPSValidation()
        .when().get("http://ergast.com/api/f1/drivers/alonso")
        .prettyPrint();

    }

    @Test
    public void keyStore() {
        given().keyStore("pathtofile", "password")
                .when().get("zippopotamAPIurl");

    }


}
