package com.recipesback.service;

import com.recipesback.model.Recipe;
import com.recipesback.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeRepository repository;

    public RecipeService(RecipeRepository repository) {
        this.repository = repository;
    }

    public List<Recipe> getAllRecipes() {
        return repository.findAll();
    }

    public Optional<Recipe> getRecipeById(Long id) {
        return repository.findById(id);
    }

    public Recipe createRecipe(Recipe recipe) {
        return repository.save(recipe);
    }

    public Optional<Recipe> updateRecipe(Long id, Recipe recipe) {
        return repository.findById(id)
                .map(existing -> {
                    recipe.setId(id);
                    return repository.save(recipe);
                });
    }

    public boolean deleteRecipe(Long id) {
        return repository.findById(id)
                .map(recipe -> {
                    repository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
