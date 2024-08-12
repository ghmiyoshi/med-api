package br.com.alura.med.infra.controllers;

import static br.com.alura.med.domain.entities.medico.Especialidade.CARDIOLOGIA;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.alura.med.application.usecases.medico.BuscarMedico;
import br.com.alura.med.application.usecases.medico.CriarMedico;
import br.com.alura.med.application.usecases.medico.ExcluirMedico;
import br.com.alura.med.application.usecases.medico.ListarMedicos;
import br.com.alura.med.domain.entities.medico.Medico;
import br.com.alura.med.infra.controllers.requests.DadosCadastroMedico;
import br.com.alura.med.infra.controllers.requests.DadosEndereco;
import br.com.alura.med.infra.controllers.responses.DadosDetalhamentoMedico;
import br.com.alura.med.mock.DadosCadastroMedicoMock;
import br.com.alura.med.mock.MedicoMock;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroMedico> dadosCadastroMedicoJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> dadosDetalhamentoMedicoJson;

    @MockBean
    private CriarMedico criarMedico;

    @MockBean
    private BuscarMedico buscarMedico;

    @MockBean
    private ExcluirMedico excluirMedico;

    @MockBean
    private ListarMedicos listarMedicos;

    @Nested
    class CadastrarMedico {

        @Test
        @DisplayName("Deve retornar status 201")
        @WithMockUser(roles = "ADMIN")
        void deveRetornarStatus201() throws Exception {
            final var jsonBody = dadosCadastroMedicoJson.write(DadosCadastroMedicoMock.newDados()).getJson();

            when(criarMedico.execute(any(Medico.class))).thenReturn(MedicoMock.newMedico());

            final var jsonEsperado =
                    dadosDetalhamentoMedicoJson.write(new DadosDetalhamentoMedico("Nome",
                            "email@email.com", "123456", CARDIOLOGIA, new DadosEndereco("Rua"
                            , "Bairro", "01234567", "São Paulo", "SP", "32A", "123"))).getJson();

            mvc.perform(post("/medicos").contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().json(jsonEsperado));

            verify(criarMedico).execute(any(Medico.class));
        }
    }

    @Nested
    class DetalharMedico {

        @Test
        @DisplayName("Deve retornar status 200")
        @WithMockUser(roles = "ADMIN")
        void deveRetornarStatus200() throws Exception {
            final var medico = MedicoMock.newMedico();
            when(buscarMedico.execute(any(Long.class))).thenReturn(medico);

            mvc.perform(get("/medicos/{id}", 1L))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.nome").value(medico.getNome()))
                    .andExpect(jsonPath("$.email").value(medico.getEmail()));

            verify(buscarMedico).execute(any(Long.class));
        }
    }

    @Nested
    class ApagarMedico {

        @Test
        @DisplayName("Deve retornar status 204")
        @WithMockUser(roles = "ADMIN")
        void devePermitirApagarMedico() throws Exception {
            doNothing().when(excluirMedico).execute(any(Long.class));

            mvc.perform(delete("/medicos/{id}", 1L))
                    .andExpect(status().isNoContent())
                    .andExpect(content().string(""));

            verify(excluirMedico).execute(any(Long.class));
        }
    }

    @Nested
    class ListarMedico {

        @Test
        void devePermitirListarMedicos() throws Exception {
            final var medico = MedicoMock.newMedico();
            Page<Medico> page = new PageImpl<>(Collections.singletonList(medico));
            when(listarMedicos.execute(any(Pageable.class))).thenReturn(page);

            mvc.perform(get("/medicos")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content", not(empty())))
                    .andExpect(jsonPath("$.total_elements").value(1))
                    .andExpect(jsonPath("$.total_pages").value(1))
                    .andExpect(jsonPath("$.content[0].nome").value(medico.getNome().toString()))
                    .andExpect(jsonPath("$.content[0].email").value(medico.getEmail()));

            verify(listarMedicos).execute(any(Pageable.class));
        }
    }
}