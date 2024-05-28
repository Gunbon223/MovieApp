package org.gb.movieapp.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UpsertMovieRequest {
    String name;
    String description;
    Integer releaseYear;
    String type;
    Boolean status;
    String trailer;
    Integer countryId;
    Integer[] genres;
    Integer[] actors;
    Integer[] directors;
}
