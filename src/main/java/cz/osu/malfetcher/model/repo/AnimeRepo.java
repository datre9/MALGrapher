package cz.osu.malfetcher.model.repo;

import cz.osu.malfetcher.model.db.Anime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AnimeRepo extends CrudRepository<Anime, Integer> {
    ArrayList<Anime> getAnimeBySeasonAndYear(String season, int year);
    Anime getAnimeById(int id);
}