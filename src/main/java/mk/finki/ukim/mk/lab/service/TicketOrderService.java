package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.TicketOrder;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketOrderService {
    TicketOrder placeOrder(String movieTitle, int numberOfTickets, LocalDateTime dateCreated);
    List<TicketOrder> findAllFilterDate(LocalDateTime from, LocalDateTime to);
    List<TicketOrder> listAll();
}
