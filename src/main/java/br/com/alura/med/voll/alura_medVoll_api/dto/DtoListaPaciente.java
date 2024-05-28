package br.com.alura.med.voll.alura_medVoll_api.dto;

import br.com.alura.med.voll.alura_medVoll_api.models.Especialidades;
import br.com.alura.med.voll.alura_medVoll_api.models.Medico;
import br.com.alura.med.voll.alura_medVoll_api.models.Paciente;

public record DtoListaPaciente(
        Long id,
        String nome,
        String email,
        String telefone
){

    public DtoListaPaciente (Paciente paciente){
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getTelefone());
    }
}
