package obi_task.wahrenkorb.api;

import obi_task.wahrenkorb.model.Customer;
import obi_task.wahrenkorb.model.ShoppingCart;
import obi_task.wahrenkorb.service.CustomerService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartController implements ShoppingCartApi {

    CustomerService customerService;

    public ShoppingCartController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public Customer getCustomer(Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    @Override
    public void addCustomer(Customer customer) {
        this.customerService.addCustomer(customer);
        // Return Statuscode 200 via ResponseEntity
    }

    @Override
    public void addItemToCustomerShoppingCart(Long customerId, Long productId, Integer quantity) {
        customerService.addItemToShoppingCart(customerId, productId, quantity);
    }

    @Override
    public void removeItemFromCustomerShoppingCart(Long customerId, Long productId) {
        customerService.removeItemFromShoppingCart(customerId, productId);
    }

    @Override
    public void clearCustomerShoppingCart(Long customerId) {
        customerService.clearShoppingCart(customerId);
    }

    @Override
    public ShoppingCart getCustomerShoppingCart(Long customerId) {
        return customerService.getShoppingCartForCustomer(customerId);
    }
}
