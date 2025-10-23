package com.recipesback.service;

import com.recipesback.model.Ingredient;
import com.recipesback.repository.IngredientRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    private final IngredientRepository repository;

    public IngredientService(IngredientRepository repository) {
        this.repository = repository;
    }

    public List<Ingredient> getAllIngredients() {
        return repository.findAll();
    }

    public Optional<Ingredient> getIngredientById(Long id) {
        return repository.findById(id);
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return repository.save(ingredient);
    }

    public Optional<Ingredient> updateIngredient(Long id, Ingredient ingredient) {
        return repository.findById(id)
                .map(existing -> {
                    ingredient.setId(id);
                    return repository.save(ingredient);
                });
    }

    public boolean deleteIngredient(Long id) {
        return repository.findById(id)
                .map(ingredient -> {
                    repository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
