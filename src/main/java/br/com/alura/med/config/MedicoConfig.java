package br.com.alura.med.config;

import br.com.alura.med.application.gateways.RepositorioDeMedico;
import br.com.alura.med.application.usecases.AtualizarMedico;
import br.com.alura.med.application.usecases.BuscarMedicos;
import br.com.alura.med.application.usecases.CriarMedico;
import br.com.alura.med.application.usecases.ExcluirMedico;
import br.com.alura.med.application.usecases.ListarMedicos;
import br.com.alura.med.infra.controllers.mappers.EnderecoMapper;
import br.com.alura.med.infra.controllers.mappers.MedicoMapper;
import br.com.alura.med.infra.gateways.RepositorioDeMedicoJpa;
import br.com.alura.med.infra.persistence.medico.MedicoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicoConfig {

    @Bean
    CriarMedico criarMedico(RepositorioDeMedico repositorioDeMedico) {
        return new CriarMedico(repositorioDeMedico);
    }

    @Bean
    ListarMedicos listarMedico(RepositorioDeMedico repositorioDeMedico) {
        return new ListarMedicos(repositorioDeMedico);
    }

    @Bean
    RepositorioDeMedico repositorioDeMedico(MedicoRepository medicoRepository,
                                            MedicoMapper medicoMapper,
                                            EnderecoMapper enderecoMapper) {
        return new RepositorioDeMedicoJpa(medicoRepository, medicoMapper, enderecoMapper);
    }

    @Bean
    MedicoMapper medicoEntityMapper(EnderecoMapper enderecoMapper) {
        return new MedicoMapper(enderecoMapper);
    }

    @Bean
    BuscarMedicos buscarMedicos(RepositorioDeMedico repositorioDeMedico) {
        return new BuscarMedicos(repositorioDeMedico);
    }

    @Bean
    AtualizarMedico atualizarMedico(RepositorioDeMedico repositorioDeMedico) {
        return new AtualizarMedico(repositorioDeMedico);
    }

    @Bean
    ExcluirMedico excluirMedico(RepositorioDeMedico repositorioDeMedico) {
        return new ExcluirMedico(repositorioDeMedico);
    }
}
