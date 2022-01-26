package ru.bulish.spring.geek_shop_boot.controllers.advice;



import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.bulish.spring.geek_shop_boot.component.ShoppingCart;
import ru.bulish.spring.geek_shop_boot.entity.Product;

import java.util.NoSuchElementException;

/**
 * Class ControllerAdvice for actions with ShoppingCart entity
 * @author Sorokina
 * @version 1.0
 * @see ShoppingCart
 */
@ControllerAdvice
public class GlobalShoppingCart {

    /**
     * Method create and return the object of ShoppingCart
     * @return ShoppingCart object
     * @see ShoppingCart
     */
    @ModelAttribute("shoppingCart")
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }


}
