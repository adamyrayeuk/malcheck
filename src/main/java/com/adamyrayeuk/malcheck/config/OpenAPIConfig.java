package com.adamyrayeuk.malcheck.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {
    
    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact();
        contact.setEmail("adamy.rayeuk@gmail.com");
        contact.setName("Muhamad Adamy Rayeuk");
        contact.setUrl("https://github.com/adamyrayeuk");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
            .title("Malcheck")
            .version("1.0")
            .contact(contact)
            .description("Rest API to check if a file contains malware")
            .license(mitLicense);

        return new OpenAPI().info(info);
    }
}
