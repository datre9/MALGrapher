package cz.osu.malfetcher.controller;

import cz.osu.malfetcher.service.FetchDataService;
import cz.osu.malfetcher.service.GetDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@CrossOrigin()
public class AnimeController {
    private final FetchDataService fetchDataService;
    private final GetDataService getDataService;

    public AnimeController(FetchDataService fetchDataService, GetDataService getDataService) {
        this.fetchDataService = fetchDataService;
        this.getDataService = getDataService;
    }

    @GetMapping("/fetch")
    public void fetchAnime() {
        try {
            fetchDataService.getData();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get/scores")
    public ResponseEntity<Object> getScoresForAnimeId(@RequestParam int id) {
        return getDataService.getScoresForAnimeId(id);
    }

    @GetMapping("/get/popular")
    public ResponseEntity<Object> getPopularAnime(@RequestParam String season, @RequestParam int year) {
        try {
            return getDataService.getPopularAnime(season, year);
        } catch (IndexOutOfBoundsException _) {
            return new ResponseEntity<>("This page contains no further anime", HttpStatus.NOT_FOUND);
        }
    }
}