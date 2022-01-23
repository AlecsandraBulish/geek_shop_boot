package ru.bulish.spring.geek_shop_boot.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bulish.spring.geek_shop_boot.entity.UserEntity;
import ru.bulish.spring.geek_shop_boot.service.RoleService;
import ru.bulish.spring.geek_shop_boot.service.UserService;

import javax.validation.Valid;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/list")
    public String getUsers(@RequestParam(required = false) Integer pageNum, Model model) {
        final int pageSize = 5;

        Pageable pageRequest = PageRequest.of(pageNum == null ? 0 : pageNum, pageSize);
        Page<UserEntity> page = userService.findAllByPage(pageRequest);

        model.addAttribute("page", page);

        return "user/list";
    }

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        model.addAttribute("user", new UserEntity());

        return "user/registration";
    }

    @PostMapping("/registration")
    public String register(@Valid @ModelAttribute("user")UserEntity user, BindingResult result) {
        if (result.hasErrors()) {
            return "/user/registration";
        }
            userService.save(user);
            return "redirect:/login";


    }
    @GetMapping("/new")
    public String create(Model model) {

        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", new UserEntity());
        return "user/user_form";
    }
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("user") UserEntity user, BindingResult result, Model model) {
        model.addAttribute("roles", roleService.findAll());
        if (result.hasErrors()) {
            return "/user/user_form";
        }
//        if (!user.getPassword().equals(user.getMatchingPassword())) {
//            result.rejectValue("password", "", "Password not matching");
//            return "user_form";
//        }

//        logger.info("Updating user with id {}", user.getId());
        userService.save(user);
        return "redirect:/user/list";
    }


    @GetMapping("/enable")
    public String setEnableUser(@RequestParam Long userId, @RequestParam Boolean enable) {
        userService.setEnable(userId, enable);

        return "redirect:/user/list";
    }

}
