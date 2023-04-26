package obi_task.wahrenkorb.api;

import obi_task.wahrenkorb.model.ShoppingCart;
import obi_task.wahrenkorb.service.CustomerService;
import obi_task.wahrenkorb.service.ShoppingCartService;
import org.springframework.stereotype.Controller;

@Controller
public class ShoppingCartController implements ShoppingCartApi {

    CustomerService customerService;

    public ShoppingCartController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void addItemToCustomerShoppingCart(Long customerId, Long itemId, int quantity) {
        customerService.addItemToShoppingCart(customerId, itemId, quantity);
    }

    @Override
    public ShoppingCart getCustomerShoppingCart(Long customerId) {
        return customerService.getShoppingCartForCustomer(customerId);
    }
}
