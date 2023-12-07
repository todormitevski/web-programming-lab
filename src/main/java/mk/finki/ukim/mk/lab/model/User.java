package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="shop_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Convert(converter = UserFullnameConverter.class)
    private UserFullname userFullname;

    private String password;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @ManyToMany
    private List<ShoppingCart> carts;

    public User(String username, UserFullname userFullname, String password, LocalDate dateOfBirth, List<ShoppingCart> carts) {
        this.username = username;
        this.userFullname = userFullname;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.carts = carts;
    }
}
