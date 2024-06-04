package br.com.alura.med.infra.persistence.medico;

import static br.com.alura.med.domain.entities.medico.Especialidade.CARDIOLOGIA;
import static org.assertj.core.api.Assertions.assertThat;

import br.com.alura.med.domain.entities.medico.Especialidade;
import br.com.alura.med.infra.persistence.consulta.ConsultaEntity;
import br.com.alura.med.infra.persistence.consulta.ConsultaRepository;
import br.com.alura.med.infra.persistence.paciente.PacienteEntity;
import br.com.alura.med.infra.persistence.paciente.PacienteRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
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
        var proximaSegundaAs10 =
                LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456",
                CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        //when ou act
        var medicoLivre =
                medicoRepository.escolherMedicoAleatorioLivreNaData(CARDIOLOGIA,
                        proximaSegundaAs10);

        //then ou assert
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
    void shouldReturnMedico_whenItsAvailable() {
        //given ou arrange
        var proximaSegundaAs10 =
                LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456",
                CARDIOLOGIA);

        //when ou act
        var medicoLivre =
                medicoRepository.escolherMedicoAleatorioLivreNaData(CARDIOLOGIA,
                        proximaSegundaAs10);

        //then ou assert
        assertThat(medicoLivre)
                .isNotNull()
                .isInstanceOf(MedicoEntity.class)
                .isEqualTo(medico);
        assertThat(medicoLivre.getEspecialidade()).isEqualTo(CARDIOLOGIA);
    }

    private void cadastrarConsulta(MedicoEntity medico, PacienteEntity paciente,
                                   LocalDateTime data) {
        consultaRepository.save(new ConsultaEntity(null, medico, paciente, data));
    }

    private MedicoEntity cadastrarMedico(String nome, String email, String crm,
                                         Especialidade especialidade) {
        var medico = new MedicoEntity(nome, email, null, null, especialidade, null);
        return medicoRepository.save(medico);
    }

    private PacienteEntity cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new PacienteEntity(nome, email, null, cpf, null);
        return pacienteRepository.save(paciente);
    }
}
