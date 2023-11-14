package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> listAll();
    List<Movie> searchMovies(String text);
    List<Movie> searchMoviesByTitleAndRating(String text, Double rating);
    void deleteMovieById(Long id);
    Optional<Movie> saveMovie(String title, String summary, double rating, Production production);
}
