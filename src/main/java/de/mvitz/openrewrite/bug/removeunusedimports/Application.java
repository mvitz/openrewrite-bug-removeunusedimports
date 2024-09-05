package de.mvitz.openrewrite.bug.removeunusedimports;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(Application.TestProperties.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner printTestValue(TestProperties properties) {
        return args -> System.out.println("test.value=" + properties.value);
    }

    @ConfigurationProperties("test")
    public record TestProperties(
            @DefaultValue("foo") String value) {
    }
}
