package br.com.alura.med.application.usecases.medico;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import br.com.alura.med.domain.entities.medico.Medico;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class BuscarMedicoIT {

    @Autowired
    private BuscarMedico buscarMedico;

    @DisplayName("Deve buscar um medico")
    @Test
    void shouldFindMedico() {
        final var medicoFound = buscarMedico.execute(1L);

        assertThat(medicoFound).isNotNull()
                .isInstanceOf(Medico.class);
        assertThat(medicoFound.getNome()).isNotNull()
                .isEqualTo("Gabriel");
    }

    @DisplayName("Deve retornar erro quando nao encontrar um medico")
    @Test
    void shouldNotFindMedico() {
        assertThatThrownBy(() -> buscarMedico.execute(100L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Médico não encontrado");
    }
}
