package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.service.impl.MovieServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

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

        this.springTemplateEngine.process("listMovies.html",
                webContext,
                resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("movie");
        String numTickets = req.getParameter("numTickets");
        resp.sendRedirect("/ticketOrder");
    }
}
