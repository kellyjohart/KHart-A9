package com.coderscampus.assignment9.service;
import com.coderscampus.assignment9.domain.Recipe;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.apache.commons.csv.QuoteMode;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    @Autowired
    private ResourceLoader resourceLoader;

    public List<Recipe> readFile() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:recipes.txt");
        List<Recipe> recipes = new ArrayList<>();


        System.out.println("File exists: " + resource.exists());
        System.out.println("File size: " + resource.contentLength() + " bytes");

        Reader in = new InputStreamReader(resource.getInputStream());

        String[] HEADERS = {
                "Cooking Minutes",
                "Dairy Free",
                "Gluten Free",
                "Instructions",
                "Preparation Minutes",
                "Price Per Serving",
                "Ready In Minutes",
                "Servings",
                "Spoonacular Score",
                "Title",
                "Vegan",
                "Vegetarian"
        };

        CSVFormat csvFormat = CSVFormat.DEFAULT
                .builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .setIgnoreSurroundingSpaces(true)
                .setEscape('\\')
                .setQuote('"')
                .setQuoteMode(QuoteMode.ALL)
                .setDelimiter(',')
                .setTrailingDelimiter(true)
                .build();

        CSVParser parser = csvFormat.parse(in);
        for (CSVRecord record : parser) {
            Recipe recipe = new Recipe();
            recipe.setCookingMinutes(Integer.parseInt(record.get("Cooking Minutes")));
            recipe.setDairyFree(Boolean.parseBoolean(record.get("Dairy Free")));
            recipe.setGlutenFree(Boolean.parseBoolean(record.get("Gluten Free")));
            recipe.setInstructions(record.get("Instructions"));
            recipe.setPreparationMinutes(Double.parseDouble(record.get("Preparation Minutes")));
            recipe.setPricePerServing(Double.parseDouble(record.get("Price Per Serving")));
            recipe.setReadyInMinutes(Integer.parseInt(record.get("Ready In Minutes")));
            recipe.setServings(Integer.parseInt(record.get("Servings")));
            recipe.setSpoonacularScore(Double.parseDouble(record.get("Spoonacular Score")));
            recipe.setTitle(record.get("Title"));
            recipe.setVegan(Boolean.parseBoolean(record.get("Vegan")));
            recipe.setVegetarian(Boolean.parseBoolean(record.get("Vegetarian")));

            recipes.add(recipe);
        }

        return recipes;
    }
}
