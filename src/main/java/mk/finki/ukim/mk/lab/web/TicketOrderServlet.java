package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import mk.finki.ukim.mk.lab.service.impl.MovieServiceImpl;
import mk.finki.ukim.mk.lab.service.impl.TicketOrderServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(urlPatterns = "/ticketOrder")
public class TicketOrderServlet extends HttpServlet {

    final TicketOrderServiceImpl ticketOrderService;
    final SpringTemplateEngine springTemplateEngine;

    public TicketOrderServlet(TicketOrderServiceImpl ticketOrderService, SpringTemplateEngine springTemplateEngine) {
        this.ticketOrderService = ticketOrderService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String selectedMovie = req.getParameter("selected-movie");
        long numTickets = Long.parseLong(req.getParameter("numTickets"));

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);

        WebContext webContext = new WebContext(webExchange);

        TicketOrder ticketOrder = new TicketOrder(
                selectedMovie,
                "Todor Mitevski",
                req.getRemoteAddr(),
                numTickets
        );

        webContext.setVariable("movie", ticketOrder.getMovieTitle());
        webContext.setVariable("cname", ticketOrder.getClientName());
        webContext.setVariable("address", ticketOrder.getClientAddress());
        webContext.setVariable("numTickets", ticketOrder.getNumberOfTickets());

        this.springTemplateEngine.process("orderConfirmation.html",
                webContext,
                resp.getWriter());
    }
}
