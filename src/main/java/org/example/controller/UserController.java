package org.example.controller;

import org.example.dao.UserDaoImpl;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getPeopleList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute(new User());
        return "users/new";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, @RequestParam(value ="id", required = false) Integer id) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/edit";
    }


    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String update(@ModelAttribute("user") User user, @RequestParam(value ="id", required = false) Integer id) {
        userService.update(id, user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id", required = false) Integer id) {
        userService.remove(id);
        return "redirect:/users";
    }
}
