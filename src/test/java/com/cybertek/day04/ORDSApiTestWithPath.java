package com.cybertek.day04;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to coutries with Path method")
    @Test
    public void test1() {
        Response response = given().log().all()
                                    .accept(ContentType.JSON)
                                    .queryParam("q" , "{\"region_id\":2}")
                            .when()
                                    .get("/countries");

        assertEquals(200, response.statusCode());

        int limit = response.path("count");
        System.out.println("limit = " + limit);


//        List<String> listOFCountryID = new ArrayList<>();
        //return type will be arrayList, so no need for the loop
        List<String> listOFCountryID = response.path("items.country_id");
//        for(int i = 0; i < limit; i++) {
//            listOFCountryID.add(response.path("items["+i+"].country_id"));
//        }

        System.out.println("listOFCountryID = " + listOFCountryID);

        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        String firstCountryId = response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        String secondCountryId = response.path("items[1].country_id");
        System.out.println("secondCountryId = " + secondCountryId);

        List<Integer> allRegionsIDs = response.path("items.region_id");
        for(Integer regionID : allRegionsIDs) {
            System.out.println("regionID = " + regionID);
            assertEquals(2, regionID);
        }


    }
    @DisplayName("GET request /employees/ who works in IT_PROG")
    @Test
    public void test2(){
        Response response= given().log().all()
                .accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

        //make sure we have only IT_PROG as a job_id
        List<String> allJobIDs = response.path("items.job_id");

        for(String jobID : allJobIDs) {
            assertEquals("IT_PROG" , jobID);
        }


    }

    //TODO : print each name of IT_PROG

}
