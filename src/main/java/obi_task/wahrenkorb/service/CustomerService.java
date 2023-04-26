package obi_task.wahrenkorb.service;

import obi_task.wahrenkorb.exception.CustomerNotFoundException;
import obi_task.wahrenkorb.exception.ProductNotFoundException;
import obi_task.wahrenkorb.exception.ShoppingCartNotFoundException;
import obi_task.wahrenkorb.model.Customer;
import obi_task.wahrenkorb.model.Item;
import obi_task.wahrenkorb.model.Product;
import obi_task.wahrenkorb.model.ShoppingCart;
import obi_task.wahrenkorb.persistance.CustomerRepository;
import obi_task.wahrenkorb.persistance.ItemRepository;
import obi_task.wahrenkorb.persistance.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;

    public CustomerService(CustomerRepository customerRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
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

    public void addItemToShoppingCart(Long customerId, Long itemId, int quantity) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Product product = productRepository.findById(itemId).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException();
        } else if (product == null) {
            throw new ProductNotFoundException();
        }
        Item item = new Item(product, quantity);
        customer.getShoppingCart().addItem(item);
        customerRepository.save(customer);
    }
}