package br.com.alura.med.voll.alura_medVoll_api.dto;

import jakarta.validation.constraints.NotNull;

public record DtoAtualizarPaciente(

        @NotNull Long id,
        String nome,
        String telefone,
        DadosEndereco endereco

) {
}
