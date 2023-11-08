package com.example;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.Header;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class TagFilesControllerTest {

    @Test
    public void uploadTagFile_UploadResourceFile_UploadSuccessful() {
        //Setup
        File file = ResourceUtils.getResourceAsFile("sampledata/tag/unitdata.tag");
        Assertions.assertNotNull(file);

        //Action + Assert
        given().header(new Header("content-type", "multipart/form-data"))
                .multiPart("tagFile", file)
                .contentType("multipart/form-data")
                .when()
                .put("/api/tag/upload")
                .then()
                .statusCode(204);
    }

}
