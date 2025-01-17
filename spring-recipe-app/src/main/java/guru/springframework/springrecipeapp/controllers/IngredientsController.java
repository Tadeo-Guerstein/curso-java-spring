package guru.springframework.springrecipeapp.controllers;

import guru.springframework.springrecipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientsController {
    private final RecipeService recipeService;

    public IngredientsController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "/recipe/ingredients/list";
    }
}
