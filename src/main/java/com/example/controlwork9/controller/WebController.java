package com.example.controlwork9.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/main")
    public String showMain() {
        return "main";
    }
}
