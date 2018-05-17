package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/members")
    public String members(){
        return "members";
    }
    @GetMapping("/")
    public String index(){
        return "index";
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
    @GetMapping("/confirmMember")
    public  String confirmMember() {
        return "confirmMember";
    }
    @GetMapping("/vismedlemmer")
    public  String vismedlemmer()  {
        return "vismedlemmer";
    }
}

