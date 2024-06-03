package com.openclassrooms.chatop.configuration;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Chatop API documentation",
                description = "Chatop API documentation",
                version = "v1.0"
        ),
        security = {
        		@SecurityRequirement(
        				name = "BearerAuth"
        				)
        }
)
@SecurityScheme(
        name = "BearerAuth",
        description = "JWT auth description (Connect to get JWT token)",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
        
)
public class OpenApiConfig {

}
