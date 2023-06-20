package br.com.alura.med.controller;

import br.com.alura.med.domain.medico.DadosAtualizacaoMedico;
import br.com.alura.med.domain.medico.DadosCadastroMedico;
import br.com.alura.med.domain.medico.DadosDetalhamentoMedico;
import br.com.alura.med.domain.medico.DadosListagemMedico;
import br.com.alura.med.domain.repository.MedicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/medicos")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DadosDetalhamentoMedico cadastrar(@RequestBody @Valid final DadosCadastroMedico dados) {
        log.info("{}::cadastrar - Dados recebidos: {}", getClass().getSimpleName(), dados);
        var medico = dados.converterParaMedico();

        medico = medicoRepository.save(medico);
        log.info("{}::cadastrar - Dados salvos: {}", getClass().getSimpleName(), dados);
        return new DadosDetalhamentoMedico(medico);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 1, sort = "nome", direction = Sort.Direction.ASC) final Pageable pageable) {
        log.info("{}::listar - Listando médicos", getClass().getSimpleName());
        return medicoRepository.findAllByAtivoTrue(pageable).map(DadosListagemMedico::new);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Transactional
    @PutMapping
    public DadosDetalhamentoMedico atualizar(@RequestBody @Valid final DadosAtualizacaoMedico dados) {
        log.info("{}::atualizar - Dados recebidos: {}", getClass().getSimpleName(), dados);
        var medico = medicoRepository.getReferenceById(dados.id());

        medico = medico.atualizarInformacoes(dados);
        log.info("{}::atualizar - Dados atualizados: {}", getClass().getSimpleName(), dados);
        return new DadosDetalhamentoMedico(medico);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable final Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.desativar();
        log.info("{}::excluir - Médico excluído: {}", getClass().getSimpleName(), medico);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public DadosDetalhamentoMedico detalhar(@PathVariable final Long id) {
        var medico = medicoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                  String.format("Unable to find medico with id %d", id)));
        log.info("{}::detalhar - Detalhes do médico: {}", getClass().getSimpleName(), medico);
        return new DadosDetalhamentoMedico(medico);
    }

}
