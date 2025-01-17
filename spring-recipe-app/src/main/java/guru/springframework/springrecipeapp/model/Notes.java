package guru.springframework.springrecipeapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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
