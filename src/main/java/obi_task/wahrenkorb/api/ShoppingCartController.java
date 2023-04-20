package obi_task.wahrenkorb.api;

import obi_task.wahrenkorb.model.ShoppingCart;
import obi_task.wahrenkorb.service.ShoppingCartService;
import org.springframework.stereotype.Controller;

@Controller
public class ShoppingCartController implements ShoppingCartApi {

    ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void addItem(Long itemId, int quantity) {

    }

    @Override
    public ShoppingCart getCustomerShoppingCart(Long shoppingCartId) {
        return shoppingCartService.getShoppingCardForCustomer(shoppingCartId);
    }
}
