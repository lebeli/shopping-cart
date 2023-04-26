package obi_task.wahrenkorb.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "shoppingcart_id")  // Customer parent of ShoppingCart
    private ShoppingCart shoppingCart;

    public Customer() {
        this.shoppingCart = new ShoppingCart();
    }
}
