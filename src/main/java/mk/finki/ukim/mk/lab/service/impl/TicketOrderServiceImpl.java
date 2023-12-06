package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.repository.impl.InMemoryTicketOrderRepository;
import mk.finki.ukim.mk.lab.repository.jpa.TicketOrderRepository;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {
    final TicketOrderRepository ticketOrderRepository;

    public TicketOrderServiceImpl(TicketOrderRepository ticketOrderRepository) {
        this.ticketOrderRepository = ticketOrderRepository;
    }

    @Override
    public TicketOrder placeOrder(String movieTitle, int numberOfTickets, LocalDateTime dateCreated) {
        ticketOrderRepository.save(new TicketOrder(movieTitle, (long) numberOfTickets, dateCreated));
        return new TicketOrder(movieTitle, (long) numberOfTickets, dateCreated);
    }

    @Override
    public List<TicketOrder> findAllFilterDate(@DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss") LocalDateTime from,
                                               @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss") LocalDateTime to) {
        return ticketOrderRepository.findAllByDateCreatedBetween(from, to);
    }

    @Override
    public List<TicketOrder> listAll() {
        return ticketOrderRepository.findAll();
    }

}
