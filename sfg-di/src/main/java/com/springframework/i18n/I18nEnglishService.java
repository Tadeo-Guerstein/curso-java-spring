package com.springframework.i18n;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

public class I18nEnglishService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello world - EN";
    }
}
