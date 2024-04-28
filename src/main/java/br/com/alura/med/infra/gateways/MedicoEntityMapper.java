package br.com.alura.med.infra.gateways;

import br.com.alura.med.domain.entities.medico.Medico;
import br.com.alura.med.infra.persistence.MedicoEntity;

public class MedicoEntityMapper {

    public MedicoEntity toEntity(Medico medico) {
        return new MedicoEntity(medico);
    }

    public Medico toDomain(MedicoEntity medicoEntity) {
        return new Medico(medicoEntity.getNome(), medicoEntity.getEmail(),
                medicoEntity.getTelefone());
    }
}
