package cz.osu.malfetcher.model.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "anime")
public class Anime {
    @Id
    @Column(unique = true, nullable = false, updatable = false)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "anime")
    private List<Score> scores;
}