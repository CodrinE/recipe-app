package com.springframework.recipeapp.controllers;

import com.springframework.recipeapp.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class IndexController {

    private final RecipeService recipeService;

    @RequestMapping({"", "/", "/index", " "})
    public String getIndexPage(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());
        System.out.println(recipeService.getRecipes());
        return "index";
    }
}
