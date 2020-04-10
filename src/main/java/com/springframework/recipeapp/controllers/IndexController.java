package com.springframework.recipeapp.controllers;

import com.springframework.recipeapp.commands.RecipeCommand;
import com.springframework.recipeapp.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        return "index";
    }

    @RequestMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand saveCommand = recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/show/" + saveCommand.getId();
    }
}
