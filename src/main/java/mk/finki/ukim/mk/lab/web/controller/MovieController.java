package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.service.impl.MovieServiceImpl;
import mk.finki.ukim.mk.lab.service.impl.ProductionServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieServiceImpl movieService;
    private final ProductionServiceImpl productionService;


    public MovieController(MovieServiceImpl movieService, ProductionServiceImpl productionService) {
        this.movieService = movieService;
        this.productionService = productionService;
    }

    @GetMapping
    public String getMoviesPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) String searchedTitle,
                                @RequestParam(required = false) String searchedRating,
                                Model model){

        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("movies", this.movieService.listAll());

        double parsedRating = 0.0;
        List<Movie> foundMovies = new ArrayList<>();
        if(searchedTitle != null && searchedRating != null){
            parsedRating = Double.parseDouble(searchedRating);
            foundMovies = movieService.searchMoviesByTitleAndRating(searchedTitle, parsedRating);
            model.addAttribute("searchedMovies", foundMovies);
        }

        return "listMovies";
    }

    @GetMapping("/edit/{movieId}")
    public String editMovie(@PathVariable Long movieId,
                            Model model){

        if (this.movieService.findById(movieId).isPresent()) {
            Movie movies = this.movieService.findById(movieId).get();
            List<Production> productions = this.productionService.findAll();
            model.addAttribute("productions", productions);
            model.addAttribute("movies", movies);
            return "add-movie";
        }
        return "redirect:/movies?error=MovieNotFound";
    }

    @GetMapping("/add-form")
    public String addMoviePage(Model model) {
        List<Production> productions = this.productionService.findAll();
        model.addAttribute("productions", productions);
        return "add-movie";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id){
        this.movieService.deleteById(id);
        return "redirect:/movies";
    }

    @PostMapping("/add")
    public String saveMovie(@RequestParam String title,
                            @RequestParam String summary,
                            @RequestParam double rating,
                            @RequestParam Long productionId,
                            @RequestParam Long movieId){

        this.movieService.saveMovie(title, summary, rating, productionId, movieId);
        return "redirect:/movies";
    }
}
