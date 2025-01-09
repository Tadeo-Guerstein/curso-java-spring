package guru.springframework.springrecipeapp.services;

import guru.springframework.springrecipeapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RecipeService {
    Set<Recipe> getRecipes();
}
