package ru.bulish.spring.geek_shop_boot.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bulish.spring.geek_shop_boot.entity.Product;
import ru.bulish.spring.geek_shop_boot.entity.ProductSearchConditional;
import ru.bulish.spring.geek_shop_boot.entity.UserEntity;
import ru.bulish.spring.geek_shop_boot.service.RoleService;
import ru.bulish.spring.geek_shop_boot.service.ServiceProduct;
import ru.bulish.spring.geek_shop_boot.service.UserService;

import javax.validation.Valid;

/**
 * Class Controller for actions with Product entity
 * @author Sorokina
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    /**
     * Field serviceProduct is injected in this class for giving access to service layer
     * @see ServiceProduct
     */
    private final UserService userService;
    /**
     * Field roleService is injected in this class for giving access to service layer
     * @see RoleService
     */
    private final RoleService roleService;


    /**
     * Method show a particular view according to url and also using coming parameters calling methods that
     * do some actions with entity User, at this case the list of all users
     * @param model is responsible for interacting between Controller and View, also keeping variables so that display them in the view
     * @param pageNum  is parameter that coming from view and then is used for pagination view of all Products
     * @return view HTML
     * @see UserEntity
     */
    @GetMapping("/list")
    public String getUsers(@RequestParam(required = false) Integer pageNum, Model model) {
        final int pageSize = 5;

        Pageable pageRequest = PageRequest.of(pageNum == null ? 0 : pageNum, pageSize);
        Page<UserEntity> page = userService.findAllByPage(pageRequest);

        model.addAttribute("page", page);

        return "user/list";
    }

    /**
     * Method show a particular view that is for registration a user
     * @param model is responsible for interacting between Controller and View, also keeping variables so that display them in the view
     * @return view HTML
     * @see UserEntity
     */
    @GetMapping("/registration")
    public String getRegistration(Model model) {
        model.addAttribute("user", new UserEntity());

        return "user/registration";
    }

    /**
     * Method show a particular view according to url and also using coming parameters calling methods that
     * do some actions with entity User
     * @param user this is model that keeps created object
     * @param result allows to check if all params of User object were filled according applied rules
     * @return if validation goes successfully it saves user and redirects to login view otherwise returns the form that has to be repeated registration with
     * written mistakes
     * @see UserEntity
     */
    @PostMapping("/registration")
    public String register(@Valid @ModelAttribute("user")UserEntity user, BindingResult result) {
        if (result.hasErrors()) {
            return "/user/registration";
        }
            userService.save(user);
            return "redirect:/login";


    }
    /**
     * Method show a particular view according to url and also using coming parameters calling methods that
     * do some actions with entity Product
     * @param model is responsible for interacting between Controller and View and keeping variables so that display them in the view
     * @return view HTML form for creating a new product
     * @see UserEntity
     */
    @GetMapping("/new")
    public String create(Model model) {

        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", new UserEntity());
        return "user/user_form";
    }
    /**
     * Method show a particular view according to url and also using coming parameters calling methods that
     * do some actions with entity User
     * @param user this is model that keeps created object
     * @param model is responsible for interacting between Controller and View and keeping variables so that display them in the view
     * @param result allows to check if all params of User object were filled according applied rules
     * @return if validation goes successfully it redirects to user list view otherwise returns the form for creation with
     * written mistakes
     * @see UserEntity
     */
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("user") UserEntity user, BindingResult result, Model model) {
        model.addAttribute("roles", roleService.findAll());
        if (result.hasErrors()) {
            return "/user/user_form";
        }
        userService.save(user);
        return "redirect:/user/list";
    }

    /**
     * Method show a particular view according to url and also using coming parameters calling methods that
     * do some actions with entity User
     * @param userId keeps the specific id of the user
     * @param enable is boolean variable that point the action that must happen - to disable or enable a user
     * @return redirects to the specific view
     * @see UserEntity
     */
    @GetMapping("/enable")
    public String setEnableUser(@RequestParam Long userId, @RequestParam Boolean enable) {
        userService.setEnable(userId, enable);

        return "redirect:/user/list";
    }

}
