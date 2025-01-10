package guru.springframework.springrecipeapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // no hay cascade por si se elimina la nota no se elimina la receta
    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

}
