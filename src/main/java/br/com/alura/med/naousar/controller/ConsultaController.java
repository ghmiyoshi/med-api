package br.com.alura.med.naousar.controller;

import br.com.alura.med.naousar.domain.consulta.DadosAgendamentoConsulta;
import br.com.alura.med.naousar.domain.consulta.DadosDetalhamentoConsulta;
import br.com.alura.med.naousar.service.consulta.ConsultaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    private ConsultaService consultaService;

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<DadosDetalhamentoConsulta> agendar(@RequestBody @Valid final DadosAgendamentoConsulta dados) {
        return ResponseEntity.ok(consultaService.agendar(dados));
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoConsulta> buscarAgendamento(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarAgendamento(id));
    }

}
