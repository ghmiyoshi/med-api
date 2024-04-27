package br.com.alura.med.application.gateways;

import br.com.alura.med.domain.entities.medico.Medico;
import java.util.List;

public interface RepositorioDeMedico {

    Medico cadastrarMedico(Medico medico);

    List<Medico> buscarTodos();

    Medico atualizarMedico(Medico medico);

    void excluirMedico(Long id);
}
