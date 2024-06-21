package br.com.alura.med.config;

import br.com.alura.med.application.gateways.RepositorioPaciente;
import br.com.alura.med.application.usecases.paciente.BuscarPaciente;
import br.com.alura.med.infra.controllers.mappers.EnderecoMapper;
import br.com.alura.med.infra.controllers.mappers.PacienteMapper;
import br.com.alura.med.infra.gateways.RepositorioPacienteJpa;
import br.com.alura.med.infra.persistence.paciente.PacienteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PacienteConfig {

    @Bean
    PacienteMapper pacienteMapper(final EnderecoMapper enderecoMapper) {
        return new PacienteMapper(enderecoMapper);
    }

    @Bean
    RepositorioPaciente repositorioPaciente(final PacienteRepository pacienteRepository,
                                            final PacienteMapper pacienteMapper,
                                            final EnderecoMapper enderecoMapper) {
        return new RepositorioPacienteJpa(pacienteRepository, pacienteMapper, enderecoMapper);
    }

    @Bean
    BuscarPaciente buscarPaciente(final RepositorioPaciente repositorioPaciente) {
        return new BuscarPaciente(repositorioPaciente);
    }
}
