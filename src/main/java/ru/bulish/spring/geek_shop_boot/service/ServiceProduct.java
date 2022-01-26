package ru.bulish.spring.geek_shop_boot.service;



import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.bulish.spring.geek_shop_boot.entity.Product;
import ru.bulish.spring.geek_shop_boot.entity.ProductSearchConditional;

import java.util.List;
import java.util.Optional;

/**
 * Interface Service layer for Product to implement business logic
 * @author Sorokina
 * @see ru.bulish.spring.geek_shop_boot.service.impl.ServiceProductImpl
 */
@Service
public interface ServiceProduct {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void saveOrUpdate(Product product);
    void deleteById(Long id);
    void saveProductWithImage(MultipartFile file, Product product);
    Page<Product> getProductsByConditional(ProductSearchConditional conditional, Optional<Integer> page);
}
