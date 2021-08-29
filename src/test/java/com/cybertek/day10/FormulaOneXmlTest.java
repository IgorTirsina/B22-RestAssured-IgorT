package com.cybertek.day10;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class FormulaOneXmlTest {

    @BeforeAll
    public static void setUp() {

        baseURI = "http://ergast.com";
        basePath = "/api/f1";

    }

    @Test
    public void tes1() {
        Response response = given().pathParam("driver", "alonso")
                .when().get("/drivers/{driver}")
                .then().statusCode(200).log().all()
                .extract().response();

        XmlPath xmlPath = response.xmlPath();

        String givenName = xmlPath.getString("MRData.DriverTable.Driver.GivenName");
        String familyName = xmlPath.getString("MRData.DriverTable.Driver.FamilyName");

        System.out.println("Full name = " + givenName +" "+ familyName);
        //to get an attribute from the xml file, we will use the '@'
        String driverId = xmlPath.getString("MRData.DriverTable.Driver.@driverId");
        String driverCode = xmlPath.getString("MRData.DriverTable.Driver.@code");
        String driverUrl = xmlPath.getString("MRData.DriverTable.Driver.@url");

        System.out.println("driverId = " + driverId);
        System.out.println("driverCode = " + driverCode);
        System.out.println("driverUrl = " + driverUrl);

    }

}
