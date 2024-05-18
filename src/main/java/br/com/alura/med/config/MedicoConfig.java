package br.com.alura.med.config;

import br.com.alura.med.application.gateways.RepositorioMedico;
import br.com.alura.med.application.usecases.medico.AtualizarMedico;
import br.com.alura.med.application.usecases.medico.BuscarMedico;
import br.com.alura.med.application.usecases.medico.CriarMedico;
import br.com.alura.med.application.usecases.medico.ExcluirMedico;
import br.com.alura.med.application.usecases.medico.ListarMedicos;
import br.com.alura.med.infra.controllers.mappers.EnderecoMapper;
import br.com.alura.med.infra.controllers.mappers.MedicoMapper;
import br.com.alura.med.infra.gateways.RepositorioMedicoJpa;
import br.com.alura.med.infra.persistence.medico.MedicoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicoConfig {

    @Bean
    CriarMedico criarMedico(RepositorioMedico repositorioMedico) {
        return new CriarMedico(repositorioMedico);
    }

    @Bean
    ListarMedicos listarMedico(RepositorioMedico repositorioMedico) {
        return new ListarMedicos(repositorioMedico);
    }

    @Bean
    RepositorioMedico repositorioDeMedico(MedicoRepository medicoRepository,
                                          MedicoMapper medicoMapper,
                                          EnderecoMapper enderecoMapper) {
        return new RepositorioMedicoJpa(medicoRepository, medicoMapper, enderecoMapper);
    }

    @Bean
    MedicoMapper medicoEntityMapper(EnderecoMapper enderecoMapper) {
        return new MedicoMapper(enderecoMapper);
    }

    @Bean
    BuscarMedico buscarMedico(RepositorioMedico repositorioMedico) {
        return new BuscarMedico(repositorioMedico);
    }

    @Bean
    AtualizarMedico atualizarMedico(RepositorioMedico repositorioMedico) {
        return new AtualizarMedico(repositorioMedico);
    }

    @Bean
    ExcluirMedico excluirMedico(RepositorioMedico repositorioMedico) {
        return new ExcluirMedico(repositorioMedico);
    }
}
