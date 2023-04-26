package obi_task.wahrenkorb.api;

import obi_task.wahrenkorb.model.ShoppingCart;
import obi_task.wahrenkorb.service.CustomerService;
import org.springframework.stereotype.Controller;

@Controller
public class ShoppingCartController implements ShoppingCartApi {

    CustomerService customerService;

    public ShoppingCartController(CustomerService customerService) {
        this.customerService = customerService;
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
