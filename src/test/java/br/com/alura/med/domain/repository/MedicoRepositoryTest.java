package br.com.alura.med.domain.repository;

import br.com.alura.med.domain.entities.medico.Especialidade;
import br.com.alura.med.domain.entities.medico.Medico;
import br.com.alura.med.infra.controllers.requests.DadosCadastroMedico;
import br.com.alura.med.infra.controllers.requests.DadosCadastroPaciente;
import br.com.alura.med.infra.controllers.requests.DadosEndereco;
import br.com.alura.med.infra.persistence.consulta.Consulta;
import br.com.alura.med.infra.persistence.medico.MedicoRepository;
import br.com.alura.med.infra.persistence.paciente.Paciente;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest
@ActiveProfiles("test")
//Anotações usadas quando é para configurar o mesmo banco da aplicação MySQL
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
    void shouldReturnNull_whenMedicoItsNotAvailableInDate() {
        //given ou arrange
        var proximaSegundaAs10 =
                LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456",
                Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        //when ou act
        var medicoLivre =
                medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA,
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
                Especialidade.CARDIOLOGIA);

        //when ou act
        var medicoLivre =
                medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA,
                        proximaSegundaAs10);

        //then ou assert
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm,
                                   Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm,
                                            Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPaciente(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

}
