package br.com.alura.med.application.usecases.medico;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.alura.med.BaseMockTest;
import br.com.alura.med.application.gateways.RepositorioMedico;
import br.com.alura.med.domain.entities.medico.Medico;
import br.com.alura.med.mock.MedicoMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class CriarMedicoTest extends BaseMockTest {

    @InjectMocks
    private CriarMedico criarMedico;

    @Mock
    private RepositorioMedico repositorio;

    @DisplayName("Deveria criar um medico")
    @Test
    void shouldCreateMedico() {
        // Arrange
        final var medico = MedicoMock.newMedico();
        when(repositorio.cadastrar(any(Medico.class))).thenReturn(medico);

        // Act
        final var medicoCreated = criarMedico.execute(medico);

        // Assert
        verify(repositorio).cadastrar(any(Medico.class));
        assertThat(medicoCreated).isInstanceOf(Medico.class).isNotNull();
        assertThat(medicoCreated.getNome()).isNotNull().isEqualTo(medico.getNome());
        assertThat(medicoCreated.getEmail()).isNotNull().isEqualTo(medico.getEmail());
        assertThat(medicoCreated.getCrm()).isNotNull().isEqualTo(medico.getCrm());
        assertThat(medicoCreated.getEspecialidade()).isNotNull().isEqualTo(medico.getEspecialidade());
    }
}