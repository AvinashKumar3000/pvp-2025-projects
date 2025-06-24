package com.example.Social_media.controller;

import com.example.Social_media.model.Todo;
import com.example.Social_media.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/all")
    public String getAll(Model model) {
        int[] arr = new int[2];
        arr[0] = 100;
        arr[1] = 200;
        model.addAttribute("name","ece-good-students");
        model.addAttribute("data",arr);
        return  "sample";
    }
}

