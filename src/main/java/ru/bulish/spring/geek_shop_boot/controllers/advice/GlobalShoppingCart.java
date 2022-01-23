package ru.bulish.spring.geek_shop_boot.controllers.advice;


import org.springframework.data.crossstore.ChangeSetPersister.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import ru.bulish.spring.geek_shop_boot.component.ShoppingCart;

@ControllerAdvice
public class GlobalShoppingCart {

    @ModelAttribute("shoppingCart")
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException e) {
        ModelAndView mav = new ModelAndView("not_found");
        mav.setStatus(HttpStatus.NOT_FOUND);
        return mav;
    }
    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(Exception ex) {
        ModelAndView mav = new ModelAndView("not_found");
        mav.setStatus(HttpStatus.BAD_REQUEST);
        return mav;
    }

}
