package ru.bulish.spring.geek_shop_boot.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.bulish.spring.geek_shop_boot.entity.Product;
import ru.bulish.spring.geek_shop_boot.entity.ProductSearchConditional;
import ru.bulish.spring.geek_shop_boot.service.CategoryService;
import ru.bulish.spring.geek_shop_boot.service.ServiceProduct;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;



/**
 * Class Controller for actions with Product entity
 * @author Sorokina
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/product")
public class ProductController {
    /**
     * Field serviceProduct is injected in this class for giving access to service layer
     * @see ServiceProduct
     */
    private final ServiceProduct serviceProduct;
    /**
     * Field categoryService is injected in this class for giving access to service layer
     * @see CategoryService
     */
    private final CategoryService categoryService;


    /**
     * Method show a particular view according to url and also using coming parameters calling methods that
     * do some actions with entity Product
     * @param model is responsible for interacting between Controller and View and keeping variables so that display them in the view
     * @param id is parameter that coming from url and then is used for getting specific Product by its id from DB
     * @throws NoSuchElementException if there is no any product with pointed id in DB
     * @return view HTML
     * @see Product
     */
    @GetMapping("/{id}")
    public String showOneProduct(@PathVariable("id") Long id, Model model) {
        Product product = serviceProduct.getProductById(id);
        if (product == null) {
//            throw new NoSuchElementException ("There is no such product in Database");
            throw  new NotFoundException();
        }
        model.addAttribute("product", product);
        return "products/oneproduct";
    }

    /**
     * Method show a particular view according to url and also using coming parameters calling methods that
     * do some actions with entity Product
     * @param model is responsible for interacting between Controller and View, also keeping variables so that display them in the view
     * @param page  is parameter that coming from view and then is used for pagination view of all Products
     * @param  conditional keeps set of params for filtering the returning list of products
     * @return view HTML
     * @see Product,ProductSearchConditional
     */
    @GetMapping("/list")
    public String showAllProducts(Model model,
                                  @RequestParam(name = "page") Optional<Integer> page,
                                  ProductSearchConditional conditional) {

        Page<Product> products = serviceProduct.getProductsByConditional(conditional, page);
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.findAll());

        return "products/all_products";


    }
    /**
     * Method show a particular view according to url and also using coming parameters calling methods that
     * do some actions with entity Product
     * @param model is responsible for interacting between Controller and View and keeping variables so that display them in the view
     * @return view HTML form for creating a new product
     * @see Product
     */
    @GetMapping ("/new")
    public String showTheForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "products/product_form";
    }

    /**
     * Method show a particular view according to url and also using coming parameters calling methods that
     * do some actions with entity Product
     * @param product this is model that keeps created object
     * @param result allows to check if all params of Product object were filled according applied rules
     * @return if validation goes successfully it redirects to product list view otherwise returns the form for creation with
     * written mistakes
     * @see Product
     */
    @PostMapping
    public String addNewProduct(@Valid @ModelAttribute("product") Product product, BindingResult result,
                                @RequestParam(name = "image")MultipartFile image) {
        if (result.hasErrors()) {
            return "/products/product_form";
        }
            serviceProduct.saveProductWithImage(image,product);
            return "redirect:/product/list";
        }

    /**
     * Method show a particular view according to url and also using coming parameters calling methods that
     * do some actions with entity Product
     * @param model is responsible for interacting between Controller and View and keeping variables so that display them in the view
     * @param id is parameter that coming from url and then is used for getting specific Product by its id from DB
     * @return view HTML form for editing params at existed product
     * @see Product
     */
    @GetMapping("/{id}/updateInfo")
    public String updateProduct(@PathVariable("id") Long id, Model model) {
        Product product = serviceProduct.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());

        return "products/product_form";
    }

    /**
     * Method show a particular view according to url and also using coming parameters calling methods that
     * do some actions with entity Product, at this case the product is deleted from DB by its id
     * @param id is parameter that coming from url and then is used for getting specific Product by its id from DB
     * @return  it redirects to product list view
     * @see Product
     */
    @RequestMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        serviceProduct.deleteById(id);
        return "redirect:/product/list";
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
