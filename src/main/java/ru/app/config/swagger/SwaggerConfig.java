package ru.app.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "REST calc",
                version = "v1.0",
                description = "RESTFUL API S_A Test "
        )
)
public class SwaggerConfig {
}