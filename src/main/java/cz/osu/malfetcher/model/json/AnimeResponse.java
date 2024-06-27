package cz.osu.malfetcher.model.json;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class AnimeResponse {
    private List<AnimeData> data;
}