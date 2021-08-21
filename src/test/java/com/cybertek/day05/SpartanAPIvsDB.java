package com.cybertek.day05;

import com.cybertek.utilities.ConfigReader;
import com.cybertek.utilities.DBUtils;
import com.cybertek.utilities.Driver;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class SpartanAPIvsDB extends SpartanTestBase {

    WebDriver driver = Driver.getDriver();

    @DisplayName("Get selected Spartan Map")
    @Test
    public void testDB1() {
        //1.get info from db
        String query = "SELECT SPARTAN_ID, NAME, GENDER, PHONE FROM SPARTANS WHERE SPARTAN_ID=15";

        Map<String, Object> dbMap = DBUtils.getRowMap(query);
        System.out.println("dbMap = " + dbMap);

        //2.get info from api
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and().contentType("application/json")
                .extract().response();

        //Deserialization here JSon to Java  with Jackson objectMapper
        Map<String,Object> apiMap= response.as(Map.class);
        System.out.println("apiMap = " + apiMap);

        //3.compare database vs api --> we assume expected result is db
        assertThat(apiMap.get("id").toString(),is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("name"),is(dbMap.get("NAME")));
        assertThat(apiMap.get("gender"),is(dbMap.get("GENDER")));
        assertThat(apiMap.get("phone").toString(),is(dbMap.get("PHONE").toString()));

        //4. create the map for Spartan 15, from the UI
        Map<String, Object> uiMap = getUIMapForSpartan("15");
        System.out.println("uiMap = " + uiMap);

        //5. compare database vs ui
        assertThat(uiMap.get("ID"), is(apiMap.get("id").toString()));
        assertThat(uiMap.get("Name"), is(apiMap.get("name")));
        assertThat(uiMap.get("Gender"), is(apiMap.get("gender")));
        assertThat(uiMap.get("Phone"), is(apiMap.get("phone").toString()));

        //6. compare ui to api
        assertThat(uiMap.get("ID"), is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(uiMap.get("Name"), is(dbMap.get("NAME")));
        assertThat(uiMap.get("Gender"), is(dbMap.get("GENDER")));
        assertThat(uiMap.get("Phone"), is(dbMap.get("PHONE")));

    }

    public Map<String, Object> getUIMapForSpartan(String id) {

        driver.get(ConfigReader.get("spartanAPIurl"));
        WebDriverWait wait = new WebDriverWait(driver, 10);

        //get into the WebData
        WebElement webData = driver.findElement(By.xpath("//a[@href='/spartans']"));
        webData.click();

        WebElement webTable = driver.findElement(By.xpath("//table[@id='myTable']"));
        wait.until(ExpectedConditions.visibilityOf(webTable));
        List<WebElement> webTableHeaders = driver.findElements(By.xpath("//table[@id='myTable']/thead/tr/th"));

        List<WebElement> webTableDataForSpartan = driver.findElements(
                By.xpath("//table[@id='myTable']/tbody/tr//*[.='"+id+"']/../td"));

        Map<String, Object> myMapForSpartan = new HashMap<>();

        //we dont need more then 4 columns, just {id, name, gender, phone}
        for(int i = 0; i < 4; i++) {
            myMapForSpartan.put(webTableHeaders.get(i).getText().trim(),
                    webTableDataForSpartan.get(i).getText().trim());
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@id='myTable']/tbody/tr[10]/td[1]"))));
        }

        Driver.getDriver().close();
        return myMapForSpartan;
    }

}
