package com.cybertek.homework;

import com.cybertek.pojo.cbTrainingAPI.Students;
import com.cybertek.utilities.CBTraining;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CBTrainingApiPOJO extends CBTraining {

    @DisplayName("Get request for student id - 23401")
    @Test
    public void test01() {

        JsonPath jsonPath = given()
                .pathParam("student_id" , 23401)
                .when()
                .get("/student/{student_id}")
                .then()
                .statusCode(200)
                .and().contentType("application/json;charset=UTF-8")
                .and().header("Content-Encoding", is("gzip"))
                .extract().jsonPath();

        Students student = jsonPath.getObject("students[0]", Students.class);

        Integer batch = 14;
        String name = "Vera";
        String section = "12";
        String email = "aaa@gmail.com";
        String company = "Cybertek";
        String state = "IL";
        int zipcode = 60606;

        assertThat(student.getFirstName(), is(name));
        assertThat(student.getBatch(), is(14));
        assertThat(student.getSection(), is(section));
        assertThat(student.getContact().getEmailAddress(), is(email));
        assertThat(student.getCompany().getCompanyName(), is(company));
        assertThat(student.getCompany().getAddress().getState(), is(state));
        assertThat(student.getCompany().getAddress().getZipCode(), is(zipcode));

    }

}
