package ru.bulish.spring.geek_shop_boot.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.bulish.spring.geek_shop_boot.entity.Category;

import java.util.List;

/**
 * Interface Service layer for Category to implement business logic
 * @author Sorokina
 * @see ru.bulish.spring.geek_shop_boot.service.impl.CategoryServiceImpl
 */
public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id);

    void save(Category category);



}
