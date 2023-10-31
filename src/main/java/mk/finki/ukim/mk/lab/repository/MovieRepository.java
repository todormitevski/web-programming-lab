package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@Repository
public class MovieRepository {
    public static List<Movie> movieList = null;

    @PostConstruct
    public void init(){
        Random rand = new Random();
        movieList = new ArrayList<>(10);
        IntStream.range(0, 10)
                .forEach(i ->{
                    String title = String.format("Movie %d", i+1);
                    String summary = String.format("Summary %d", i+1);
                    double rating = Math.round((rand.nextDouble() * 4 + 1) * 10) / 10.0;
                    movieList.add(new Movie(title,summary,rating));
                });
    }

    public List<Movie> findAll(){
        return movieList;
    }

    public List<Movie> searchMovies(String text){
        return movieList.stream()
                .filter(m -> m.getTitle().contains(text) || m.getSummary().contains(text))
                .collect(Collectors.toList());
    }
}
