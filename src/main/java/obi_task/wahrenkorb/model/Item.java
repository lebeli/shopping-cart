package obi_task.wahrenkorb.model;

import io.swagger.models.auth.In;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Product product;
    private Integer quantity;

    public Item(Product product) {
        this.product = product;
    }

    public Item(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hashCode(product);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Item) {
            if (((Item) obj).getProduct().equals(getProduct())) {
                return true;
            }
        }
        return false;
    }

    // TODO: implement equals method
}
