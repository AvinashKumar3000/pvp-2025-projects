package com.example.Social_media.service;


import com.example.Social_media.model.Todo;
import com.example.Social_media.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> getAllItems() {
        return todoRepository.findAll();
    }
}
