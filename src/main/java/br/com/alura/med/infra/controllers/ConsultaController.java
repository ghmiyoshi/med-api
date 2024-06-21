package br.com.alura.med.infra.controllers;

import br.com.alura.med.application.usecases.consulta.AgendarConsulta;
import br.com.alura.med.application.usecases.consulta.BuscarConsulta;
import br.com.alura.med.infra.controllers.mappers.ConsultaMapper;
import br.com.alura.med.infra.controllers.requests.DadosAgendamentoConsulta;
import br.com.alura.med.infra.controllers.responses.DadosDetalhamentoConsulta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    private AgendarConsulta agendarConsulta;
    private BuscarConsulta buscarConsulta;
    private ConsultaMapper consultaMapper;

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @PostMapping
    public DadosDetalhamentoConsulta agendar(@RequestBody @Valid final DadosAgendamentoConsulta dados) {
        var consulta = agendarConsulta.execute(dados);
        log.info("{}::agendar - Dados salvos: {}", getClass().getSimpleName(),
                dados);
        return consultaMapper.toResponse(consulta);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/{id}")
    public DadosDetalhamentoConsulta buscarConsulta(@PathVariable Long id) {
        var consulta = buscarConsulta.execute(id);
        return consultaMapper.toResponse(consulta);
    }
}
