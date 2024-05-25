package br.com.alura.med.voll.alura_medVoll_api.dto;

public record DadosEndereco(String logradouro,
                            String bairro,
                            String cep,
                            String cidade,
                            String uf,
                            String complemento,
                            String numero) {
}
