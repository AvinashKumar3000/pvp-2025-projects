package com.example.Social_media.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Data
class Student {
    String name;
    int age;
    float marks;
}

@RestController
@RequestMapping("/students")
public class Sample {
    private Student[] arr;
    private int index;
    public Sample() {
         this.arr = new Student[5];
         this.index = 0;
    }

    @GetMapping("/all")
    public Student[] getAll() {
        return this.arr;
    }
    @GetMapping("/by/{id}")
    public Student getById(@PathVariable int id) {
        return this.arr[id];
    }

    @PostMapping("/")
    public String createNew(@RequestBody Student newObj) {
        this.arr[this.index] = newObj;
        this.index++;
        return "new student created";
    }

//    @DeleteMapping("/all")
//    @DeleteMapping("/by/id")
//    @PutMapping("/id")
}
