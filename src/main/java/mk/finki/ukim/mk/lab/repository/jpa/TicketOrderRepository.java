package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketOrderRepository extends JpaRepository<TicketOrder, Long> {
}
