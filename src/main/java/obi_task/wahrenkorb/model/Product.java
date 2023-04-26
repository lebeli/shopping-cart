package obi_task.wahrenkorb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int name;
    private float price;
    private int stock;
    private int minQuantity;

    @Override
    public int hashCode() {
        return java.util.Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Product) {
            if (((Product) obj).getId().equals(this.getId())) {
                return true;
            }
        }
        return false;
    }

}
