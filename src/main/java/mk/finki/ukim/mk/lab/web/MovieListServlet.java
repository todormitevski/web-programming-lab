package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.service.impl.MovieServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "")
public class MovieListServlet extends HttpServlet {
    private final MovieServiceImpl movieServiceImpl;
    private final SpringTemplateEngine springTemplateEngine;

    public MovieListServlet(MovieServiceImpl movieServiceImpl, SpringTemplateEngine springTemplateEngine) {
        this.movieServiceImpl = movieServiceImpl;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);

        WebContext webContext = new WebContext(webExchange);
        webContext.setVariable("Movies",movieServiceImpl.listAll());

        if(req.getParameter("sTitle") != null && req.getParameter("sRating") != null){
            String searchedTitle = req.getParameter("sTitle");

            double finalSearchedRating = Double.parseDouble(req.getParameter("sRating"));
            List<Movie> movies = movieServiceImpl.listAll().stream()
                    .filter(m -> m.getTitle().contains(searchedTitle)
                            && m.getRating() >= finalSearchedRating)
                    .collect(Collectors.toList());

            if (!movies.isEmpty())
                webContext.setVariable("SearchedMovies", movies);
        }

        this.springTemplateEngine.process("listMovies.html",
                webContext,
                resp.getWriter());
    }
}
