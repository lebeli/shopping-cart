package obi_task.wahrenkorb.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "shoppingCart")  // Customer parent of ShoppingCart
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Item> items = new HashSet<>();
    private float cartPrice;

    public void addItem(Item item) {
        Item updateItem = items.stream()
                .filter(cartItem -> cartItem.getProduct().equals(item.getProduct()))
                .findFirst()
                .orElse(null);
        if (updateItem == null) {
            items.add(item);
        } else {
            updateItem.setQuantity(updateItem.getQuantity() + item.getQuantity());
        }
        cartPrice += item.getProduct().getPrice() * item.getQuantity();
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
}
