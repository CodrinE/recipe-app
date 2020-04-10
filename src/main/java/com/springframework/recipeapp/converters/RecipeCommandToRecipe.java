package com.springframework.recipeapp.converters;

import com.springframework.recipeapp.commands.RecipeCommand;
import com.springframework.recipeapp.domain.Recipe;
import lombok.AllArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@AllArgsConstructor
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if(isNull(source)){
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setSource(source.getSource());
        recipe.setDescription(source.getDescription());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setServings(source.getServings());
        recipe.setUrl(source.getUrl());
        recipe.setDirection(source.getDirection());

        recipe.setNotes(notesConverter.convert(source.getNotes()));

        if(isNull(source.getCategories()) && source.getCategories().size() > 0){
            source.getCategories().forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }

        if(isNull(source.getIngredients()) && source.getIngredients().size() > 0){
            source.getIngredients().forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipe;
    }
}
