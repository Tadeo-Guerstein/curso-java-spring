package guru.springframework.springrecipeapp.services;

import guru.springframework.springrecipeapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id);
}
