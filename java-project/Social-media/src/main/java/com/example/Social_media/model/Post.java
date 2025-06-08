package com.example.Social_media.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String description;
    private boolean isPublic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
