package br.com.alura.med.voll.alura_medVoll_api.dto;

import br.com.alura.med.voll.alura_medVoll_api.models.Especialidades;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "\\d{4,6}") String crm,
        @NotNull Especialidades especialidade,
        @NotNull @Valid DadosEndereco endereco,
        @NotBlank String telefone
) {
}
