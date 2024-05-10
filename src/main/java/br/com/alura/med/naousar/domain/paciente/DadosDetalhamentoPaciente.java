package br.com.alura.med.naousar.domain.paciente;

import br.com.alura.med.infra.persistence.medico.EnderecoEntity;

public record DadosDetalhamentoPaciente(Long id,
                                        String nome,
                                        String email,
                                        String cpf,
                                        String telefone,
                                        EnderecoEntity endereco) {

    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getEndereco());
    }

}
