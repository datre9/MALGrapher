package cz.osu.malfetcher.model.db;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ScoreComposite implements Serializable {
    private LocalDate date;
    private int animeId;
}