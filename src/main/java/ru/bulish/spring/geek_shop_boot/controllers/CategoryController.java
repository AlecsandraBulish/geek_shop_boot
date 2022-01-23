package ru.bulish.spring.geek_shop_boot.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.bulish.spring.geek_shop_boot.entity.Category;
import ru.bulish.spring.geek_shop_boot.service.CategoryService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;



    @GetMapping("/form")
    public String getCategoryForm(@RequestParam(required = false) Long id, Model model) {
        Category category = new Category();
        if (id != null) {
            category = categoryService.findById(id);
        }

        model.addAttribute("category", category);
        model.addAttribute("categories", categoryService.findAll());

        return "category/form";
    }

    @PostMapping
    public RedirectView saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new RedirectView("/category/form");
        }
            categoryService.save(category);
            return new RedirectView("/product/list");

    }


}
