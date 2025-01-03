package com.springframework.i18n;

import guru.springframework.sfgdi.repositories.EnglishGreetingRepository;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepositoryImpl;
import org.springframework.stereotype.Service;

public class I18nServiceFactory {
    public GreetingService getI18nService(String i18nType){
        switch (i18nType) {
            case "EN": {
                EnglishGreetingRepository englishGreetingRepository = new EnglishGreetingRepositoryImpl();
                return new I18nEnglishService(englishGreetingRepository);
            }
            case "ES":
                return new I18nSpanishService();
            default:
                return new I18nSpanishService();
        }
    }
}
