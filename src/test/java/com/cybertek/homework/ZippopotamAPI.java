package com.cybertek.homework;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import com.cybertek.utilities.ZippopotamTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ZippopotamAPI extends ZippopotamTestBase {
    /**
     * Given Accept application/json
     * And path zipcode is 22031
     * When I send a GET request to /us endpoint
     * Then status code must be 200
     * And content type must be application/json
     * And Server header is cloudflare
     * And Report-To header exists
     * And body should contains following information
     *     post code is 22031
     *     country  is United States
     *     country abbreviation is US
     *     place name is Fairfax
     *     state is Virginia
     *     latitude is 38.8604
     */

    @DisplayName("GET request from Zippopotam fro US/22031")
    @Test
    public void testQ1() {
        Response response = given().accept(ContentType.JSON)
                            .when().get("/US/22031");

        assertEquals(200 , response.statusCode());
        assertEquals("application/json" , response.contentType());
        String server = response.header("server");
        assertEquals("cloudflare",server);
        String header = response.header("Report-To");
        assertFalse(header.isEmpty());

        JsonPath jsonPath = response.jsonPath();
        assertEquals(22031 , jsonPath.getInt("'post code'"));
        assertEquals("United States" , jsonPath.getString("country"));
        assertEquals("US" , jsonPath.getString("'country abbreviation'"));
        assertEquals("Fairfax" , jsonPath.getString("places[0].'place name'"));
        assertEquals("Virginia" , jsonPath.getString("places[0].state"));
        assertEquals("38.8604" , jsonPath.getString("places[0].latitude"));
    }
    /**
     * Given Accept application/json
     * And path zipcode is 50000
     * When I send a GET request to /us endpoint
     * Then status code must be 404
     * And content type must be application/json
     */
    @DisplayName("GET request from Zippopotam for US/50000")
    @Test
    public void testQ2() {
        Response response = given().accept(ContentType.JSON)
                            .when().get("/US/50000");

        assertEquals(404 , response.statusCode());
        assertEquals("application/json" , response.contentType());
    }
    /**
     * Given Accept application/json
     * And path state is va
     * And path city is fairfax
     * When I send a GET request to /us endpoint
     * Then status code must be 200
     * And content type must be application/json
     * And payload should contains following information
     *     country abbreviation is US
     *     country  United States
     *     place name  Fairfax
     *     each places must contains fairfax as a value
     *     each post code must start with 22
     */
    @DisplayName("GET request from Zippopotam for /US/VA/Fairfax")
    @Test
    public void testQ3() {
        Response response = given().accept(ContentType.JSON)
                            .and().pathParam("state" , "va")
                            .and().pathParam("city" , "fairfax")
                            .when().get("/us/{state}/{city}");

        assertEquals(200 , response.statusCode());
        assertEquals("application/json" , response.contentType());

        JsonPath jsonPath = response.jsonPath();
        assertEquals("US" , jsonPath.getString("'country abbreviation'"));
        assertEquals("United States" , jsonPath.getString("country"));
        assertEquals("Fairfax" , jsonPath.getString("'place name'"));
        List<String> localPostCodes = jsonPath.getList("places.'post code'");
        for(String eachPostCode : localPostCodes) {
            assertTrue(eachPostCode.startsWith("22"));
        }
    }
}
