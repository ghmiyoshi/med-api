package br.com.alura.med.model.dto;

import br.com.alura.med.model.Especialidade;
import br.com.alura.med.model.Medico;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class MedicoDTO {

    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Especialidade especialidade;

    public static MedicoDTO convertTo(Medico medico) {
        var medicoDto = new MedicoDTO();
        BeanUtils.copyProperties(medico, medicoDto);
        return medicoDto;
    }

}
