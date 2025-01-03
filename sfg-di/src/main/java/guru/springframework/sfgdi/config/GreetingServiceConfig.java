package guru.springframework.sfgdi.config;

import com.springframework.i18n.GreetingService;
import com.springframework.i18n.I18nEnglishService;
import com.springframework.i18n.I18nServiceFactory;
import com.springframework.i18n.I18nSpanishService;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepository;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepositoryImpl;
import guru.springframework.sfgdi.services.ConstructorGreetingService;
import guru.springframework.sfgdi.services.PrimaryGreetingService;
import guru.springframework.sfgdi.services.PropertyInjectedGreetingService;
import guru.springframework.sfgdi.services.SetterInjectedGreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class GreetingServiceConfig {
    @Bean
    I18nServiceFactory i18nServiceFactory(){
        return new I18nServiceFactory();
    }

    @Profile({"ES", "default"})
    @Bean("i18nService")
    GreetingService i18nSpanishService(I18nServiceFactory i18nServiceFactory){
        return i18nServiceFactory.getI18nService("ES");
    }

    @Bean
    EnglishGreetingRepository englishGreetingRepository(){
        return new EnglishGreetingRepositoryImpl();
    }

    @Profile("EN")
    @Bean
    GreetingService i18nService(I18nServiceFactory i18nServiceFactory){
        return i18nServiceFactory.getI18nService("EN");
    }

    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService(){
        return new PrimaryGreetingService();
    }

    @Bean
    ConstructorGreetingService constructorGreetingService() {
        return new ConstructorGreetingService();
    }

    @Bean
    PropertyInjectedGreetingService propertyInjectedGreetingService() {
        return new PropertyInjectedGreetingService();
    }

    @Bean
    SetterInjectedGreetingService setterInjectedGreetingService() {
        return new SetterInjectedGreetingService();
    }
}
