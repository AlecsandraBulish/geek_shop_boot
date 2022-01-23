package ru.bulish.spring.geek_shop_boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bulish.spring.geek_shop_boot.entity.Category;
import ru.bulish.spring.geek_shop_boot.repository.CategoryRepository;
import ru.bulish.spring.geek_shop_boot.service.CategoryService;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service

public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private  CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

}
