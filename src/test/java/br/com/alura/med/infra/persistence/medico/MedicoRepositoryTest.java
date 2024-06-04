package br.com.alura.med.infra.persistence.medico;

import static br.com.alura.med.domain.entities.medico.Especialidade.CARDIOLOGIA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.alura.med.BaseMockTest;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class MedicoRepositoryTest extends BaseMockTest {

    @Mock
    private MedicoRepository medicoRepository;

    @DisplayName("Deveria salvar medico")
    @Test
    void shouldSaveMedico_whenCallSave() {
        // Arrange
        var medico = gerarMedico();
        when(medicoRepository.save(any(MedicoEntity.class))).thenReturn(medico);

        // Act
        var medicoSaved = medicoRepository.save(medico);

        // Assert
        verify(medicoRepository).save(medico);
        assertThat(medicoSaved).isInstanceOf(MedicoEntity.class).isNotNull().isEqualTo(medico);
        assertThat(medicoSaved).extracting(MedicoEntity::getId).isEqualTo(medico.getId());
        assertThat(medicoSaved).extracting(MedicoEntity::getCrm).isEqualTo(medico.getCrm());
        assertThat(medicoSaved).extracting(MedicoEntity::getNome).isEqualTo(medico.getNome());
        assertThat(medicoSaved).extracting(MedicoEntity::getTelefone).isEqualTo(medico.getTelefone());
    }

    @DisplayName("Deveria buscar medico pelo id")
    @Test
    void shouldFindMedico_whenCallFindById() {
        // Arrange
        var id = 1L;
        var medico = gerarMedico();

        when(medicoRepository.findById(anyLong())).thenReturn(Optional.of(medico));

        // Act
        var medicoOptional = medicoRepository.findById(id);

        // Assert
        verify(medicoRepository).findById(id);
        assertThat(medicoOptional).isPresent().containsSame(medico);
        medicoOptional.ifPresent(medicoSaved -> {
            assertThat(medicoSaved.getId()).isEqualTo(medico.getId());
            assertThat(medicoSaved.getNome()).isEqualTo(medico.getNome());
            assertThat(medicoSaved.getCrm()).isEqualTo(medico.getCrm());
            assertThat(medicoSaved.getEspecialidade()).isEqualTo(medico.getEspecialidade());
        });
    }

    @DisplayName("Deveria deletar medico pelo id")
    @Test
    void shouldDeleteMedico_whenCallDeleteById() {
        // Arrange
        var id = 1L;
        doNothing().when(medicoRepository).deleteById(id);

        // Act
        medicoRepository.deleteById(id);

        // Assert
        verify(medicoRepository).deleteById(id);
    }

    @DisplayName("Deveria buscar todos os medicos")
    @Test
    void shouldFindAllMedicos_whenCallFindAll() {
        // Arrange
        var medico1 = gerarMedico();
        var medico2 = gerarMedico();
        var medicoList = List.of(medico1, medico2);

        when(medicoRepository.findAll()).thenReturn(medicoList);

        // Act
        var resultado = medicoRepository.findAll();

        // Assert
        verify(medicoRepository).findAll();
        assertThat(resultado).hasSize(2).containsExactlyInAnyOrder(medico1, medico2);
    }

    private MedicoEntity gerarMedico() {
        return new MedicoEntity(1L, "Medico", "medico@voll.med", "123456", "123", CARDIOLOGIA,
                new EnderecoEntity(), true);
    }
}
