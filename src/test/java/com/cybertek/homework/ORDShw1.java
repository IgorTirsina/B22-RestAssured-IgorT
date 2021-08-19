package com.cybertek.homework;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    /**
     * - Given accept type is Json
     * - Query param value - q={"department_id":80}
     * - When users sends request to /employees
     * - Then status code is 200
     * - And Content - Type is Json
     * - And all job_ids start with 'SA'
     * - And all department_ids are 80
     * - Count is 25
     */
    @DisplayName("GET request from employees for department 80")
    @Test
    public void testQ2() {
        Response response = given().accept(ContentType.JSON)
                            .and().queryParam("q", "{\"department_id\":80}")
                            .when().get("/employees");

        assertEquals(200 , response.statusCode());
        assertEquals("application/json" , response.contentType());

        JsonPath jsonPath = response.jsonPath();
        List<String> jobIDs = jsonPath.getList("items.job_id");
        for(String eachID : jobIDs) {
            assertTrue(eachID.startsWith("SA"));
        }

        List<Integer> departmentsIDs = jsonPath.getList("items.department_id");
        for (Integer eachID : departmentsIDs) {
            assertEquals(80 , eachID);
        }
        Integer actualCount = jsonPath.getInt("count");
        assertEquals(25 , actualCount);


    }
    /**
     * - Given accept type is Json
     * -Query param value q= region_id 3
     * - When users sends request to /countries
     * - Then status code is 200
     * - And all regions_id is 3
     * - And count is 6
     * - And hasMore is false
     * - And Country_name are;
     * Australia,China,India,Japan,Malaysia,Singapore
     */
    @DisplayName("GET request from Countries from Region 3")
    @Test
    public void testQ3() {
        Response response = given().accept(ContentType.JSON)
                            .and().queryParam("q" , "{\"region_id\":3}")
                            .when().get("/countries");

        assertEquals(200 , response.statusCode());
        assertEquals("application/json" , response.contentType());

        JsonPath jsonPath = response.jsonPath();
        List<Integer> regionIDs = jsonPath.getList("items.region_id");
        for(Integer eachID : regionIDs) {
            assertEquals(3 , eachID);
        }

        assertEquals(6 , jsonPath.getInt("count"));

        assertFalse(jsonPath.getBoolean("hasMore"));

        List<String> expectedCountriesNames = new ArrayList<>(Arrays.asList("Australia","China","India","Japan","Malaysia","Singapore"));
        List<String> actualCountriesNames = jsonPath.getList("items.country_name");
        System.out.println("actualCountriesNames = " + actualCountriesNames);

        assertEquals(expectedCountriesNames , actualCountriesNames);






    }




}
