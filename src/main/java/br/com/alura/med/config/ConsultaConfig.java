package br.com.alura.med.config;

import br.com.alura.med.application.gateways.RepositorioConsulta;
import br.com.alura.med.application.usecases.consulta.CriarConsulta;
import br.com.alura.med.infra.controllers.mappers.ConsultaMapper;
import br.com.alura.med.infra.controllers.mappers.MedicoMapper;
import br.com.alura.med.infra.controllers.mappers.PacienteMapper;
import br.com.alura.med.infra.gateways.RepositorioConsultaJpa;
import br.com.alura.med.infra.persistence.consulta.ConsultaRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultaConfig {

    @Bean
    CriarConsulta criarConsulta(final RepositorioConsulta repositorioConsulta) {
        return new CriarConsulta(repositorioConsulta);
    }

    @Bean
    ConsultaMapper consultaMapper(final MedicoMapper medicoMapper,
                                  final PacienteMapper pacienteMapper) {
        return new ConsultaMapper(medicoMapper, pacienteMapper);
    }

    @Bean
    RepositorioConsulta repositorioConsulta(final ConsultaRepository consultaRepository,
                                            final ConsultaMapper consultaMapper,
                                            final ApplicationEventPublisher applicationEventPublisher) {
        return new RepositorioConsultaJpa(consultaRepository, consultaMapper,
                applicationEventPublisher);
    }
}
