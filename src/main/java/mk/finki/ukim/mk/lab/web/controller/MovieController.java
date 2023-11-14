package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.service.impl.MovieServiceImpl;
import mk.finki.ukim.mk.lab.service.impl.ProductionServiceImpl;
import mk.finki.ukim.mk.lab.service.impl.TicketOrderServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieServiceImpl movieService;

    public MovieController(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String getMoviesPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) String searchedTitle,
                                @RequestParam(required = false) Double searchedRating,
                                Model model){

        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("movies", this.movieService.listAll());

        if(searchedTitle != null && searchedRating != null)
            model.addAttribute("searchedMovies",
                movieService.searchMoviesByTitleAndRating(searchedTitle, searchedRating));

        return "listMovies.html";
    }

//    @GetMapping("edit/{movieId}")
//    public String editMovie(){
//
//    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id){
        this.movieService.deleteMovieById(id);
        return "redirect:/movies";
    }

//    public String saveMovie(@RequestParam String title,
//                            @RequestParam String summary,
//                            @RequestParam double rating,
//                            @RequestParam Long productionId){
//
//    }
}
