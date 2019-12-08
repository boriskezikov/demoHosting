package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/android")
public class Controller {

    @GetMapping
    public String printMyString() {
        return "This is my message incoming ";
    }
}
