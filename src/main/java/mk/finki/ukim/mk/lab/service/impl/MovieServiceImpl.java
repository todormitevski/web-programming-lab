package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.model.exceptions.ProductionNotFoundException;
import mk.finki.ukim.mk.lab.repository.impl.InMemoryMovieRepository;
import mk.finki.ukim.mk.lab.repository.impl.InMemoryProductionRepository;
import mk.finki.ukim.mk.lab.repository.jpa.MovieRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ProductionRepository;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    final MovieRepository movieRepository;
    final InMemoryProductionRepository inMemoryProductionRepository;
    final ProductionRepository productionRepository;

    public MovieServiceImpl(MovieRepository movieRepository, InMemoryProductionRepository inMemoryProductionRepository, ProductionRepository productionRepository) {
        this.movieRepository = movieRepository;
        this.inMemoryProductionRepository = inMemoryProductionRepository;
        this.productionRepository = productionRepository;
    }

    @Override
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return movieRepository.findMoviesByTitle(text);
    }

    @Override
    public List<Movie> searchMoviesByTitleAndRating(String text, Double rating) {
        return movieRepository.searchMoviesByTitleAndRating(text, rating);
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Optional<Movie> saveMovie(String title, String summary, double rating, Long productionId, Long movieId) {
        Production production = productionRepository.findById(productionId)
                .orElseThrow(ProductionNotFoundException::new);
        Movie movie = movieRepository.findById(movieId)
                .orElse(new Movie());
        movie.setTitle(title);
        movie.setSummary(summary);
        movie.setRating(rating);
        movie.setProduction(production);
        this.movieRepository.save(movie);
        return Optional.of(movie);
    }

    @Override
    public Optional<Movie> findById(Long movieId) {
        return movieRepository.findById(movieId);
    }

}
