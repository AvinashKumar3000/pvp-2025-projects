package com.company.vsproject.controllers;

import org.springframework.web.bind.annotation.*;

import com.company.vsproject.dto.ResponseDto;
import com.company.vsproject.entity.User;
import com.company.vsproject.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseDto getUserById(@PathVariable Long id) {
        ResponseDto response = new ResponseDto();
        try {
            User user = userService.getUserById(id);
            response.setSuccess(true);
            response.setData(user);
            response.setMessage("User found");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("/by-username/{username}")
    public ResponseDto getUserByUsername(@PathVariable String username) {
        ResponseDto response = new ResponseDto();
        try {
            User user = userService.getUserByUsername(username);
            response.setSuccess(true);
            response.setData(user);
            response.setMessage("User found by username");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("/by-email/{email}")
    public ResponseDto getUserByEmail(@PathVariable String email) {
        ResponseDto response = new ResponseDto();
        try {
            User user = userService.getUserByEmail(email);
            response.setSuccess(true);
            response.setData(user);
            response.setMessage("User found by email");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping("/login")
    public ResponseDto loginUser(@RequestParam String email, @RequestParam String password) {
        ResponseDto response = new ResponseDto();
        try {
            User user = userService.loginUser(email, password);
            response.setSuccess(true);
            response.setData(user);
            response.setMessage("Login successful");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping("/register")
    public ResponseDto register(@RequestBody User newUser) {
        ResponseDto response = new ResponseDto();
        try {
            User created = userService.createNewUser(newUser);
            response.setSuccess(true);
            response.setData(created);
            response.setMessage("User registered successfully");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping("/{userId}/favorite/{movieId}")
    public ResponseDto addFavorite(@PathVariable Long userId, @PathVariable Long movieId) {
        ResponseDto response = new ResponseDto();
        try {
            User updatedUser = userService.addFavorite(userId, movieId);
            response.setSuccess(true);
            response.setData(updatedUser);
            response.setMessage("Movie added to favorites");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/{userId}/favorite/{movieId}")
    public ResponseDto removeFavorite(@PathVariable Long userId, @PathVariable Long movieId) {
        ResponseDto response = new ResponseDto();
        try {
            User updatedUser = userService.removeFavorite(userId, movieId);
            response.setSuccess(true);
            response.setData(updatedUser);
            response.setMessage("Movie removed from favorites");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
