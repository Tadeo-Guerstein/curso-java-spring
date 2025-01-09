package guru.springframework.springrecipeapp.repository;

import guru.springframework.springrecipeapp.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
