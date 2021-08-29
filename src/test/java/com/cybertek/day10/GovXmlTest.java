package com.cybertek.day10;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class GovXmlTest {

    @Test
    public void test1() {
        Response response = get("https://data.ct.gov/api/views/qm34-pq7e/rows.xml")
                .then().log().all()
                .statusCode(200)
                .extract().response();

        XmlPath xmlPath = response.xmlPath();

        List<Integer> yearsList = xmlPath.getList("response.row.row.year");
        System.out.println("yearsList = " + yearsList);

        List<Integer> unknownList = xmlPath.getList("response.row.row.unknown");
        System.out.println("unknownList = " + unknownList);

        int other2005 = xmlPath.getInt("response.row.row[2].other");
        System.out.println("other2005 = " + other2005);

        String address2007 = xmlPath.getString("response.row.row[2].@_address");
        System.out.println("address2007 = " + address2007);


    }

}
