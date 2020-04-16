package com.springframework.recipeapp.services;

import com.springframework.recipeapp.domain.Recipe;
import com.springframework.recipeapp.domain.repositories.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {
        log.debug("Received a file");

        try {
            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] byteObject = new Byte[file.getBytes().length];

            int i = 0;

            for(byte b : file.getBytes()){
              byteObject[i++] = b;
            }

            recipe.setImage(byteObject);

            recipeRepository.save(recipe);

        } catch (IOException e) {
            log.error("Error occurred: ", e);
            e.printStackTrace();
        }
    }
}
