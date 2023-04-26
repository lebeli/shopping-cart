package obi_task.warenkorb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "shoppingCart")  // Customer parent of ShoppingCart
    @JsonIgnore
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private Set<Item> items = new HashSet<>();
    private float cartPrice;

    public ShoppingCart() {}

    public void addItem(Item item) {
        // Manage through hashcode() and equals() in entities
        if (item.getQuantity() > item.getProduct().getStock()) {
            return;
        }
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

    public Item getItemForProduct(Product product) {
        return items.stream()
                    .filter(cartItem -> cartItem.getProduct().equals(product))
                    .findFirst()
                    .orElse(null);
    }
}
