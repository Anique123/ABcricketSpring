package com.exam.ab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/members")
    public String members(){
        return "members";
    }

    @GetMapping("/matches")
    public String matches() {
        return "matches";
    }

    @GetMapping("/competitions")
    public String competitions() {
        return "competitions";
    }

    @GetMapping("/training")
    public String training() {
        return "training";
    }

    @GetMapping("/calendar")
    public String calendar(){
        return "calendar";
    }

}