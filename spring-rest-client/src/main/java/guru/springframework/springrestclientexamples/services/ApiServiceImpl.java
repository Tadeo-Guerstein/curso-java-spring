package guru.springframework.springrestclientexamples.services;

import guru.springframework.api.domain.User;
import guru.springframework.api.domain.UserData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
    private RestTemplate restTemplate;
    private final String api_url;

    public ApiServiceImpl(RestTemplate restTemplate, @Value("${api.url}") String api_url) {
        this.restTemplate = restTemplate;
        this.api_url = api_url;
    }

    @Override
    public List<User> getUsers() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(api_url);
                                                // .queryParams("limit", 3)
        List<User> userData = restTemplate.getForObject(uriBuilder.toUriString(), List.class);
        return userData;
    }

//    @Override
//    public Flux<User> getUsersFlux() {
//        return WebClient
//        .create(api_url)
//        .get()
//        .accept(MediaType.APPLICATION_JSON)
//        .exchange()
//        .flatMap(resp -> resp.bodyToMono(List.class))
//        .flatMapIterable(UserData::getData);
//    }
}
