package com.cybertek.homework;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDShw1 extends HRTestBase {

    /**
     * - Given accept type is Json
     * - Path param value- country_id US
     * - When users sends request to /countries
     * - Then status code is 200
     * - And Content - Type is Json
     * - And country_id is US
     * - And Country_name is United States of America
     * - And Region_id is
     */
    @DisplayName("GET request from Countries for value US")
    @Test
    public void testQ1() {
        Response response = given().accept(ContentType.JSON)
                            .and().queryParam("q", "{\"country_id\":\"US\"}")
                            .when().get("/countries");

        assertEquals(200 , response.statusCode());
        assertEquals("application/json" , response.contentType());
        JsonPath jsonPath = response.jsonPath();
        String actualCountryId = jsonPath.getString("items[0].country_id");
        assertEquals("US" , actualCountryId);
        String actualCountryName = jsonPath.getString("items[0].country_name");
        assertEquals("United States of America" , actualCountryName);
        Integer actualRegionId = jsonPath.getInt("items[0].region_id");
        assertEquals(2 , actualRegionId);
    }



}
