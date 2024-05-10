package br.com.alura.med.config;

import br.com.alura.med.infra.controllers.mappers.EnderecoMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnderecoConfig {

    @Bean
    public EnderecoMapper enderecoEntityMapper() {
        return new EnderecoMapper();
    }
}
