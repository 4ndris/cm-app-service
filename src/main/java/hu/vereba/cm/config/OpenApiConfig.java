package hu.vereba.cm.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition( 
    servers = {
       @Server(url = "http://localhost:8080", description = "Local Server URL")
    }
) 
@Configuration
public class OpenApiConfig {
    
}
