package guru.springframework.springrecipeapp.controllers;

import guru.springframework.springrecipeapp.model.Recipe;
import guru.springframework.springrecipeapp.services.RecipeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class IndexControllerTest {
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    public void getIndexPage() {
        //given
        Set<Recipe> recipesSet = new HashSet<>();
        recipesSet.add(new Recipe());

        Recipe recipe = new Recipe();
        recipe.setId(1l);

        recipesSet.add(recipe);

        Mockito.when(recipeService.getRecipes()).thenReturn(recipesSet);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = indexController.getIndexPage(model);

        //then
        Assert.assertEquals(viewName, "index");
        Mockito.verify(recipeService, Mockito.times(1)).getRecipes();
        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("recipes"),
                argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        Assert.assertEquals(2, setInController.size());
    }
}