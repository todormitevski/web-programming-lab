package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.model.exceptions.ProductionNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
                    String name = String.format("Name %d", i+1);
                    String country = String.format("Country %d", i+1);
                    String address = String.format("Address %d", i+1);
                    Production production = new Production(name, country, address);
                    movieList.add(new Movie(title,summary,rating,production));
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

    public List<Movie> searchMoviesByTitleAndRating(String title, Double rating){
        return movieList.stream()
                .filter(m -> m.getTitle().contains(title) &&
                        m.getRating() >= rating)
                .collect(Collectors.toList());
    }

    public void deleteMovieById(Long id){
        movieList.removeIf(m -> m.getId().equals(id));
    }

    public Optional<Movie> saveMovie(String title, String summary, double rating, Production production){
        if(production == null)
            throw new ProductionNotFoundException();

        Movie movie = new Movie(title, summary, rating, production);
        movieList.removeIf(m -> m.getTitle().equals(title));
        movieList.add(movie);
        return Optional.of(movie);
    }
}
