package com.cybertek.day07;

import com.cybertek.pojo.Spartan;
import com.cybertek.utilities.SpartanTestBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SpartanPostRequestDemo extends SpartanTestBase {

        /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Severus",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */
    @DisplayName("Post request to spartans")
    @Test
    public void postReqToSpartan() {

        String requestJsonBody = "{\n" +
                "      \"gender\":\"Male\",\n" +
                "      \"name\":\"Spartacus\",\n" +
                "      \"phone\":8877445996\n" +
                "   }";

        Response postResponse = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestJsonBody)  //here will be our post body
                .when()
                .post("/api/spartans");

        assertThat(postResponse.statusCode(), is(201));
        assertThat(postResponse.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";

        assertThat(postResponse.path("success"), is(expectedResponseMessage));

        assertThat(postResponse.path("data.name"),is("Spartacus"));
        assertThat(postResponse.path("data.gender"),is("Male"));
        assertThat(postResponse.path("data.phone"),is(8877445996L));


    }

    @DisplayName("Post request with a map")
    @Test
    public void postReqToSpartan01() {

        Map<String, Object> requestJsonMap = new HashMap<>();
        requestJsonMap.put("name", "SpartacusJr");
        requestJsonMap.put("gender", "Male");
        requestJsonMap.put("phone", "8877445997");

        Response postResponse = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestJsonMap).log().all()  //here will be our post body
                .when()
                .post("/api/spartans");

//        ObjectMapper objectMapper = new ObjectMapper();

        assertThat(postResponse.statusCode(), is(201));
        assertThat(postResponse.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";

        assertThat(postResponse.path("success"), is(expectedResponseMessage));
        assertThat(postResponse.path("data.name"),is("SpartacusJr"));
        assertThat(postResponse.path("data.gender"),is("Male"));
        assertThat(postResponse.path("data.phone"),is(8877445997L));

        postResponse.prettyPrint();

    }

    @DisplayName("Post request with a POJO")
    @Test
    public void postReqToSpartan02() {

        Map<String, Object> requestJsonMap = new HashMap<>();
        requestJsonMap.put("name", "SpartacusJr");
        requestJsonMap.put("gender", "Male");
        requestJsonMap.put("phone", 8877445997l);

        Spartan spartan = new Spartan();
        spartan.setName(requestJsonMap.get("name").toString());
        spartan.setGender(requestJsonMap.get("gender").toString());
        spartan.setPhone((long)requestJsonMap.get("phone"));

//        System.out.println("spartan = " + spartan);

        Response postResponse = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(spartan).log().all()  //here will be our post body
                .when()
                .post("/api/spartans");

//        ObjectMapper objectMapper = new ObjectMapper();

        assertThat(postResponse.statusCode(), is(201));
        assertThat(postResponse.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";

        assertThat(postResponse.path("success"), is(expectedResponseMessage));
        assertThat(postResponse.path("data.name"),is("SpartacusJr"));
        assertThat(postResponse.path("data.gender"),is("Male"));
        assertThat(postResponse.path("data.phone"),is(8877445997L));

        postResponse.prettyPrint();

    }

    @DisplayName("Post request with a POJO using Hamcrest")
    @Test
    public void postReqToSpartan03() {
        /**
         * //Create one SpartanUtil class
         *     //create a static method that returns Map<String,Object>
         *     //use faker library(add as a dependency) to assign each time different information
         *     //for name,gender,phone number
         *     //then use your method for creating spartan as a map,dynamically.
         */
        Map<String, Object> requestJsonMap = new HashMap<>();
        requestJsonMap.put("name", "SpartacusJr");
        requestJsonMap.put("gender", "Male");
        requestJsonMap.put("phone", 8877445997l);

        Spartan spartan = new Spartan();
        spartan.setName(requestJsonMap.get("name").toString());
        spartan.setGender(requestJsonMap.get("gender").toString());
        spartan.setPhone((long)requestJsonMap.get("phone"));

        String expectedResponseMessage = "A Spartan is Born!";

        int idFromPost = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(spartan).log().all()  //here will be our post body
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .and().contentType("application/json")
                .and().body("success", is(expectedResponseMessage))
                .extract().jsonPath().getInt("data.id");

        System.out.println("idFromPost = " + idFromPost);

        //send a get request to id
        Spartan spartanPost = given().accept(ContentType.JSON)
                .and().pathParam("id", idFromPost)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(200).log().all()
                .extract().as(Spartan.class);

        assertThat(spartanPost.getName(), is(spartan.getName()));
        assertThat(spartanPost.getGender(), is(spartan.getGender()));
        assertThat(spartanPost.getPhone(), is(spartan.getPhone()));



    }

}
