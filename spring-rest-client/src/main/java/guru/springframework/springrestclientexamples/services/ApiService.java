package guru.springframework.springrestclientexamples.services;

import guru.springframework.api.domain.User;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ApiService {
    List<User> getUsers();

//    Flux<User> getUsersFlux();
}
