package cz.osu.malfetcher.model.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "score")
public class Score {
    @EmbeddedId
    private ScoreComposite id;

    @Column(nullable = false)
    private float score;

    @Column(nullable = false)
    private int rank;

    @Column(nullable = false)
    private int members;

    @ManyToOne
    @MapsId("animeId")
    @JoinColumn(name = "anime_id", nullable = false)
    private Anime anime;
}