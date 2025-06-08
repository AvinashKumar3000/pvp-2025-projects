package com.example.Social_media.service;

import com.example.Social_media.model.Post;
import com.example.Social_media.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getPublicPosts() {
        return postRepository.findByIsPublicTrue();
    }

    public List<Post> getPostsByUser(Long userId) {
        return postRepository.findByUserId(userId);
    }

    public void createPost(Post post) {
        postRepository.save(post);
    }

}
