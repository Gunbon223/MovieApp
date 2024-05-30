package org.gb.movieapp.Rest;

import org.gb.movieapp.Model.Request.UpsertEpisodeRequest;
import org.gb.movieapp.Model.Request.UpsertFullEpisodeRequest;
import org.gb.movieapp.Service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/episode")
public class EpisodeApi {
    @Autowired
    EpisodeService episodeService;

    @PostMapping("/{movieId}")
    public ResponseEntity<?> createEpisode(@RequestBody UpsertEpisodeRequest request, @PathVariable int movieId) {
        episodeService.createEpisode(request, movieId);
        return new ResponseEntity<>(request, HttpStatus.CREATED); //tra ve 201
    }

    @PutMapping("/{episodeId}")
    public ResponseEntity<?> updateEpisode(@RequestBody UpsertFullEpisodeRequest request, @PathVariable int episodeId) {
        episodeService.updateEpisode(request, episodeId);
        return new ResponseEntity<>(request, HttpStatus.OK); //tra ve 200
    }

    @DeleteMapping("/{episodeId}")
    public ResponseEntity<?> deleteEpisode(@PathVariable int episodeId) {
        episodeService.deleteEpisode(episodeId);
        return new ResponseEntity<>(HttpStatus.OK); //tra ve 200
    }

    @PostMapping("/{episodeId}/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("video") MultipartFile video, @PathVariable int episodeId) {
        String url = episodeService.uploadVideo(episodeId,video);
        return new ResponseEntity<>(url, HttpStatus.OK); //tra ve 200
    }


}
