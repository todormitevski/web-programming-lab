package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.impl.TicketOrderServiceImpl;
import mk.finki.ukim.mk.lab.service.impl.UserServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController {

    private final TicketOrderServiceImpl ticketOrderService;
    private final UserServiceImpl userService;

    public TicketOrderController(TicketOrderServiceImpl ticketOrderService, UserServiceImpl userService) {
        this.ticketOrderService = ticketOrderService;
        this.userService = userService;
    }

    @PostMapping
    public String getTicketOrderPage(@RequestParam(required = false) String error,
                                     @RequestParam(required = false) String selectedMovie,
                                     @RequestParam(required = false) long numTickets,
                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm") LocalDateTime dateCreated,
                                     HttpServletRequest request,
                                     Model model){

        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        User user = userService.findAll().stream()
                .findFirst()
                .orElseThrow();
        String remoteAddr = request.getRemoteAddr();

        ticketOrderService.placeOrder(
                selectedMovie, (int) numTickets);

        model.addAttribute("cname", user.getUsername());
        model.addAttribute("address", remoteAddr);
        model.addAttribute("movie", selectedMovie);
        model.addAttribute("numTickets", numTickets);
        model.addAttribute("dateCreated", dateCreated);

        return "orderConfirmation";
    }
}
