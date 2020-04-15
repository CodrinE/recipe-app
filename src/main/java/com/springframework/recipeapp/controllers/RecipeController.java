package com.springframework.recipeapp.controllers;

import com.springframework.recipeapp.commands.RecipeCommand;
import com.springframework.recipeapp.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@AllArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable Long id, Model model){
        model.addAttribute("recipe", recipeService.findById(id));

        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return  "recipe/recipeform";
    }

    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand saveCommand = recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/" + saveCommand.getId() + "/show";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id){
            log.debug("Deleting recipe with id {}", id);

            recipeService.deleteById(Long.valueOf(id));

            return "redirect:/";
    }
}
