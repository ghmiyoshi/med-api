package br.com.alura.med.controller;

import br.com.alura.med.domain.consulta.DadosAgendamentoConsulta;
import br.com.alura.med.domain.consulta.DadosDetalhamentoConsulta;
import br.com.alura.med.service.consulta.ConsultaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    private final ConsultaService consultaService;

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> agendar(@RequestBody @Valid final DadosAgendamentoConsulta dados) {
        return ResponseEntity.ok(consultaService.agendar(dados));
    }

}
