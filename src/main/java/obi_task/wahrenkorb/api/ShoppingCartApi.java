package obi_task.wahrenkorb.api;

import obi_task.wahrenkorb.model.Customer;
import obi_task.wahrenkorb.model.ShoppingCart;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ShoppingCartApi {
    @GetMapping("customer/{customerId}")
    Customer getCustomer(@PathVariable Long customerId);

    @PostMapping("customer/add")
    void addCustomer(@RequestBody Customer customer);

    @PostMapping(value = "customer/{customerId}/shoppingcart/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    void addItemToCustomerShoppingCart(@PathVariable Long customerId, @RequestBody Long productId, Integer quantity);

    @PostMapping(value = "customer/{customerId}/shoppingcart/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
    void removeItemFromCustomerShoppingCart(@PathVariable Long customerId, @RequestBody Long productId);

    @PostMapping(value = "customer/{customerId}/shoppingcart/clear", consumes = MediaType.APPLICATION_JSON_VALUE)
    void clearCustomerShoppingCart(@PathVariable Long customerId);

    @GetMapping(value = "customer/{customerId}/shoppingcart", consumes = MediaType.APPLICATION_JSON_VALUE)
    ShoppingCart getCustomerShoppingCart(@PathVariable Long customerId);
}
