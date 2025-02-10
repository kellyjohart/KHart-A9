# Recipe Finder API 🍽️

A Spring Boot-based REST API for searching and filtering recipes by dietary preferences (e.g., vegan, gluten-free). The project demonstrates Java, Spring Boot, and CSV data processing using Apache Commons CSV.

## Features ✨
- **Recipe Filtering**:
  - `/gluten-free`: Gluten-free recipes.
  - `/vegan`: Vegan recipes.
  - `/vegan-and-gluten-free`: Vegan & gluten-free recipes.
  - `/vegetarian`: Vegetarian recipes.
  - `/all-recipes`: All recipes.

## Technologies 🛠️
- Java
- Spring Boot
- Apache Commons CSV
- Maven
- JUnit 5

## Endpoints 🚀
- `GET /all-recipes`
- `GET /gluten-free`
- `GET /vegan`
- `GET /vegan-and-gluten-free`
- `GET /vegetarian`

## Example Response for `/all-recipes` 🍝

```json
[
  {
    "title": "Pasta Primavera",
    "cookingMinutes": 30,
    "preparationMinutes": 15,
    "pricePerServing": 3.5,
    "readyInMinutes": 45,
    "servings": 4,
    "spoonacularScore": 95,
    "instructions": "Step 1: Prepare ingredients. Step 2: Cook for 30 minutes.",
    "dairyFree": true,
    "glutenFree": false,
    "vegan": true,
    "vegetarian": true
  },
  {
    "title": "Grilled Chicken Salad",
    "cookingMinutes": 20,
    "preparationMinutes": 10,
    "pricePerServing": 2.5,
    "readyInMinutes": 30,
    "servings": 2,
    "spoonacularScore": 89,
    "instructions": "Step 1: Prepare vegetables. Step 2: Cook for 20 minutes.",
    "dairyFree": false,
    "glutenFree": true,
    "vegan": false,
    "vegetarian": false
  }
]
