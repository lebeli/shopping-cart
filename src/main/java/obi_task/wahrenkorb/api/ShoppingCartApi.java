package obi_task.wahrenkorb.api;

import obi_task.wahrenkorb.model.ShoppingCart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface ShoppingCartApi {

    @PostMapping
    void addItem(Long itemId, int quantity);

    @GetMapping("/shoppingcart/{id}")
    ShoppingCart getCustomerShoppingCart(Long shoppingCartId);
}
