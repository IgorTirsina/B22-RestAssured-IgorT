package com.cybertek.day06;

import com.cybertek.pojo.Region;
import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

}
