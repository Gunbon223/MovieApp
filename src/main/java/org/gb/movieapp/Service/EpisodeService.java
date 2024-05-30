package org.gb.movieapp.Service;

import org.gb.movieapp.Model.Request.UpsertEpisodeRequest;
import org.gb.movieapp.Model.Request.UpsertFullEpisodeRequest;
import org.gb.movieapp.entites.Episodes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EpisodeService {
    List<Episodes> episodesByMovieId(int movieId);

    List<Episodes> getEpisodesByMovieId(int id);
    Episodes getEpisodeById(int id);

    Episodes createEpisode(UpsertEpisodeRequest request, int movieId);

    Episodes updateEpisode(UpsertFullEpisodeRequest request, int episodeId);

    void deleteEpisode(int episodeId);

    String uploadVideo(int episodeId, MultipartFile file);

    Episodes getEpisode(Integer movieId, String tap);
}
