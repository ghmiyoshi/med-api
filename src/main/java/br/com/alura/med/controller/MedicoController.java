package br.com.alura.med.controller;

import br.com.alura.med.domain.record.DadosAtualizacaoMedico;
import br.com.alura.med.domain.record.DadosCadastroMedico;
import br.com.alura.med.domain.record.DadosDetalhamentoMedico;
import br.com.alura.med.domain.record.DadosListagemMedico;
import br.com.alura.med.domain.repository.MedicoRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/medicos")
@AllArgsConstructor
public class MedicoController {

    private final MedicoRepository medicoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DadosDetalhamentoMedico cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        log.info("{}::cadastrar - Dados recebidos: {}", getClass().getSimpleName(), dados);
        var medico = dados.converterParaMedico();
        log.info("{}::cadastrar - Dados salvos: {}", getClass().getSimpleName(), dados);
        medico = medicoRepository.save(medico);
        return new DadosDetalhamentoMedico(medico);
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 1, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return medicoRepository.findAllByAtivoTrue(pageable).map(DadosListagemMedico::new);
    }

    @Transactional
    @PutMapping
    public DadosDetalhamentoMedico atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        log.info("{}::atualizar - Dados recebidos: {}", getClass().getSimpleName(), dados);
        var medico = medicoRepository.getReferenceById(dados.id());
        medico = medico.atualizarInformacoes(dados);
        log.info("{}::atualizar - Dados atualizados: {}", getClass().getSimpleName(), dados);
        return new DadosDetalhamentoMedico(medico);
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        log.info("{}::excluir - Médico excluído: {}", getClass().getSimpleName(), medico);
        medico.desativar();
    }

    @GetMapping("/{id}")
    public DadosDetalhamentoMedico detalhar(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        log.info("{}::detalhar - Detalhes do médico: {}", getClass().getSimpleName(), medico);
        return new DadosDetalhamentoMedico(medico);
    }

}
