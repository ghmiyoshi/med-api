package br.com.alura.med.infra.controllers.mappers;

import br.com.alura.med.domain.entities.medico.FabricaDeMedico;
import br.com.alura.med.domain.entities.medico.Medico;
import br.com.alura.med.infra.controllers.requests.DadosCadastroMedico;
import br.com.alura.med.infra.controllers.responses.DadosDetalhamentoMedico;
import br.com.alura.med.infra.controllers.responses.DadosListagemMedico;
import br.com.alura.med.infra.persistence.medico.MedicoEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MedicoMapper {

    private final EnderecoMapper enderecoMapper;

    public Medico toDomain(final MedicoEntity medicoEntity) {
        final var enderecoValueObject = enderecoMapper.toDomain(medicoEntity.getEndereco());
        return new FabricaDeMedico().novo(medicoEntity.getNome(), medicoEntity.getEmail(),
                medicoEntity.getTelefone(), medicoEntity.getCrm(),
                medicoEntity.getEspecialidade(), enderecoValueObject);
    }


    public MedicoEntity toEntity(final Medico medico) {
        return new MedicoEntity(medico.getNome(), medico.getEmail(),
                medico.getTelefone(), medico.getCrm(),
                medico.getEspecialidade(),
                enderecoMapper.toEntity(medico.getEndereco()));
    }

    public DadosDetalhamentoMedico toResponseDetalhamento(final Medico medico) {
        return new DadosDetalhamentoMedico(medico.getNome(), medico.getEmail(),
                medico.getCrm(), medico.getEspecialidade(),
                enderecoMapper.toResponse(medico.getEndereco()));
    }

    public DadosListagemMedico toResponseListagem(final Medico medico) {
        return new DadosListagemMedico(medico.getNome(), medico.getEmail(),
                medico.getCrm(), medico.getEspecialidade().toString(),
                enderecoMapper.toResponse(medico.getEndereco()));
    }

    public Medico toDomain(final DadosCadastroMedico dados) {
        return new FabricaDeMedico().novo(dados.nome(), dados.email(),
                dados.telefone(), dados.crm(), dados.especialidade(),
                enderecoMapper.toDomain(dados.endereco()));
    }
}
