package com.cybertek.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.baseURI;

public abstract class CBTraining {

    @BeforeAll
    public void init() {
        baseURI = ConfigReader.get("CBTrainingAPI");
    }

    @AfterAll
    public static void closeDB() {
        DBUtils.destroy();
    }


}
