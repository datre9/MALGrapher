package cz.osu.malfetcher.model.repo;

import cz.osu.malfetcher.model.db.Score;
import cz.osu.malfetcher.model.db.ScoreComposite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public interface ScoreRepo extends CrudRepository<Score, ScoreComposite> {
    boolean existsByAnimeIdAndDate(int animeId, LocalDate date);
    ArrayList<Score> findByAnimeId(int animeId);
}