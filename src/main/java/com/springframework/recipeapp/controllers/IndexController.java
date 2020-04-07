package com.springframework.recipeapp.controllers;

import com.springframework.recipeapp.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@Slf4j
public class IndexController {

    private final RecipeService recipeService;

    @RequestMapping({"", "/", "/index", " "})
    public String getIndexPage(Model model) {
        log.debug("Getting Index Page...");
        model.addAttribute("recipes", recipeService.getRecipes());
        System.out.println(recipeService.getRecipes());
        return "index";
    }
}
