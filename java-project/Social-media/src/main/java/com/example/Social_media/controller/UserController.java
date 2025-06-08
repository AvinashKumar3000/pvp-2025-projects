package com.example.Social_media.controller;

import com.example.Social_media.model.User;
import com.example.Social_media.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        Boolean result = userService.saveUser(user); // password stored in plain text
        if(result){ 
            return "redirect:/login";
        }
        model.addAttribute("error", "User or email already exists");
        return "/register";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username,@RequestParam String password,Model model) {
        User user = userService.findByUsernameAndPass(username, password);
        if (user != null) {
            return "redirect:/posts/public/all/" + user.getId().toString(); // Redirect to user profile
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}
