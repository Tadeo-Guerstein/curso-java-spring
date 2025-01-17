package guru.springframework.springrecipeapp.controllers;

import guru.springframework.springrecipeapp.commands.IngredientCommand;
import guru.springframework.springrecipeapp.commands.RecipeCommand;
import guru.springframework.springrecipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ExtendWith(MockitoExtension.class)
public class IngredientControllerTest {
    @Mock
    RecipeService recipeService;

    IngredientsController controller;
    MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        controller = new IngredientsController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testListIngredients() throws Exception {
        // given
        RecipeCommand command = new RecipeCommand();
        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        // when
        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/ingredients/list"))
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService, times(1)).findCommandById(anyLong());
    }
}
