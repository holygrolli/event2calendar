package de.networkchallenge.e2c.lambda.backend.boot;

import de.networkchallenge.e2c.lambda.backend.handler.UrlController;
import de.networkchallenge.e2c.lambda.backend.parser.impl.ParserRegistry;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {UrlController.class, ParserRegistry.class})
public class SpringAppConfig {
}
