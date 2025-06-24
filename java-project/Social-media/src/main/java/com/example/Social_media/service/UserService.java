package com.example.Social_media.service;

import com.example.Social_media.model.User;
import com.example.Social_media.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Boolean saveUser(User user) {
        try {       
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User findByUsernameAndPass(String username, String password) {
        List<User> allUsers = userRepository.findAll();
        User foundUser = null;
        for(User user: allUsers) {
            if(user.getUsername() == username && user.getPassword() == password) {
                foundUser = user;
                break;
            }
        }
        return foundUser;
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public String resetPassword(Long userId, String newPassword) {
        User foundUser = userRepository.findById(userId).orElse(null);
        if(foundUser==null) {
            return "no user found";
        }else{
            foundUser.setPassword(newPassword);
            return "password reset Done";
        }
    }
}









