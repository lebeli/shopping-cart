package obi_task.wahrenkorb.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ShoppingCartTest {

    private ShoppingCart shoppingCart;
    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    public void init() {
        shoppingCart = new ShoppingCart();
        product1 = new Product(1L, "Schraube", 1.00F, 1000, 10);
        product2 = new Product(2L, "Glühbirne", 8.00F, 1500, 4);
        product3 = new Product(3L, "Gartenschlauch", 35.00F, 250, 1);
        shoppingCart.addItem(new Item(product1,10));
        shoppingCart.addItem(new Item(product2,4));
        shoppingCart.addItem(new Item(product3,1));
    }

    @Test
    void addItemToShoppingCartTest() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item item = new Item(new Product("Schraube", 0.99F, 1000, 10), 10);
        shoppingCart.addItem(item);
        assertThat(shoppingCart.getItems().size() == 1).isTrue();
    }

    @Test
    void addItemToShoppingCartMinQuantityTest() {
        Item item = new Item(new Product(4L, "Schraube", 0.99F, 1000, 10), 8);
        shoppingCart.addItem(item);
        assertThat(shoppingCart.getItems().contains(item) && item.getQuantity() == 10).isTrue();
    }

    @Test
    void addDuplicateItemToShoppingCartTest() {
        Item item = new Item(product1, 20);
        shoppingCart.addItem(item);
        assertThat(shoppingCart.getItemForProduct(product1).getQuantity() == 30).isTrue();
    }

    @Test
    void removeItemFromShoppingCartTest() {
        Item item = shoppingCart.getItemForProduct(product1);
        shoppingCart.removeItem(product1);
        assertThat(shoppingCart.getItems().contains(item)).isFalse();
    }

    @Test
    void cartPriceTest() {
        assertThat(shoppingCart.getCartPrice()).isEqualTo(77.00F);
    }
}
