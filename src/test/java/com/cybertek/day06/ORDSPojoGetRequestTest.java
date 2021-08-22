package com.cybertek.day06;

import com.cybertek.pojo.Employee;
import com.cybertek.pojo.Region;
import com.cybertek.pojo.Regions;
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
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ORDSPojoGetRequestTest extends HRTestBase {

    @DisplayName("Get request to regions and convert to POJO")
    @Test
    public void test01() {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                            .and().queryParam("q", "{\"region_id\":1}")
                            .when().get("/regions")
                            .then()
                            .extract().jsonPath();

        Region rg1 = jsonPath.getObject("items[0]" , Region.class);

        System.out.println("rg1 = " + rg1);
    }

    @DisplayName("Get request to regions")
    @Test
    public void regionTest() {

        JsonPath jsonPath = get("/regions").then().statusCode(200).log().body().extract().jsonPath();
        //if we include .log() , it will return the same value as .prettyPrint()
        jsonPath.prettyPrint();


    }

    @DisplayName("Get request from Employees for 4 values only")
    @Test
    public void employeeTest() {
        Employee emp01 = get("/employees").then().statusCode(200).extract().jsonPath().getObject("items[0]" , Employee.class);

        System.out.println(emp01);


    }

    /* send a get request to regions
       verify that region ids are 1,2,3,4
       verify that regions names Europe ,Americas , Asia, Middle East and Africa
       verify that count is 4
       try to use pojo as much as possible
       ignore non used fields

    */
    @DisplayName("Get request tot regions and verify some fields")
    @Test
    public void testRegionsIdNameCount() {
        //created Regions class
        Regions regions = get("/regions").then().statusCode(200).and().extract().response().as(Regions.class);

        assertThat(regions.getCount(), is(4));

        List<Integer> actualRegionId = new ArrayList<>();
        List<String> actualRegionName = new ArrayList<>();

        for(Region each : regions.getItems()) {
            actualRegionId.add(each.getRegionId());
            actualRegionName.add(each.getRegion_name());
        }

        List<Integer> expectedRegionId = Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");

        assertThat(actualRegionId, is(equalTo(expectedRegionId)));
        assertThat(actualRegionName, is(equalTo(expectedRegionNames)));


    }

}
