package de.networkchallenge.e2c.lambda.backend.spring;

import de.networkchallenge.e2c.lambda.backend.handler.UrlController;
import de.networkchallenge.e2c.lambda.backend.parser.impl.ParserRegistry;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {ApplicationConfiguration.class, UrlController.class, ParserRegistry.class})
public class ApplicationConfiguration {

//    @Bean
//    public ExampleServiceA exampleServiceA() {
//        return new ExampleServiceA();
//    }
}