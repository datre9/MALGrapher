package cz.osu.malfetcher.controller;

import cz.osu.malfetcher.service.FetchDataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
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
            fetchDataService.getData(new URI("https://api.jikan.moe/v4/seasons/2024/spring"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}