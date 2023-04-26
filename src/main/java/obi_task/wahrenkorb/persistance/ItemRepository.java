package obi_task.wahrenkorb.persistance;

import obi_task.wahrenkorb.model.Item;
import obi_task.wahrenkorb.model.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Optional<Item> findById(Long id);
}
