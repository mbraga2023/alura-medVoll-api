package br.com.alura.med.voll.alura_medVoll_api.dto;

public record DadosCadastroPaciente(
        String nome,
        String email,
        String telefone,
        String cpf,
        DadosEndereco endereco
) {
}
