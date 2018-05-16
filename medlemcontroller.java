package com.example.demo;

import com.example.demo.medlem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
    public class medlemcontroller{

            ArrayList<medlem>medlemmer = new ArrayList<medlem>();


        @GetMapping("/addmembersite")
        public String addmembersite(Model model) {

            model.addAttribute("medlem",new medlem());
            return "addmembersite";
        }

        @PostMapping("/addmembersite")
        public String medlem(@ModelAttribute medlem medlem, BindingResult bindingResult) {

            medlemmer.add(medlem);
            return "redirect:/";
        }

    }
