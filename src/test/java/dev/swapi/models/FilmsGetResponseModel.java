package dev.swapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmsGetResponseModel {
    Integer count;
    ArrayList<FilmModel> results;
}
