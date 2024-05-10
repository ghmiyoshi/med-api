package br.com.alura.med.naousar.controller;

import br.com.alura.med.infra.persistence.paciente.PacienteRepository;
import br.com.alura.med.naousar.domain.paciente.DadosAtualizacaoPaciente;
import br.com.alura.med.naousar.domain.paciente.DadosCadastroPaciente;
import br.com.alura.med.naousar.domain.paciente.DadosDetalhamentoPaciente;
import br.com.alura.med.naousar.domain.paciente.DadosListagemPaciente;
import br.com.alura.med.naousar.domain.paciente.Paciente;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping("/pacientes")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
public class PacienteController {

    private PacienteRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
    public Page<DadosListagemPaciente> listar(@PageableDefault(sort = {"nome"}) final Pageable paginacao) {
        log.info("{}::listar - Listando pacientes", getClass().getSimpleName());
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
    }

    @PutMapping
    @Transactional
    public DadosDetalhamentoPaciente atualizar(@RequestBody @Valid final DadosAtualizacaoPaciente dados) {
        log.info("{}::atualizar - Dados recebidos: {}", getClass().getSimpleName(), dados);
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
        log.info("{}::atualizar - Dados atualizados: {}", getClass().getSimpleName(), dados);
        return new DadosDetalhamentoPaciente(paciente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable final Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
        log.info("{}::excluir - Paciente excluído: {}", getClass().getSimpleName(), paciente);
    }

    @GetMapping("/{id}")
    public DadosDetalhamentoPaciente detalhar(@PathVariable final Long id) {
        var paciente = repository.getReferenceById(id);
        log.info("{}::detalhar - Detalhes do médico: {}", getClass().getSimpleName(), paciente);
        return new DadosDetalhamentoPaciente(paciente);
    }

}
