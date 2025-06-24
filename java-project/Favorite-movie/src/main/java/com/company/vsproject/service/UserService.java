package com.company.vsproject.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.company.vsproject.entity.Movie;
import com.company.vsproject.entity.User;
import com.company.vsproject.repository.MovieRepository;
import com.company.vsproject.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public User getUserById(Long id) throws Exception {
        return userRepository.findById(id)
            .orElseThrow(() -> new Exception("User not found with ID: " + id));
    }

    public User getUserByUsername(String username) throws Exception {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new Exception("User not found with username: " + username));
    }

    public User getUserByEmail(String email) throws Exception {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new Exception("User not found with email: " + email));
    }

    public User loginUser(String email, String password) throws Exception {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new Exception("Invalid email");
        }

        User user = userOpt.get();
        if (!user.getPassword().equals(password)) {
            throw new Exception("Incorrect password");
        }

        return user;
    }

    public User createNewUser(User newUser) throws Exception {
        Optional<User> existingUser = userRepository.findByEmail(newUser.getEmail());
        if (existingUser.isPresent()) {
            throw new Exception("User already exists with email: " + newUser.getEmail());
        }
        return userRepository.save(newUser);
    }

    @Transactional
    public User addFavorite(Long userId, Long movieId) throws Exception {
        User user = getUserById(userId);
        Movie movie = movieRepository.findById(movieId)
            .orElseThrow(() -> new Exception("Movie not found with ID: " + movieId));

        if (!user.getFavoriteMovies().contains(movie)) {
            user.getFavoriteMovies().add(movie);
        }
        return user;
    }

    @Transactional
    public User removeFavorite(Long userId, Long movieId) throws Exception {
        User user = getUserById(userId);
        Movie movie = movieRepository.findById(movieId)
            .orElseThrow(() -> new Exception("Movie not found with ID: " + movieId));

        user.getFavoriteMovies().remove(movie);
        return user;
    }
}
