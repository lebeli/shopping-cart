package obi_task.warenkorb.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "shoppingcart_id")  // Customer parent of ShoppingCart
    private ShoppingCart shoppingCart;

    public Customer() {
        this.shoppingCart = new ShoppingCart();
    }
}
