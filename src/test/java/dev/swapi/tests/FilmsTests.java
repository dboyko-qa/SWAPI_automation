package dev.swapi.tests;

import dev.swapi.models.FilmModel;
import dev.swapi.models.PersonModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static dev.swapi.api.films.FilmsApi.findFilmWithLatestReleaseDate;
import static dev.swapi.api.films.PersonsApi.getAllPersons;
import static dev.swapi.helpers.Constants.*;
import static dev.swapi.helpers.Utils.*;
import static dev.swapi.specs.Specs.baseRequestSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FilmsTests {
    @BeforeEach
    public void beforeEach() {
        baseURI = BASE_URL;
    }

    @DisplayName("Find the film with latest release date.")
    @Test
    public void filmWithLatestReleaseDateTest(){
        FilmModel latestFilm = step("Call films API and find film with latest release date",
                () ->  findFilmWithLatestReleaseDate());
        step("Verify film exists", () -> assertNotNull(latestFilm));
        System.out.println("Film with latest release date: " + latestFilm.getTitle());
    }

    @DisplayName("Find the tallest person among the characters that were part of latest released film.")
    @Test
    public void findTallestPersonInLatestReleasedFilm(){
        FilmModel latestFilm = step("Call films API and find film with latest release date",
                () ->  findFilmWithLatestReleaseDate());
        step("Verify film exists", () -> assertNotNull(latestFilm));

        ArrayList<PersonModel> persons = new ArrayList<>();
        for (String personLink: latestFilm.getCharacters()){
            PersonModel person = step("Request for person info", () -> given()
                    .spec(baseRequestSpec)
                    .when()
                    .get(personLink)
                    .then()
                    .extract().as(PersonModel.class)
            );

            if (person != null) persons.add(person);

        }
        PersonModel highestPerson = Collections.max(persons, Comparator.comparing(f -> tryParseOrZero(f.getHeight())));
        System.out.println(highestPerson.getUrl() + ": " + highestPerson.getName());
    }


    @DisplayName("Find the tallest person ever played in any Star Wars film.")
    @Test
    public void findTallestPerson(){
        ArrayList<PersonModel> allPersons = step("Request for all people", () -> getAllPersons());
        PersonModel highestPerson = Collections.max(allPersons, Comparator.comparing(f -> tryParseOrZero(f.getHeight())));
        System.out.println(highestPerson.getUrl() + ": " + highestPerson.getName());
    }

    @DisplayName("Create contract (Json schema validation) test for /people API.")
    @Test
    public void peopleContractTest() {
        Path path = Paths.get(SCHEMES_DIR, PEOPLE_SCHEME_JSON);
        step("Validate JSON schema for people API", () -> given()
                .spec(baseRequestSpec)
                .when()
                .get(API_PEOPLE)
                .then()
                .body(matchesJsonSchemaInClasspath(path.toString()))
        );
    }
}
