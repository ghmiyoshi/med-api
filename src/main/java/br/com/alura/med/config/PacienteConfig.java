package br.com.alura.med.config;

import br.com.alura.med.infra.controllers.mappers.EnderecoMapper;
import br.com.alura.med.infra.controllers.mappers.PacienteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PacienteConfig {

    @Bean
    PacienteMapper pacienteMapper(EnderecoMapper enderecoMapper) {
        return new PacienteMapper(enderecoMapper);
    }
}
