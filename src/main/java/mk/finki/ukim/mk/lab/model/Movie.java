package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class Movie {
    String title;
    String summary;
    double rating;

    public Movie(String title, String summary, double rating) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
    }
}
