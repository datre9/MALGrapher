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

    public static ArrayList<Season> getLastYearOfSeasons() {
        int month = LocalDate.now().getMonthValue();
        ArrayList<Season> seasons = new ArrayList<>();

        seasons.add(new Season("winter", LocalDate.now().getYear() - 1));
        seasons.add(new Season("spring", LocalDate.now().getYear() - 1));
        seasons.add(new Season("summer", LocalDate.now().getYear() - 1));
        seasons.add(new Season("fall", LocalDate.now().getYear() - 1));

        if (Season.isBetween(month, 1, 3)) {
            seasons.getFirst().setYear(LocalDate.now().getYear());
        } else if (Season.isBetween(month, 4, 6)) {
            seasons.get(0).setYear(LocalDate.now().getYear());
            seasons.get(1).setYear(LocalDate.now().getYear());
        } else if (Season.isBetween(month, 7, 9)) {
            seasons.get(0).setYear(LocalDate.now().getYear());
            seasons.get(1).setYear(LocalDate.now().getYear());
            seasons.get(2).setYear(LocalDate.now().getYear());
        } else if (Season.isBetween(month, 10, 12)) {
            seasons.forEach(x -> x.setYear(LocalDate.now().getYear()));
        }

        return seasons;
    }
}