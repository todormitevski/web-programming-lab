package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.service.impl.TicketOrderServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController {

    private final TicketOrderServiceImpl ticketOrderService;

    public TicketOrderController(TicketOrderServiceImpl ticketOrderService) {
        this.ticketOrderService = ticketOrderService;
    }

    @PostMapping
    public String getTicketOrderPage(@RequestParam(required = false) String error,
                                     @RequestParam(required = false) String selectedMovie,
                                     @RequestParam(required = false) long numTickets,
                                     HttpServletRequest request,
                                     Model model){

        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("cname", "todormitevski");
        model.addAttribute("address", request.getRemoteAddr());
        model.addAttribute("movie", selectedMovie);
        model.addAttribute("numTickets", numTickets);

        return "orderConfirmation.html";
    }
}
