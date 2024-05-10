package br.com.alura.med.naousar.domain.paciente;

import br.com.alura.med.infra.persistence.medico.EnderecoEntity;

public record DadosListagemPaciente(Long id,
                                    String nome,
                                    String email,
                                    String cpf,
                                    EnderecoEntity endereco) {

    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(),
                paciente.getEndereco());
    }

}
