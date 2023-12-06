package dev.swapi.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static dev.swapi.helpers.Constants.STATUS_CODE_SUCCESS;
import static dev.swapi.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.STATUS;

public class Specs {
    public static RequestSpecification baseRequestSpec = with()
            .log().uri()
            .log().body()
            .filter(withCustomTemplates());

    public static ResponseSpecification filmsResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(STATUS_CODE_SUCCESS)
            .build();
}
