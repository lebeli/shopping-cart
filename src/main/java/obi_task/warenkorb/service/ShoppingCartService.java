package obi_task.warenkorb.service;

import obi_task.warenkorb.exception.ShoppingCartNotFoundException;
import obi_task.warenkorb.model.ShoppingCart;
import obi_task.warenkorb.persistance.ShoppingCartRepository;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public ShoppingCart getShoppingCard(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElse(null);
        if (shoppingCart == null) {
            throw new ShoppingCartNotFoundException();
        }
        return shoppingCart;
    }

    public ShoppingCart addShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }
}
