package guru.springfamework.api.v1.model;

import lombok.Data;

@Data
public class UserDTO {
    private Long Id;
    private String name;
    private String email;
}
