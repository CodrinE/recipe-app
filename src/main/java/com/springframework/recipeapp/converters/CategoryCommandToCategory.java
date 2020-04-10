package com.springframework.recipeapp.converters;

import com.springframework.recipeapp.commands.CategoryCommand;
import com.springframework.recipeapp.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Nullable
    @Synchronized
    @Override
    public Category convert(CategoryCommand source) {
        if(isNull(source)){
            return null;
        }
        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}
