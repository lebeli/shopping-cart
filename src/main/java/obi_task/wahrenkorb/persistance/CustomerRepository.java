package obi_task.wahrenkorb.persistance;

import obi_task.wahrenkorb.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findById(Long id);
    Customer save(Customer customer);
}
