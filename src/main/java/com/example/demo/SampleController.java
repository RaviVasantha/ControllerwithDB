package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SampleController {

    @Autowired
    SampleRepo sr;

    @GetMapping("/showForm")
    public String showForm(Model model) {
        model.addAttribute("entity", new MyEntity());
        return "form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MyEntity entity) {
        sr.save(entity);
        return "redirect:/form";
    }

    @GetMapping("/getEntityById/{id}")
    public String getEntityById(@PathVariable int id, Model model) {
        MyEntity entity = sr.findById(id).orElse(null);
        model.addAttribute("entity", entity);
        return "details";
    }

    @GetMapping("/findAll")
    public String findAll(Model model) {
        model.addAttribute("entities", sr.findAll());
        return "list";
        
    }
    

}

