package br.com.alura.med.application.gateways;

import br.com.alura.med.domain.entities.medico.Especialidade;
import br.com.alura.med.domain.entities.medico.Medico;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RepositorioMedico {

    Medico cadastrar(Medico medico);

    Page<Medico> buscarTodos(Pageable pageable);

    Medico atualizar(Long id, Medico medico);

    void excluir(Long id);

    Medico buscar(Long id);

    Medico buscarMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);
}
