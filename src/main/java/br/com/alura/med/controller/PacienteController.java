package br.com.alura.med.controller;

import br.com.alura.med.domain.paciente.*;
import br.com.alura.med.domain.repository.PacienteRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
public class PacienteController {

    private final PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> cadastrar(@RequestBody @Valid final DadosCadastroPaciente dados,
                                                               final UriComponentsBuilder uriBuilder) {
        log.info("{}::cadastrar - Dados recebidos: {}", getClass().getSimpleName(), dados);
        var paciente = new Paciente(dados);
        repository.save(paciente);

        log.info("{}::cadastrar - Dados salvos: {}", getClass().getSimpleName(), dados);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(sort = "nome") final Pageable paginacao) {
        log.info("{}::listar - Listando pacientes", getClass().getSimpleName());
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid final DadosAtualizacaoPaciente dados) {
        log.info("{}::atualizar - Dados recebidos: {}", getClass().getSimpleName(), dados);
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
        log.info("{}::atualizar - Dados atualizados: {}", getClass().getSimpleName(), dados);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable final Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
        log.info("{}::excluir - Médico excluído: {}", getClass().getSimpleName(), paciente);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable final Long id) {
        var paciente = repository.getReferenceById(id);
        log.info("{}::detalhar - Detalhes do médico: {}", getClass().getSimpleName(), paciente);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

}
