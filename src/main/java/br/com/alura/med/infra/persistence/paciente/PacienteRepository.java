package br.com.alura.med.infra.persistence.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    boolean existsByIdAndAtivo(Long idPaciente, boolean isAtivo);

    Page<PacienteEntity> findAllByAtivoTrue(Pageable paginacao);

}
