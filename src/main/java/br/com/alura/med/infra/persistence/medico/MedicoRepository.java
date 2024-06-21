package br.com.alura.med.infra.persistence.medico;

import br.com.alura.med.domain.entities.medico.Especialidade;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {

    Page<MedicoEntity> findAllByAtivoTrue(Pageable pageable);

    @Query("""
             SELECT m FROM MedicoEntity m
             WHERE m.ativo = true AND m.especialidade = :especialidade
             AND m.id NOT IN (
                SELECT c.medico.id FROM ConsultaEntity c WHERE c.data = :data
             )
             ORDER BY RAND() limit 1
            """)
    Optional<MedicoEntity> escolherMedicoAleatorioLivreNaData(Especialidade especialidade,
                                                              LocalDateTime data);

    boolean existsByIdAndAtivo(Long idMedico, boolean isAtivo);

    boolean existsByCrm(String crm);
}
