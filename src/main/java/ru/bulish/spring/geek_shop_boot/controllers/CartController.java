package ru.bulish.spring.geek_shop_boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.bulish.spring.geek_shop_boot.component.ShoppingCart;
import ru.bulish.spring.geek_shop_boot.entity.Product;
import ru.bulish.spring.geek_shop_boot.service.ServiceProduct;

import java.util.NoSuchElementException;

/**
 * Class Controller for actions with Cart
 * @author Sorokina
 * @version 1.0
 */
@Controller
@RequestMapping("/cart")
@SessionAttributes("shoppingCart")
@AllArgsConstructor
public class CartController {

    /**
     * Field serviceProduct is injected in this class for giving access to service layer
     * @see ServiceProduct
     */
    private final ServiceProduct productService;

    /**
     * Method shows the view with the list of added products to a cart
     * @return view HTML
     */
    @GetMapping("/list")
    public String showCart() {
        return "cart/list";
    }

    /**
     * Method adds a product by its id to the cart
     * @param id is parameter that coming from url and then is used for getting specific Product by its id from DB
     * @param cart is the object where a product is added in
     * @return redirect to product list view
     * @see ShoppingCart
     */
    @GetMapping("/add-to-cart")
    public RedirectView addToCart(@RequestParam Long id, @ModelAttribute ShoppingCart cart) {
        Product product = productService.getProductById(id);
        cart.addProduct(product);

        return new RedirectView("/product/list");
    }

    /**
     * Method removes a product by its id in the cart
     * @param id is parameter that coming from url and then is used for getting specific Product by its id from DB
     * @param cart is the object where a product is removed from
     * @return redirect to product list view
     * @see ShoppingCart
     */
    @GetMapping("/remove-from-cart")
    public RedirectView removeFromCart(@RequestParam Long id, @ModelAttribute ShoppingCart cart) {
        Product product = productService.getProductById(id);
        cart.removeProduct(product);

        return new RedirectView("/cart/list");
    }

}
