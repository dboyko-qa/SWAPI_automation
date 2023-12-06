package dev.swapi.api.films;

import dev.swapi.models.PersonGetResponseModel;
import dev.swapi.models.PersonModel;

import java.util.ArrayList;

import static dev.swapi.helpers.Constants.API_PEOPLE;
import static dev.swapi.specs.Specs.baseRequestSpec;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonsApi {

    public static ArrayList<PersonModel> getAllPersons(){
        ArrayList<PersonModel> allPersons = new ArrayList<>();
        String next = API_PEOPLE;
        Integer totalCount = -1;

        while (next != null){
            PersonGetResponseModel response = given()
                    .spec(baseRequestSpec)
                    .when()
                    .get(next)
                    .then()
                    .extract().as(PersonGetResponseModel.class);

            if (totalCount != -1) assertEquals(totalCount, response.getCount());
            totalCount = response.getCount();

            next = response.getNext();
            allPersons.addAll(response.getResults());
        }

        assertEquals(allPersons.size(), totalCount);
        return allPersons;
    }
}
