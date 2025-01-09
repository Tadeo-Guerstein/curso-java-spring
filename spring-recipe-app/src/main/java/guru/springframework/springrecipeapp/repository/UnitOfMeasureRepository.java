package guru.springframework.springrecipeapp.repository;

import guru.springframework.springrecipeapp.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
