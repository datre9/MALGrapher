package cz.osu.malfetcher.controller;

import cz.osu.malfetcher.service.FetchDataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@CrossOrigin()
public class AnimeController {
    private final FetchDataService fetchDataService;

    public AnimeController(FetchDataService fetchDataService) {
        this.fetchDataService = fetchDataService;
    }

    @GetMapping("/fetch")
    public void fetchAnime() {
        try {
            fetchDataService.getData();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}