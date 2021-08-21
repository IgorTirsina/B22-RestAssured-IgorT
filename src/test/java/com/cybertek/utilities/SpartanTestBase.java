package com.cybertek.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.< 54.92.248.102 >
        baseURI = ConfigReader.get("spartanAPIurl");

        String dbUrl = ConfigReader.get("dbUrl");
        String dbUserName = "Sp";
        String dbPassword = "SP";

        DBUtils.createConnection(dbUrl, dbUserName, dbPassword);
    }

}
