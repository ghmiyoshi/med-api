package br.com.alura.med.infra.persistence.consulta;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

    boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);

    boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario,
                                             LocalDateTime ultimoHorario);

}
