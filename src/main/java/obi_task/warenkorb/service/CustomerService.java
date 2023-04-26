package obi_task.warenkorb.service;

import lombok.extern.slf4j.Slf4j;
import obi_task.warenkorb.exception.CustomerNotFoundException;
import obi_task.warenkorb.exception.ProductNotFoundException;
import obi_task.warenkorb.exception.ShoppingCartNotFoundException;
import obi_task.warenkorb.model.Customer;
import obi_task.warenkorb.model.Item;
import obi_task.warenkorb.model.Product;
import obi_task.warenkorb.model.ShoppingCart;
import obi_task.warenkorb.persistance.CustomerRepository;
import obi_task.warenkorb.persistance.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerService {
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;

    public CustomerService(CustomerRepository customerRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    public void addCustomer(Customer customer) {
        customer.getShoppingCart().setCustomer(customer);
        customerRepository.save(customer);
        log.info(String.format("Saved customer with id %d", customer.getId()));
    }

    public ShoppingCart getShoppingCartForCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException();
        } else if (customer.getShoppingCart() == null) {
            throw new ShoppingCartNotFoundException();
        }
        return customer.getShoppingCart();
    }

    public void addItemToShoppingCart(Long customerId, Long productId, int quantity) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        Item item = new Item(product, quantity);
        customer.getShoppingCart().addItem(item);
        customerRepository.save(customer);
    }

    public void removeItemFromShoppingCart(Long customerId, Long productId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        customer.getShoppingCart().removeItem(product);
        customerRepository.save(customer);
    }

    public void clearShoppingCart(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        customer.getShoppingCart().clear();
        customerRepository.save(customer);
    }
}
