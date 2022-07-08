package com.rtkit.fifth.element.kms.controller;

import com.rtkit.fifth.element.kms.model.dto.UserRegistrationInfo;
import com.rtkit.fifth.element.kms.model.entity.User;
import com.rtkit.fifth.element.kms.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    private static final String VIEW = "registration";

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    // TODO: проверить работоспособность
    @GetMapping("/registration")
    public ModelAndView registration(Model model) {
        model.addAttribute("userForm", new User());
        return new ModelAndView(VIEW);
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") UserRegistrationInfo userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return VIEW;
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return VIEW;
        }

        if (!userService.saveUser(userForm)) {
            model.addAttribute("emailError", "Пользователь с таким email уже существует");
            return VIEW;
        }

        return "redirect:/";
    }
}