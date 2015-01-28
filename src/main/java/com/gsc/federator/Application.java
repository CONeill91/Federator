package com.gsc.federator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(final String[] args) {
        final ApplicationContext ctx = SpringApplication.run(Application.class, args);

        logger.info("beans provided by Spring Boot");

        final String[] beanNames = ctx.getBeanDefinitionNames();

        for (final String beanName : beanNames) {
            logger.info("bean [{}]", beanName);
        }
    }

}
