package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TicketOrder {
    String movieTitle;
    Long numberOfTickets;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss")
    LocalDateTime dateCreated;

    public TicketOrder(String movieTitle, Long numberOfTickets) {
        this.movieTitle = movieTitle;
        this.numberOfTickets = numberOfTickets;
    }

    @ConstructorProperties({"movieTitle", "numTickets", "dateCreated"})
    public TicketOrder(String movieTitle, Long numberOfTickets, LocalDateTime dateCreated) {
        this.movieTitle = movieTitle;
        this.numberOfTickets = numberOfTickets;
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formattedDate = dateCreated != null ? dateCreated.format(formatter) : "N/A";

        return String.format(
                "Title: " + movieTitle +
                " Number of tickets: " + numberOfTickets +
                " Date created: " + dateCreated
        );
    }
}
