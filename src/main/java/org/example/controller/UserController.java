package org.example.controller;

import org.example.model.User;
import org.example.service.UserServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getPeopleList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/users";
    }

    @GetMapping("/admin/new")
    public String newUser(Model model) {
        model.addAttribute(new User());
        return "users/new";
    }


    @PostMapping("/admin")
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit")
    public String edit(Model model, @RequestParam(value ="id") Long id) {

        if (userService.checkId(id)) {
            return "users/unknownUser";

        }

        model.addAttribute("user", userService.getUserById(id));
        return "users/edit";
    }

    @PostMapping("/admin/edit")
    public String update(@ModelAttribute("user") User user, @RequestParam(value ="id") Long id) {

        if (userService.checkId(id)) {
            return "users/unknownUser";
        }

        userService.update(id, user);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String getUserById(Model model) {
        Long id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        model.addAttribute("user", userService.getUserById(id));
        return "users/showuser";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id") Long id) {

        if (userService.checkId(id)) {
            return "users/unknownUser";
        }

        userService.remove(id);
        return "redirect:/admin";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "users/login";
    }

}
