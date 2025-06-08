package com.example.Social_media.repository;

import com.example.Social_media.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByIsPublicTrue();
    List<Post> findByUserId(Long userId);
}
