package br.com.alura.med.application.gateways;

import br.com.alura.med.domain.entities.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RepositorioPaciente {

    Paciente cadastrar(Paciente paciente);

    Page<Paciente> buscarTodos(Pageable pageable);

    Paciente atualizar(Long id, Paciente paciente);

    void excluir(Long id);

    Paciente buscar(Long id);
}
