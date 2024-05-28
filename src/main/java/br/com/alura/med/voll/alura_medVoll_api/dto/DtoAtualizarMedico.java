package br.com.alura.med.voll.alura_medVoll_api.dto;

import br.com.alura.med.voll.alura_medVoll_api.models.Endereco;
import jakarta.validation.constraints.NotNull;

public record DtoAtualizarMedico(

        @NotNull Long id,
        String nome,
        String telefone,
        DadosEndereco endereco

) {
}
