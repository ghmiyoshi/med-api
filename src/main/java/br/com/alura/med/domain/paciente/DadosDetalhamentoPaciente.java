package br.com.alura.med.domain.paciente;

import br.com.alura.med.domain.endereco.Endereco;

public record DadosDetalhamentoPaciente(Long id,
                                        String nome,
                                        String email,
                                        String cpf,
                                        String telefone,
                                        Endereco endereco) {

    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(),
             paciente.getEndereco());
    }

}
