package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAll();
    List<Movie> findMoviesByTitle(String text);

    @Query("SELECT m FROM Movie m WHERE m.title LIKE %:text% AND m.rating >= :rating")
    List<Movie> searchMoviesByTitleAndRating(String text, Double rating);

    void deleteById(Long id);
}
