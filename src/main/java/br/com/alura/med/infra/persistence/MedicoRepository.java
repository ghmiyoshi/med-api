package br.com.alura.med.infra.persistence;

import br.com.alura.med.naousar.domain.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
