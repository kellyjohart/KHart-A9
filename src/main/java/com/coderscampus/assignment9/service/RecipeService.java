package com.coderscampus.assignment9.service;
import com.coderscampus.assignment9.domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RecipeService {
    private final List<Recipe> recipes = new ArrayList<>();

    @Autowired
    private FileService fileService;

    @PostConstruct
    public void loadRecipes() throws IOException {
        recipes.addAll(fileService.readFile());
    }

    public List<Recipe> getAllRecipes() {
        return new ArrayList<>(recipes);
    }

    public List<Recipe> getGlutenFreeRecipes() {
        return recipes.stream()
                .filter(Recipe::getGlutenFree)
                .collect(Collectors.toList());
    }

    public List<Recipe> getVeganRecipes() {
        return recipes.stream()
                .filter(Recipe::getVegan)
                .collect(Collectors.toList());
    }

    public List<Recipe> getVeganAndGlutenFreeRecipes() {
        return recipes.stream()
                .filter(recipe -> recipe.getVegan() && recipe.getGlutenFree())
                .collect(Collectors.toList());
    }

    public List<Recipe> getVegetarianRecipes() {
        return recipes.stream()
                .filter(Recipe::getVegetarian)
                .collect(Collectors.toList());
    }
}
