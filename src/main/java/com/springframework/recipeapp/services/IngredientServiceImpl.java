package com.springframework.recipeapp.services;

import com.springframework.recipeapp.commands.IngredientCommand;
import com.springframework.recipeapp.converters.IngredientCommandToIngredient;
import com.springframework.recipeapp.converters.IngredientToIngredientCommand;
import com.springframework.recipeapp.domain.Ingredient;
import com.springframework.recipeapp.domain.Recipe;
import com.springframework.recipeapp.domain.repositories.RecipeRepository;
import com.springframework.recipeapp.domain.repositories.UnitOfMeasureRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService{

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if(!recipeOptional.isPresent()){
            log.error("Not found Recipe with id: {}", recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientToIngredientCommand::convert).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            log.error("Not found Ingredient with id: {}", ingredientId);
        }

        return ingredientCommandOptional.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){
            log.error("Not found Recipe with id: {}", command.getRecipeId());
            return new IngredientCommand();
        }else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient foundIngredient = ingredientOptional.get();
                foundIngredient.setDescription(command.getDescription());
                foundIngredient.setAmount(command.getAmount());
                foundIngredient.setId(command.getId());
                foundIngredient.setUnitOfMeasure(unitOfMeasureRepository
                .findById(command.getUnitOfMeasure().getId())
                .orElseThrow(() -> new RuntimeException("Unit of Measure not found")));
            }else {
                recipe.addIngredients(Objects.requireNonNull(ingredientCommandToIngredient.convert(command)));
            }

            Recipe saveRecipe = recipeRepository.save(recipe);

            return  ingredientToIngredientCommand.convert(saveRecipe
                    .getIngredients()
                    .stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst().get());
        }
    }
}
