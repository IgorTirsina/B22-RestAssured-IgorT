package com.cybertek.day06;

import com.cybertek.pojo.Search;
import com.cybertek.pojo.Spartan;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SpartanPojoGetRequestTest extends SpartanTestBase {

    @DisplayName("Get one spartan and convert it to Spartan Object")
    @Test
    public void oneSpartanPojo() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .log().all()
                .extract().response();

        //deserialize from JSON to POJO
        Spartan spartan15 = response.as(Spartan.class);

        System.out.println(spartan15);

        System.out.println("spartan15.getId() = " + spartan15.getId());
        System.out.println("spartan15.getName() = " + spartan15.getName());
        System.out.println("spartan15.getGender() = " + spartan15.getGender());
        System.out.println("spartan15.getPhone() = " + spartan15.getPhone());

        //deserialize to custom class using JsonPath
        JsonPath jsonPath = response.jsonPath();
        //we need to add the path as a string
        Spartan sp15 = jsonPath.getObject("" , Spartan.class);
        System.out.println("sp15"+sp15);

    }

    @DisplayName("Get one spartan from search endpoint result and use POJO")
    @Test
    public void spartanSearchWithPojo() {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "a")
                .and().queryParam("gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .log().all()
                .extract().jsonPath();

        Spartan sp1 = jsonPath.getObject("content[0]", Spartan.class);

        System.out.println("sp1 = " + sp1);
        System.out.println("sp1.getName() = " + sp1.getName());
        System.out.println("sp1.getGender() = " + sp1.getGender());


    }

    @Test
    public void test03() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "a")
                .and().queryParam("gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();

        Search searchResult = response.as(Search.class);

        System.out.println(searchResult.getTotalElement());

        System.out.println(searchResult.getContent().get(0).getName());

    }
    @DisplayName("GET  /spartans/search and save as List<Spartan>")
    @Test
    public void test04() {

        List<Spartan> spartanList = given().accept(ContentType.JSON)
                        .and().queryParam("nameContains", "a")
                        .and().queryParam("gender", "Male")
                        .when().get("/api/spartans/search")
                        .then().statusCode(200)
                        .extract().jsonPath().getList("content" , Spartan.class);

        System.out.println(spartanList);

    }

}
