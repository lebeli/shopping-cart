package obi_task.wahrenkorb.api;

import obi_task.wahrenkorb.model.ShoppingCart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ShoppingCartApi {

    @PostMapping("customer/{customerId}/shoppingcart/add")
    void addItemToCustomerShoppingCart(@PathVariable Long customerId, @RequestBody Long itemId, int quantity);

    @GetMapping("customer/{customerId}/shoppingcart")
    ShoppingCart getCustomerShoppingCart(@PathVariable Long customerId);
}
