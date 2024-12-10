package com.app.tp.controller;

import com.app.tp.model.Developer;
import com.app.tp.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final DeveloperService developerService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("developer", new Developer());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("developer") @Valid Developer developer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        // Check if username already exists
        if (developerService.getAllDevelopers().stream().anyMatch(d -> d.getLogin().equals(developer.getLogin()))) {
            bindingResult.rejectValue("login", "error.developer", "Login already exists.");
            return "register";
        }
        developerService.registerDeveloper(developer);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
