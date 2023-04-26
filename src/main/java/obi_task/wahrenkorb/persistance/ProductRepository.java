package obi_task.wahrenkorb.persistance;

import obi_task.wahrenkorb.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findById(Long id);
}
