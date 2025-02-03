package guru.springfamework.repositories;

import guru.springfamework.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsUserByEmail(String email);
}
