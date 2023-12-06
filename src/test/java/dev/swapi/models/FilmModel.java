package dev.swapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmModel {
    String title;

    @JsonProperty("episode_id")
    Integer episodeId;
    @JsonProperty("release_date")
    String releaseDate;

    ArrayList<String> characters;
}
