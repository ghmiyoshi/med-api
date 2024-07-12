package br.com.alura.med.application.usecases.medico;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.alura.med.BaseMockTest;
import br.com.alura.med.application.gateways.RepositorioMedico;
import br.com.alura.med.mock.MedicoMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class BuscarMedicoTest extends BaseMockTest {

    @InjectMocks
    private BuscarMedico buscarMedico;

    @Mock
    private RepositorioMedico repositorio;

    @DisplayName("Deve buscar um medico")
    @Test
    void shouldFindMedico() {
        // Arrange
        final var medico = MedicoMock.newMedico();
        when(repositorio.buscar(anyLong())).thenReturn(medico);

        // Act
        final var medicoFound = buscarMedico.execute(1L);

        // Assert
        verify(repositorio).buscar(anyLong());
        assertThat(medicoFound).isNotNull();
        assertThat(medicoFound.getNome()).isNotNull().isEqualTo(medico.getNome());
        assertThat(medicoFound.getEmail()).isNotNull().isEqualTo(medico.getEmail());
        assertThat(medicoFound.getCrm()).isNotNull().isEqualTo(medico.getCrm());
        assertThat(medicoFound.getEspecialidade()).isNotNull().isEqualTo(medico.getEspecialidade());
    }
}