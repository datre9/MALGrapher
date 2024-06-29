package cz.osu.malfetcher.model.repo;

import cz.osu.malfetcher.model.db.Score;
import cz.osu.malfetcher.model.db.ScoreComposite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepo extends CrudRepository<Score, ScoreComposite> {
}