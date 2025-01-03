package guru.springframework.sfgdi.services;

import com.springframework.i18n.GreetingService;
import org.springframework.stereotype.Service;

public class ConstructorGreetingService implements GreetingService {
    @Override
    public String sayGreeting(){
        return "Hello World - Constructor";
    }
}
