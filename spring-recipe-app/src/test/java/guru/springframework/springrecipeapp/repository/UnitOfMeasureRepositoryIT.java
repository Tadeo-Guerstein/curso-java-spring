package guru.springframework.springrecipeapp.repository;

import guru.springframework.springrecipeapp.model.UnitOfMeasure;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {
    @Autowired
    UnitOfMeasureRepository uomRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByDescription() {
        Optional<UnitOfMeasure> uomOptional = uomRepository.findByDescription("Teaspoon");

        Assert.assertEquals("Teaspoon", uomOptional.get().getDescription());
    }

    @Test
    public void findByDescriptionCup() {
        Optional<UnitOfMeasure> uomOptional = uomRepository.findByDescription("Cup");

        Assert.assertEquals("Cup", uomOptional.get().getDescription());
    }
}