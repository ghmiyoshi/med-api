package br.com.alura.med.config.doc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Med API")
                        .description("API Rest da aplicação med, contendo as funcionalidades"
                                + " de CRUD de médicos e de pacientes, além de agendamento e "
                                + "cancelamento de consultas")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Time Backend")
                                .email("backend@med"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://med/api/licenca")))
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                createSecurityScheme()));
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }
}
