package com.swordssorcery.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableWebMvc
@ComponentScan
public class SwordsorceryServerApplication extends WebMvcAutoConfigurationAdapter {

    public static void main(String[] args) {
        SpringApplication.run(SwordsorceryServerApplication.class, args);
    }
}