package cz.osu.malfetcher.model.repo;

import cz.osu.malfetcher.model.db.Anime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepo extends CrudRepository<Anime, Integer> {
    Anime findByName(String name);
    Anime findById(int id);
}