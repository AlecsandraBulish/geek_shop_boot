package ru.bulish.spring.geek_shop_boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.bulish.spring.geek_shop_boot.component.ShoppingCart;
import ru.bulish.spring.geek_shop_boot.entity.Product;
import ru.bulish.spring.geek_shop_boot.service.ServiceProduct;

@Controller
@RequestMapping("/cart")
@SessionAttributes("shoppingCart")
@AllArgsConstructor
public class CartController {

    private final ServiceProduct productService;

    @GetMapping("/list")
    public String showCart() {
        return "cart/list";
    }

    @GetMapping("/add-to-cart")
    public RedirectView addToCart(@RequestParam Long id, @ModelAttribute ShoppingCart cart) {
        Product product = productService.getProductById(id);
        cart.addProduct(product);

        return new RedirectView("/product/list");
    }

    @GetMapping("/remove-from-cart")
    public RedirectView removeFromCart(@RequestParam Long id, @ModelAttribute ShoppingCart cart) {
        Product product = productService.getProductById(id);
        cart.removeProduct(product);

        return new RedirectView("/cart/list");
    }

}
