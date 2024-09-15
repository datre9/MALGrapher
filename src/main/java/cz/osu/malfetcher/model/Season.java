package cz.osu.malfetcher.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Season {
    private String season;
    private int year;

    private static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    public static ArrayList<Season> getSeasons() {
        int thisMonth = LocalDate.now().getMonthValue();
        int thisYear = LocalDate.now().getYear();
        ArrayList<Season> seasons = new ArrayList<>();

        seasons.add(new Season("fall", 2023));
        for (int i = 2024; i < thisYear + 1; i++) {
            // check if the year is not complete (has less than 4 seasons)
            if (i == thisYear && thisMonth < 10) {
                if (Season.isBetween(thisMonth, 1, 3)) {
                    seasons.add(new Season("winter", i));
                } else if (Season.isBetween(thisMonth, 4, 6)) {
                    seasons.add(new Season("winter", i));
                    seasons.add(new Season("spring", i));
                } else if (Season.isBetween(thisMonth, 7, 9)) {
                    seasons.add(new Season("winter", i));
                    seasons.add(new Season("spring", i));
                    seasons.add(new Season("summer", i));
                }
            } else {
                // add seasons of a complete year (all 4 seasons)
                seasons.add(new Season("winter", i));
                seasons.add(new Season("spring", i));
                seasons.add(new Season("summer", i));
                seasons.add(new Season("fall", i));
            }
        }
        return seasons;
    }
}