package br.com.alura.med.controller;

import br.com.alura.med.domain.medico.DadosAtualizacaoMedico;
import br.com.alura.med.domain.medico.DadosCadastroMedico;
import br.com.alura.med.domain.medico.DadosDetalhamentoMedico;
import br.com.alura.med.domain.medico.DadosListagemMedico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ProblemDetail;

@Tag(name = "Medico", description = "APIs relacionadas a Médicos")
public interface MedicoControllerOpenApi {

    @Operation(summary = "Cadastrar médico", description = "Cadastra um novo médico")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Médico cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    DadosDetalhamentoMedico cadastrar(@Parameter(description = "Dados para cadastro do médico") DadosCadastroMedico dados);

    @Operation(summary = "Listar médicos", description = "Lista todos os médicos cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida"),
    })
    Page<DadosListagemMedico> listar(Pageable pageable);

    @Operation(summary = "Atualizar médico", responses = {
            @ApiResponse(responseCode = "200", description = "Médico atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    DadosDetalhamentoMedico atualizar(@Parameter(description = "Dados para atualização do médico") DadosAtualizacaoMedico dados);

    @Operation(summary = "Excluir médico", responses = {
            @ApiResponse(responseCode = "204", description = "Médico excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    void excluir(@Parameter(description = "ID do médico") Long id);

    @Operation(summary = "Detalhar médico", responses = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    DadosDetalhamentoMedico detalhar(@Parameter(description = "ID do médico") Long id);
}