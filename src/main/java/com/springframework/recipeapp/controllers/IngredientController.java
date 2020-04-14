package com.springframework.recipeapp.controllers;

import com.springframework.recipeapp.commands.IngredientCommand;
import com.springframework.recipeapp.commands.RecipeCommand;
import com.springframework.recipeapp.commands.UnitOfMeasureCommand;
import com.springframework.recipeapp.services.IngredientService;
import com.springframework.recipeapp.services.RecipeService;
import com.springframework.recipeapp.services.UnitOfMeasureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@AllArgsConstructor
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model){
        log.debug("Getting ingredient list for recipe with id {}", recipeId);

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipeIngredients(@PathVariable String recipeId,
                                        @PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

        return  "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/new")
    public String newRecipe(@PathVariable String recipeId, Model model){
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));

        model.addAttribute("ingredient", ingredientCommand);

        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());

        model.addAttribute("unitOfMeasureList", unitOfMeasureService.listAllMeasureUnits());

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

        model.addAttribute("unitOfMeasureList", unitOfMeasureService.listAllMeasureUnits());

        return "recipe/ingredient/ingredientform";
    }

    @PostMapping
    @RequestMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand){
        IngredientCommand save = ingredientService.saveIngredientCommand(ingredientCommand);

        log.debug("Saved ingredient with id {} for recipeId  {}",
                save.getId(), save.getRecipeId());

        return "redirect:/recipe/" + save.getRecipeId() + "/ingredient/" + save.getId() + "/show";
    }
}
