package cz.osu.malfetcher.service;

import cz.osu.malfetcher.model.db.Score;
import cz.osu.malfetcher.model.repo.ScoreRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class GetDataService {
    private final ScoreRepo scoreRepo;

    public ResponseEntity<Object> getScoresForAnimeId(int id) {
        List<Score> scores = scoreRepo.findByAnimeId(id);

        if (scores.isEmpty()) {
            return new ResponseEntity<>("Anime with id " + id + " has no scores", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(scores, HttpStatus.OK);
        }
    }
}