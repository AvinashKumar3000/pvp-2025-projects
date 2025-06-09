package com.example.Social_media.controller;

import com.example.Social_media.model.Post;
import com.example.Social_media.model.User;
import com.example.Social_media.service.PostService;
import com.example.Social_media.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final UserService userService;

    @GetMapping("/public/all/{userId}")
    public String getAll(@PathVariable Long userId, Model model) {
        User user = userService.findUserById(userId);
        if (user == null) {
            return "error";
        }

        List<Post> posts = postService.getPublicPosts();
        List<Post> myPosts = postService.getPostsByUser(userId);
        model.addAttribute("posts", posts);
        model.addAttribute("myPosts", myPosts);
        model.addAttribute("user", user);

        return "dashboard";
    }

    @GetMapping("/add/{userId}")
    public String showAddPostForm(@PathVariable Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "add-new-post"; 
    }

    @PostMapping("/add/{userId}")
    public String addPost(
            @PathVariable Long userId,
            @RequestParam String imageUrl,
            @RequestParam String description,
            @RequestParam(defaultValue = "false") boolean isPublic
    ) {
        User user = userService.findUserById(userId);
        if (user == null) {
            return "redirect:/login";
        }
        Post newPost = new Post();
        newPost.setUser(user);
        newPost.setDescription(description);
        newPost.setPublic(isPublic);
        newPost.setImageUrl(imageUrl);
        postService.createPost(newPost);
        return "redirect:/posts/public/all/" + userId.toString();
    }
}
