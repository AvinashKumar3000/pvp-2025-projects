package com.company.vsproject.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/calc")
    public String calc(
        @RequestParam("type") String type,
        @RequestParam("a") int a,
        @RequestParam("b") int b
    ) {
        if(type.equals("add")) {
            Integer res = a + b;
            return res.toString();
        }else if(type.equals("sub")) {
            Integer res = a - b;
            return res.toString();
        }
        return "invalid type " + type;
    }
    @PutMapping
    public String updateValue() {
        return "called updateValue method";
    }
    @DeleteMapping
    public String deleteValue() {
        return "called deleteValue method";
    }
    @PostMapping
    public String createValue() {
        return "called createValue method";
    }

    @RequestMapping("/sample/*")
    public String notFound() {
        return "Not found page";
    }
}
