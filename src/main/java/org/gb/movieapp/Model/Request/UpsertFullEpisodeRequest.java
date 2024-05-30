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
public class UpsertFullEpisodeRequest {
    String name;
    int order;
    String video_url;
    int duration;

}
