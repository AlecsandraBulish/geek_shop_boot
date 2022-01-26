package ru.bulish.spring.geek_shop_boot.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.bulish.spring.geek_shop_boot.entity.Category;
import ru.bulish.spring.geek_shop_boot.entity.Product;
import ru.bulish.spring.geek_shop_boot.service.CategoryService;

import javax.validation.Valid;

/**
 * Class Controller for actions with Category entity
 * @author Sorokina
 * @version 1.0
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    /**
     * Field categoryService is injected in this class for giving access to service layer
     * @see CategoryService
     */
    private final CategoryService categoryService;



    /**
     * Method show a particular view according to url and also using coming parameters calling methods that
     * do some actions with entity Category
     * @param id is parameter that coming from url and then is used for getting specific Category by its id from DB
     * @param model is responsible for interacting between Controller and View and keeping variables so that display them in the view
     * @return view HTML form for creating a new category or editing depending of the id
     * @see Category
     */
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

    /**
     * Method show a particular view according to url and also using coming parameters calling methods that
     * do some actions with entity Category
     * @param category this is model that keeps created object
     * @param bindingResult allows to check if all params of Product object were filled according applied rules
     * @return if validation goes successfully it redirects to product list view otherwise returns the form for creation with
     * written mistakes
     * @see Category
     */
    @PostMapping
    public RedirectView saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new RedirectView("/category/form");
        }
            categoryService.save(category);
            return new RedirectView("/product/list");

    }
    /**
     * Method catches specific exception
     * @param ex is parameter that might be caught while runtime if entity doesn't exist
     * @return  it returns view with info about a problem
     */
    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }



}
