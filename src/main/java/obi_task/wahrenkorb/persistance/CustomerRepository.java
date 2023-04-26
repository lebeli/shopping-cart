package obi_task.wahrenkorb.persistance;

import obi_task.wahrenkorb.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findById(Long id);
    Customer save(Customer customer);
}
