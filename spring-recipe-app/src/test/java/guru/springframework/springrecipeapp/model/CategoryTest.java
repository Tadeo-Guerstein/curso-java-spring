package guru.springframework.springrecipeapp.model;


import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class CategoryTest {
    Category category;

    @Before
    public void setUp(){
        category = new Category();
    }

    @Test
    public void getId() {
        Long idValue = 4l;

        category.setId(idValue);

        Assert.assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipe() {
    }
}