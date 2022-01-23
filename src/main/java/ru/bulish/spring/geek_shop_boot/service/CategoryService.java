package ru.bulish.spring.geek_shop_boot.service;

import ru.bulish.spring.geek_shop_boot.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id);

    void save(Category category);



}
