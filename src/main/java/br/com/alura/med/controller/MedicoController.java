package br.com.alura.med.controller;

import br.com.alura.med.model.dto.MedicoDTO;
import br.com.alura.med.model.record.DadosAtualizacaoMedico;
import br.com.alura.med.model.record.DadosCadastroMedico;
import br.com.alura.med.model.record.DadosListagemMedico;
import br.com.alura.med.repository.MedicoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/medicos")
@AllArgsConstructor
public class MedicoController {

    private final MedicoRepository medicoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        log.info("{}::cadastrar - Dados recebidos: {}", getClass().getSimpleName(), dados);
        var medico = dados.converterParaMedico();
        log.info("{}::cadastrar - Dados salvos: {}", getClass().getSimpleName(), dados);
        medicoRepository.save(medico);
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 1, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return medicoRepository.findAllByAtivoTrue(pageable).map(DadosListagemMedico::new);
    }

    @Transactional
    @PutMapping
    public MedicoDTO atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico = medico.atualizarInformacoes(dados);
        return MedicoDTO.convertTo(medico);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void excluir(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.desativar();
    }

}
