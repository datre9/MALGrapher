package cz.osu.malfetcher.service;

import com.google.gson.Gson;
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
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class FetchDataService {
    private final AnimeRepo animeRepo;
    private final ScoreRepo scoreRepo;

    public void getData(URI uri) {
        HttpRequest getRequest = HttpRequest.newBuilder(uri).build();
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> getResponse;

        try {
            getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        httpClient.close();

        parseData(getResponse.body());
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