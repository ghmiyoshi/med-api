package br.com.alura.med.infra.controllers;

import br.com.alura.med.application.usecases.medico.AtualizarMedico;
import br.com.alura.med.application.usecases.medico.BuscarMedico;
import br.com.alura.med.application.usecases.medico.CriarMedico;
import br.com.alura.med.application.usecases.medico.ExcluirMedico;
import br.com.alura.med.application.usecases.medico.ListarMedicos;
import br.com.alura.med.config.CachingConfig;
import br.com.alura.med.infra.controllers.mappers.MedicoMapper;
import br.com.alura.med.infra.controllers.requests.DadosAtualizacaoMedico;
import br.com.alura.med.infra.controllers.requests.DadosCadastroMedico;
import br.com.alura.med.infra.controllers.responses.DadosDetalhamentoMedico;
import br.com.alura.med.infra.controllers.responses.DadosListagemMedico;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/medicos")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    private CriarMedico criarMedico;
    private ListarMedicos listarMedicos;
    private BuscarMedico buscarMedico;
    private AtualizarMedico atualizarMedico;
    private ExcluirMedico excluirMedico;
    private MedicoMapper medicoMapper;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @CacheEvict(value = CachingConfig.MEDICOS, allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DadosDetalhamentoMedico cadastrar(@RequestBody @Valid final DadosCadastroMedico dados) {
        log.info("{}::cadastrar - Dados recebidos: {}", getClass().getSimpleName(), dados);
        var medico = medicoMapper.toDomain(dados);
        medico = criarMedico.execute(medico);
        log.info("{}::cadastrar - Dados salvos: {}", getClass().getSimpleName(), dados);
        return medicoMapper.toResponseDetalhamento(medico);
    }

    @Cacheable(CachingConfig.MEDICOS)
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 1, sort = "nome", direction =
            Sort.Direction.ASC) final Pageable pageable) {
        log.info("{}::listar - Listando médicos", getClass().getSimpleName());
        return listarMedicos.execute(pageable).map(medicoMapper::toResponseListagem);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @CacheEvict(value = CachingConfig.MEDICOS, allEntries = true)
    @PutMapping
    public DadosDetalhamentoMedico atualizar(@RequestBody @Valid final DadosAtualizacaoMedico
                                                     dados) {
        log.info("{}::atualizar - Dados recebidos: {}", getClass().getSimpleName(), dados);
        var medico = buscarMedico.execute(dados.id());
        medico = atualizarMedico.execute(dados.id(), medico);
        log.info("{}::atualizar - Dados atualizados: {}", getClass().getSimpleName(), dados);
        return medicoMapper.toResponseDetalhamento(medico);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @CacheEvict(value = CachingConfig.MEDICOS, allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable final Long id) {
        excluirMedico.execute(id);
        log.info("{}::excluir - Médico com id {} excluído", getClass().getSimpleName(), id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public DadosDetalhamentoMedico detalhar(@PathVariable final Long id) {
        var medico = buscarMedico.execute(id);
        log.info("{}::detalhar - Detalhes do médico: {}", getClass().getSimpleName(),
                medico);
        return medicoMapper.toResponseDetalhamento(medico);
    }
}
