package br.com.alura.med.naousar.domain.repository;

import br.com.alura.med.domain.entities.medico.Especialidade;
import br.com.alura.med.naousar.domain.medico.Medico;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long>, RevisionRepository<Medico,
        Long, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable pageable);

    @Query("""
             SELECT m FROM Medico m
             WHERE m.ativo = true AND m.especialidade = :especialidade
             AND m.id NOT IN (
                SELECT c.medico.id FROM Consulta c WHERE c.data = :data
             )
             ORDER BY RAND() limit 1
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade,
                                              LocalDateTime data);

    boolean existsByIdAndAtivo(Long idMedico, boolean isAtivo);

}
