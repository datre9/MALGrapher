package cz.osu.malfetcher.model.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(nullable = false)
    private int animeId;

    @Column(nullable = false)
    private float score;

    @Column(nullable = false)
    private int rank;

    @Column(nullable = false)
    private int members;

    @Column(nullable = false)
    private LocalDate date;

    public Score(int animeId, float score, int rank, int members, LocalDate date) {
        this.animeId = animeId;
        this.score = score;
        this.rank = rank;
        this.members = members;
        this.date = date;
    }
}