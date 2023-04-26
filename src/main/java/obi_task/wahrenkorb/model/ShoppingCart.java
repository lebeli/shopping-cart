package obi_task.wahrenkorb.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
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
        // Manage through hashcode() and equals() in entities
        Item updateItem = items.stream()
                .filter(cartItem -> cartItem.getProduct().equals(item.getProduct()))
                .findFirst()
                .orElse(null);
        if (updateItem == null) {
            if (item.getQuantity() < item.getProduct().getMinQuantity()) {
                item.setQuantity(item.getProduct().getMinQuantity());
            }
            items.add(item);

        } else {
            // quantity constraint on product fulfilled, if item already in shopping cart
            updateItem.setQuantity(updateItem.getQuantity() + item.getQuantity());
        }
        cartPrice += item.getProduct().getPrice() * item.getQuantity();
    }

    public void removeItem(Product product) {
        items.remove(new Item(product));
    }

    public void clear() {
        items = new HashSet<>();
    }
}
