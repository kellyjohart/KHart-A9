package com.coderscampus.assignment9.service;
import com.coderscampus.assignment9.domain.Recipe;
import com.coderscampus.assignment9.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.getAll();
    }

    public List<Recipe> getFilteredRecipes(Predicate<Recipe> filteredCriteria) {
        return getAllRecipes().stream()
                .filter(filteredCriteria)
                .collect(Collectors.toList());
    }
}
