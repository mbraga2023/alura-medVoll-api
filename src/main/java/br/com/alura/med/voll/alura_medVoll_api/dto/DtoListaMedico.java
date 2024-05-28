package br.com.alura.med.voll.alura_medVoll_api.dto;

import br.com.alura.med.voll.alura_medVoll_api.models.Especialidades;
import br.com.alura.med.voll.alura_medVoll_api.models.Medico;

public record DtoListaMedico (
        Long id,
        String nome,
        String email,
        String crm,
        Especialidades especialidade
){
public DtoListaMedico (Medico medico){
    this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidades());
}

}
