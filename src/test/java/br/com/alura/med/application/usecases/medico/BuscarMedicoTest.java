package br.com.alura.med.application.usecases.medico;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.alura.med.BaseMockTest;
import br.com.alura.med.application.gateways.RepositorioMedico;
import br.com.alura.med.mock.MedicoMock;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@Execution(CONCURRENT)
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

    @DisplayName("Deve retornar erro quando nao encontrar um medico")
    @Test
    void shouldNotFindMedico() {
        // Arrange
        when(repositorio.buscar(anyLong())).thenThrow(new EntityNotFoundException("Medico não " +
                "encontrado"));

        // Act e Assert
        assertThatThrownBy(() -> buscarMedico.execute(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Medico não encontrado");
        verify(repositorio).buscar(anyLong());
    }
}