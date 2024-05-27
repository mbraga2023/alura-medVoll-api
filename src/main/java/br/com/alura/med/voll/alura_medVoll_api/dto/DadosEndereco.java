package br.com.alura.med.voll.alura_medVoll_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank String logradouro,
        @NotBlank String bairro,
        @NotBlank @Pattern(regexp = "\\d{8}") String cep,
        @NotBlank String cidade,
        @NotBlank String uf,
        String complemento,
        String numero) {
}
