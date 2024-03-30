package com.crud.operation.hyrookin.controller;

import com.crud.operation.hyrookin.entity.UserEntity;
import com.crud.operation.hyrookin.repository.UserRepository;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String redirectToRegister(Model model) {
        return "redirect:/register"; // Redirect to the register page
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "register";
    }

    @PostMapping("/register")
public String registerUser(@ModelAttribute("user") @Valid UserEntity user, BindingResult result) {
    if (result.hasErrors()) {
        // If there are validation errors, return to the registration form
        return "register";
    }
    
    // Check if the username already exists
    UserEntity existingUser = userRepository.findByUsername(user.getUsername());
    if (existingUser != null) {
        // If the username already exists, add an error message and return to the registration form
        result.rejectValue("username", null, "Username already exists");
        return "register";
    }
    
    // Save the new user
    userRepository.save(user);
    return "redirect:/login";
}

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") UserEntity user, Model model) {
        // Logic for user authentication and login
        // Redirect to home page after successful login
        return "redirect:/home";
    }

    // Ensure only home page is visible after successful login
    @GetMapping("/home")
    public String home() {
        return "index"; // This will return the index.html template
    }
}
