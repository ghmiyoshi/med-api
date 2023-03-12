package br.com.alura.med.domain.repository;

import br.com.alura.med.domain.medico.Especialidade;
import br.com.alura.med.domain.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

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
