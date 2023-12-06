package dev.swapi.api.films;

import dev.swapi.models.FilmModel;
import dev.swapi.models.FilmsGetResponseModel;

import java.util.Collections;
import java.util.Comparator;

import static dev.swapi.helpers.Constants.API_FILMS;
import static dev.swapi.specs.Specs.baseRequestSpec;
import static dev.swapi.specs.Specs.filmsResponseSpec;
import static io.restassured.RestAssured.given;

public class FilmsApi {

    public static FilmModel findFilmWithLatestReleaseDate(){
        FilmsGetResponseModel films = given()
                .spec(baseRequestSpec)
                .when()
                .get(API_FILMS)
                .then()
                .spec(filmsResponseSpec)
                .extract().as(FilmsGetResponseModel.class);

        return Collections.max(films.getResults(), Comparator.comparing(f -> f.getReleaseDate()));
    }
}
