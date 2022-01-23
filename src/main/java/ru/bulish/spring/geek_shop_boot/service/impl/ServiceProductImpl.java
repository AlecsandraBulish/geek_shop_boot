package ru.bulish.spring.geek_shop_boot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.bulish.spring.geek_shop_boot.entity.Product;
import ru.bulish.spring.geek_shop_boot.entity.ProductSearchConditional;
import ru.bulish.spring.geek_shop_boot.repository.ProductRepository;
import ru.bulish.spring.geek_shop_boot.repository.specification.ProductSpecificationPredicates;
import ru.bulish.spring.geek_shop_boot.service.ServiceProduct;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ServiceProductImpl implements ServiceProduct {

    private final ProductRepository productRepository;


    @Override
    @Transactional
    public List<Product> getAllProducts() {
        List<Product> list = productRepository.findAll();
        return list;
    }

    @Override
    @Transactional
    public Product getProductById(Long id) {
        Product product =  productRepository.findById(id).orElse(null);
         return product;
    }

    @Override
    @Transactional
    public void saveOrUpdate(Product product)  {
        productRepository.saveAndFlush(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveProductWithImage(MultipartFile file, Product product) {
        if (file != null && !file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            try {
                product.setImageLink(Base64.getEncoder().encodeToString(file.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

         productRepository.saveAndFlush(product);
    }




    @Override
    @Transactional
    public Page<Product> getProductsByConditional(ProductSearchConditional conditional, Optional<Integer> page) {
        if (conditional == null){
            conditional = new ProductSearchConditional();
        }
        Pageable pageable = PageRequest.of(page.orElse(1)-1, conditional.getSize());
        ProductSpecificationPredicates specification = new ProductSpecificationPredicates(conditional);
        return productRepository.findAll(specification, pageable);
    }


}
