package cz.osu.malfetcher.service;

import cz.osu.malfetcher.model.db.Anime;
import cz.osu.malfetcher.model.db.Score;
import cz.osu.malfetcher.model.repo.AnimeRepo;
import cz.osu.malfetcher.model.repo.ScoreRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class GetDataService {
    private final AnimeRepo animeRepo;
    private final ScoreRepo scoreRepo;

    public ResponseEntity<Object> getScoresForAnimeId(int id) {
        List<Score> scores = scoreRepo.findByAnimeId(id);

        if (scores.isEmpty()) {
            return new ResponseEntity<>("Anime with id " + id + " has no scores", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(scores, HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> getPopularAnime(String season, int year) throws IndexOutOfBoundsException {
        List<Anime> anime = animeRepo.getAnimeBySeasonAndYear(season, year);
        List<Integer> animeIds = new ArrayList<>();
        anime.forEach(x -> animeIds.add(x.getId()));
        if (animeIds.isEmpty()) {
            return new ResponseEntity<>("No anime with were found during this season " + season + " " + year, HttpStatus.NOT_FOUND);
        }

        List<Score> scores = new ArrayList<>();
        for (Integer i : animeIds) {
            List<Score> temp = scoreRepo.findByAnimeId(i);
            scores.add(temp.stream().max(Comparator.comparingInt(Score::getId)).get());
        }
        scores.sort(Comparator.comparingInt(Score::getRank));

        List<Anime> topAnime = new ArrayList<>();
        for (Score s : scores) {
            topAnime.add(animeRepo.getAnimeById(s.getAnimeId()));
        }

        return new ResponseEntity<>(topAnime, HttpStatus.OK);
    }
}