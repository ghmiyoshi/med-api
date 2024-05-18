package br.com.alura.med.application.gateways;

import br.com.alura.med.domain.entities.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RepositorioPaciente {

    Paciente cadastrarPaciente(Paciente paciente);

    Page<Paciente> buscarTodos(Pageable pageable);

    Paciente atualizarPaciente(Long id, Paciente paciente);

    void excluirPaciente(Long id);

    Paciente buscarPaciente(Long id);
}
