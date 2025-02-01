package com.coderscampus.assignment9;

import com.coderscampus.assignment9.domain.Recipe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.coderscampus.assignment9.service.RecipeService;
import com.coderscampus.assignment9.service.FileService;
import com.coderscampus.assignment9.controller.RecipeController;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class A9Tests {

    @Autowired
    private RecipeController recipeController;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private FileService fileService;

    @Test
    void contextLoads() {
        // Verify that critical components are properly initialized
        assertThat(recipeController).isNotNull();
        assertThat(recipeService).isNotNull();
        assertThat(fileService).isNotNull();
    }

    @Test
    void recipesAreLoaded() {
        // Verify that recipes are actually loaded
        assertThat(recipeService.getAllRecipes()).isNotEmpty();
    }

    @Test
    void filteringWorks() {
        // Test that filtering functions work
        assertThat(recipeService.getVeganRecipes()).isNotNull();
        assertThat(recipeService.getGlutenFreeRecipes()).isNotNull();
        assertThat(recipeService.getVegetarianRecipes()).isNotNull();
        assertThat(recipeService.getVeganAndGlutenFreeRecipes()).isNotNull();
        assertThat(recipeService.getAllRecipes()).isNotNull();



    }

    @Test
    void verifyCompleteRecipeParsing() {
        List<Recipe> allRecipes = recipeService.getAllRecipes();

        // Verify we have recipes
        assertThat(allRecipes)
                .isNotEmpty()  // at least has some recipes
                .isNotNull();  // list itself isn't null

        // Optional: sanity check for a reasonable range
        assertThat(allRecipes.size())
                .isGreaterThan(0)    // has at least 1 recipe
                .isLessThan(1000);   // some reasonable upper limit

        // Check that no recipe has null or invalid values
        allRecipes.forEach(recipe -> {
            // Check required fields are not null
            assertThat(recipe.getTitle())
                    .isNotNull()
                    .isNotEmpty();

            assertThat(recipe.getInstructions())
                    .isNotNull()
                    .isNotEmpty();

            // Check numeric fields are valid
            assertThat(recipe.getCookingMinutes())
                    .isNotNull()
                    .isGreaterThanOrEqualTo(0);

            assertThat(recipe.getPreparationMinutes())
                    .isNotNull()
                    .isGreaterThanOrEqualTo(0);

            assertThat(recipe.getPricePerServing())
                    .isNotNull()
                    .isGreaterThan(0);

            assertThat(recipe.getReadyInMinutes())
                    .isNotNull()
                    .isGreaterThan(0);

            assertThat(recipe.getServings())
                    .isNotNull()
                    .isGreaterThan(0);

            // Check boolean fields are not null
            assertThat(recipe.getDairyFree()).isNotNull();
            assertThat(recipe.getGlutenFree()).isNotNull();
            assertThat(recipe.getVegan()).isNotNull();
            assertThat(recipe.getVegetarian()).isNotNull();
        });
    }

    @Test
    void verifyRecipeFilters() {
        List<Recipe> allRecipes = recipeService.getAllRecipes();
        List<Recipe> veganRecipes = recipeService.getVeganRecipes();
        List<Recipe> glutenFreeRecipes = recipeService.getGlutenFreeRecipes();
        List<Recipe> vegetarianRecipes = recipeService.getVegetarianRecipes();

        // Verify vegan filter
        assertThat(veganRecipes)
                .allMatch(Recipe::getVegan)
                .hasSize((int) allRecipes.stream()
                        .filter(Recipe::getVegan)
                        .count());

        // Verify gluten-free filter
        assertThat(glutenFreeRecipes)
                .allMatch(Recipe::getGlutenFree)
                .hasSize((int) allRecipes.stream()
                        .filter(Recipe::getGlutenFree)
                        .count());

        // Verify vegetarian filter
        assertThat(vegetarianRecipes)
                .allMatch(Recipe::getVegetarian)
                .hasSize((int) allRecipes.stream()
                        .filter(Recipe::getVegetarian)
                        .count());
    }
}
