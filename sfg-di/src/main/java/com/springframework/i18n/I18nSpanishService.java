package com.springframework.i18n;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"ES", "default"})
@Service("i18nService")
public class I18nSpanishService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hola mundo - ES";
    }
}
