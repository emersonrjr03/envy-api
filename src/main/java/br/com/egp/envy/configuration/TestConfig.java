package br.com.egp.envy.configuration;

import br.com.egp.envy.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {
    @Autowired
    private DBService dbService;

    @Bean boolean instantiateDatabase() {
        dbService.instantiateTestDatabase();
        return true;
    }
}
