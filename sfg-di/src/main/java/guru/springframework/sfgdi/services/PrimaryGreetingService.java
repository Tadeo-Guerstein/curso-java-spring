package guru.springframework.sfgdi.services;

import com.springframework.i18n.GreetingService;

public class PrimaryGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello World - From the PRIMERY Bean";
    }
}
