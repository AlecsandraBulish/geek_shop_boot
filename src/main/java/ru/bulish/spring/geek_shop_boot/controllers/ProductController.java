package ru.bulish.spring.geek_shop_boot.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.bulish.spring.geek_shop_boot.entity.Product;
import ru.bulish.spring.geek_shop_boot.entity.ProductIncorectData;
import ru.bulish.spring.geek_shop_boot.entity.ProductSearchConditional;
import ru.bulish.spring.geek_shop_boot.service.CategoryService;
import ru.bulish.spring.geek_shop_boot.service.ServiceProduct;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;


@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ServiceProduct serviceProduct;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/{id}")
    public String showOneProduct(@PathVariable("id") Long id, Model model) {
        Product product = serviceProduct.getProductById(id);
        if (product == null) {
            throw new NoSuchElementException ("There is no such product in Database");
        }
        model.addAttribute("product", product);
        return "products/oneproduct";
    }


    @GetMapping("/list")
    public String showAllProducts(Model model,
                                  @RequestParam(name = "page") Optional<Integer> page,
                                  ProductSearchConditional conditional) {

        Page<Product> products = serviceProduct.getProductsByConditional(conditional, page);
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.findAll());

        return "products/all_products";


    }
    @GetMapping ("/new")
    public String showTheForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "products/product_form";
    }

    @PostMapping
    public String addNewProduct(@Valid @ModelAttribute("product") Product product, BindingResult result,
                                @RequestParam(name = "image")MultipartFile image) {
        if (result.hasErrors()) {
            return "/products/product_form";
        }
            serviceProduct.saveProductWithImage(image,product);
            return "redirect:/product/list";
        }


    @GetMapping("/{id}/updateInfo")
    public String updateProduct(@PathVariable("id") Long id, Model model) {
        Product product = serviceProduct.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());

        return "products/product_form";
    }

    @RequestMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        serviceProduct.deleteById(id);
        return "redirect:/product/list";
    }



    @ExceptionHandler
    public ResponseEntity<ProductIncorectData> notFoundExceptionHandler(Exception ex) {
        ProductIncorectData data = new ProductIncorectData();
        data.setInfo(ex.getMessage());
        return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
//        ModelAndView mav = new ModelAndView("not_found");
//        mav.addObject("mess", ex.getMessage());
//        mav.setStatus(HttpStatus.NOT_FOUND);
//        return mav;
    }

}
