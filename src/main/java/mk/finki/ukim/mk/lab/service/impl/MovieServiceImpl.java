package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.repository.MovieRepository;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @Override
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return movieRepository.searchMovies(text);
    }

    @Override
    public List<Movie> searchMoviesByTitleAndRating(String text, Double rating) {
        return movieRepository.searchMoviesByTitleAndRating(text, rating);
    }

    @Override
    public void deleteMovieById(Long id) {
        movieRepository.deleteMovieById(id);
    }

    @Override
    public Optional<Movie> saveMovie(String title, String summary, double rating, Production production) {
        return movieRepository.saveMovie(title, summary, rating, production);
    }
}
