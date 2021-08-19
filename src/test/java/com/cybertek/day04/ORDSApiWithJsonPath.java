package com.cybertek.day04;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import com.cybertek.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ORDSApiWithJsonPath  extends HRTestBase {

    @DisplayName("GET request to Countries")
    @Test
    public void test1() {
        Response response = get("/countries");

        //get the second country name with jsonPath
        //short cut ALT+ENTER
        JsonPath jsonPath = response.jsonPath();
        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        List<String> allCountriesIDs = jsonPath.getList("items.country_id");
        System.out.println("allCountriesIDs = " + allCountriesIDs);

        //get all country names where their region id is equal to 2
        List<String> countryNameWithRegionId2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println(countryNameWithRegionId2);

    }

    @DisplayName("GET")
    @Test
    public void test2() {
        //we added limit query param to get 107 employees
        Response response = given().queryParam("limit" , 107).when().get("/employees");
        JsonPath jsonPath = response.jsonPath();

        //get all email of employees who is working as IT_PROG
        List<String> employeeITProgs = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println(employeeITProgs);

        //get first name of employees who is making more then 10000
        List<String> employeeNameSalary = jsonPath.get("items.findAll {it.salary>=10000}.first_name");
        System.out.println(employeeNameSalary);

        String kingFirstName = jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println("kingFirstName = " + kingFirstName);




    }

}
