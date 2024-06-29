package cz.osu.malfetcher.controller;

import cz.osu.malfetcher.service.FetchDataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class AnimeController {
    private final FetchDataService fetchDataService;

    public AnimeController(FetchDataService fetchDataService) {
        this.fetchDataService = fetchDataService;
    }

    @GetMapping("/fetch")
    public void fetchAnime() {
        fetchDataService.getData();
    }
}