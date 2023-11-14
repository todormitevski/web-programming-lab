package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketOrderRepository {
    public static List<TicketOrder> ticketOrderList = null;

    @PostConstruct
    public void init(){
        ticketOrderList = new ArrayList<>();
    }

    public void addTicketOrder(TicketOrder ticketOrder){
        ticketOrderList.add(ticketOrder);
    }
}
