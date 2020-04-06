package com.springframework.recipeapp.controllers;

import com.springframework.recipeapp.domain.repositories.CategoryRepository;
import com.springframework.recipeapp.domain.repositories.UnitOfMeasureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class IndexController {

    public CategoryRepository categoryRepository;
    public UnitOfMeasureRepository unitOfMeasureRepository;

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        return "index";
    }
}
