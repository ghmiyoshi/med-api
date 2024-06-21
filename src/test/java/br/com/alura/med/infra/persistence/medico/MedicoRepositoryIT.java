package br.com.alura.med.infra.persistence.medico;

import static br.com.alura.med.domain.entities.medico.Especialidade.CARDIOLOGIA;
import static org.assertj.core.api.Assertions.assertThat;

import br.com.alura.med.infra.persistence.consulta.ConsultaRepository;
import br.com.alura.med.infra.persistence.paciente.PacienteRepository;
import br.com.alura.med.mock.ConsultaEntityMock;
import br.com.alura.med.mock.MedicoEntityMock;
import br.com.alura.med.mock.PacienteEntityMock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
//Anotações usadas quando é para configurar o mesmo banco da aplicação MySQL
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class MedicoRepositoryIT {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @DisplayName("Deveria retornar lista de medicos com tamanho maior que zero")
    @Test
    void shouldSizeBeGreaterThanZero() {
        final var medicos = medicoRepository.findAll();

        assertThat(medicos).isNotNull().hasSizeGreaterThan(0);
        assertThat(medicos).hasSize(1);
    }

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
    void shouldReturnNull_whenMedicoItsNotAvailableInDate() {
        //given ou arrange
        final var proximaSegundaAs10 =
                LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        final var medico = medicoRepository.save(MedicoEntityMock.newMedico("Medico", "medico" +
                "@voll.med", "123456", CARDIOLOGIA));

        final var paciente = pacienteRepository.save(PacienteEntityMock.newPaciente("Paciente",
                "paciente" +
                        "@email.com",
                "00000000000"));

        consultaRepository.save(ConsultaEntityMock.newConsulta(medico, paciente,
                proximaSegundaAs10));

        //when ou act
        final var medicoLivre =
                medicoRepository.escolherMedicoAleatorioLivreNaData(CARDIOLOGIA,
                        proximaSegundaAs10);

        //then ou assert
        assertThat(medicoLivre).isInstanceOf(Optional.class).isEmpty();
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
    void shouldReturnMedico_whenItsAvailable() {
        //given ou arrange
        final var proximaSegundaAs10 =
                LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        final var medico = medicoRepository.save(MedicoEntityMock.newMedico("Medico", "medico" +
                "@voll.med", "123456", CARDIOLOGIA));

        //when ou act
        final var medicoLivre =
                medicoRepository.escolherMedicoAleatorioLivreNaData(CARDIOLOGIA,
                        proximaSegundaAs10);

        //then ou assert
        assertThat(medicoLivre)
                .isNotNull()
                .isInstanceOf(Optional.class)
                .isEqualTo(Optional.of(medico));
        assertThat(medicoLivre.get().getEspecialidade()).isEqualTo(CARDIOLOGIA);
    }
}
