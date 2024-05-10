package br.com.alura.med.application.gateways;

import br.com.alura.med.domain.entities.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RepositorioDeMedico {

    Medico cadastrarMedico(Medico medico);

    Page<Medico> buscarTodos(Pageable pageable);

    Medico atualizarMedico(Long id, Medico medico);

    void excluirMedico(Long id);

    Medico buscarMedico(Long id);
}
