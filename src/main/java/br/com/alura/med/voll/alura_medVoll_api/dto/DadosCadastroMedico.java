package br.com.alura.med.voll.alura_medVoll_api.dto;

import br.com.alura.med.voll.alura_medVoll_api.models.Especialidades;

public record DadosCadastroMedico(
        String nome,
        String email,
        String crm,
        Especialidades especialidade,
        DadosEndereco endereco
) {
}
