package br.com.alura.med.domain.repository;

import br.com.alura.med.domain.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    boolean existsByIdAndAtivo(Long idPaciente, boolean isAtivo);

    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

}
