package com.api.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    private final String serverUrl;

    public SwaggerConfig(@Value("${url.api}") String serverUrl) { this.serverUrl = serverUrl; }

    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        SecurityScheme securityScheme = new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER);
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("JWT");
        Contact contact = new Contact();
        contact.setEmail("matias6selvaggio@gmail.com");
        contact.setName("Matias Selvaggio");
        contact.setUrl(null);
        License license = null;
        Info info = new Info()
                .title("El Legado De Dionisio API")
                .contact(contact)
                .description("Swagger El Legado De Dionisio by Matias Selvaggio")
                .version("0.1");
        List<Server> servers = new ArrayList<>();
        servers.add(new Server().url(serverUrl));
        return new OpenAPI().info(info).components(new Components().addSecuritySchemes("JWT", securityScheme))
                .addSecurityItem(securityRequirement).servers(servers);
    }
}
