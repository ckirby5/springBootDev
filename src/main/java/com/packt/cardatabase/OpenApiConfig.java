package com.packt.cardatabase;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${api.version}")
    private String version;

    @Bean
    public OpenAPI carDatabaseOpenAPI() {
        return new OpenAPI().info(new Info().title("Car REST API").description("My car stock").version(version));
    }
}
