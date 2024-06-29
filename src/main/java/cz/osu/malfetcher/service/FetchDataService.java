package cz.osu.malfetcher.service;

import com.google.gson.Gson;
import cz.osu.malfetcher.model.Season;
import cz.osu.malfetcher.model.db.Anime;
import cz.osu.malfetcher.model.db.Score;
import cz.osu.malfetcher.model.json.AnimeData;
import cz.osu.malfetcher.model.json.AnimeResponse;
import cz.osu.malfetcher.model.repo.AnimeRepo;
import cz.osu.malfetcher.model.repo.ScoreRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class FetchDataService {
    private final AnimeRepo animeRepo;
    private final ScoreRepo scoreRepo;

    private ArrayList<URI> createURIs() {
        ArrayList<Season> seasons = Season.getLastYearOfSeasons();
        ArrayList<URI> uris = new ArrayList<>();

        for (Season season : seasons) {
            try {
                uris.add(new URI("https://api.jikan.moe/v4/seasons/" + season.getYear() + "/" + season.getSeason()));
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        return uris;
    }

    public void getData() {
        HttpClient httpClient = HttpClient.newHttpClient();
        ArrayList<URI> uris = createURIs();

        for (URI uri : uris) {
            HttpRequest getRequest = HttpRequest.newBuilder(uri).build();

            HttpResponse<String> getResponse;

            try {
                getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            parseData(getResponse.body());

            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        httpClient.close();
    }

    private void parseData(String data) {
        Gson gson = new Gson();

        AnimeResponse animeResponse = gson.fromJson(data, AnimeResponse.class);

        for (AnimeData x : animeResponse.getData()) {
            animeRepo.save(new Anime(x.getMal_id(), x.getTitle(), x.getUrl(), x.getSeason(), x.getYear()));
            scoreRepo.save(new Score(x.getMal_id(), x.getScore(), x.getRank(), x.getMembers(), LocalDate.now()));
        }
    }
}