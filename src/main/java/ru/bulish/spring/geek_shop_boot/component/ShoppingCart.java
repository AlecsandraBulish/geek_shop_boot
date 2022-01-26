package ru.bulish.spring.geek_shop_boot.component;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bulish.spring.geek_shop_boot.entity.Product;
import ru.bulish.spring.geek_shop_boot.service.ServiceProduct;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Class ShoppingCart for actions with The Cart
 * @author Sorokina
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
public class ShoppingCart {

    /**
     * Field productsWithCount keeps the information about quantity of exact product
     */
    private final Map<Product, Integer> productsWithCount = new HashMap<>();

    /**
     * Method adds a product
     * @param product is an object that is added in a cart
     */
    public void addProduct(Product product) {
        productsWithCount.merge(product, 1, (prev, cur) -> prev + 1);
    }

    /**
     * Method removes a product from a cart
     * @param product is an object that is removed from a cart
     */
    public void removeProduct(Product product) {
        if (productsWithCount.containsKey(product)) {
            Integer count = productsWithCount.get(product);

            removeProduct(product, count);

            return;
        }

        throw new IllegalArgumentException("Product not found in the cart");
    }

    private void removeProduct(Product product, Integer count) {
        if (count > 1) {
            productsWithCount.put(product, count - 1);
        } else {
            productsWithCount.remove(product);
        }
    }


    public Map<Product, Integer> getProductsWithCount() {
        return new HashMap<>(productsWithCount);
    }

    public int getCount() {
        return productsWithCount.values().stream()
                .reduce(0, Integer::sum);
    }

}
