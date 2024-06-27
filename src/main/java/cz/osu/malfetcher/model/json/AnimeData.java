package cz.osu.malfetcher.model.json;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AnimeData {
    private String mal_id;
    private String title;
    private String url;
    private float score;
    private int rank;
    private int members;
}