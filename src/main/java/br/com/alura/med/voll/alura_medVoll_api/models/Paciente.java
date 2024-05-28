package br.com.alura.med.voll.alura_medVoll_api.models;

import br.com.alura.med.voll.alura_medVoll_api.dto.DadosCadastroPaciente;
import br.com.alura.med.voll.alura_medVoll_api.dto.DtoAtualizarPaciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Paciente")
@Table(name= "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Paciente(DadosCadastroPaciente dadosCadastroPaciente) {
        this.nome = dadosCadastroPaciente.nome();
        this.email = dadosCadastroPaciente.email();
        this.cpf = dadosCadastroPaciente.cpf();
        this.telefone = dadosCadastroPaciente.telefone();
        this.endereco = new Endereco(dadosCadastroPaciente.endereco());
        this.ativo = true;

    }

    public void atualizarInformacoes(DtoAtualizarPaciente dtoAtualizar) {
        if(dtoAtualizar.nome() != null){
            this.nome = dtoAtualizar.nome();
        }
        if(dtoAtualizar.telefone() != null){
            this.telefone = dtoAtualizar.telefone();
        }
        if(dtoAtualizar.endereco() != null){
            this.endereco.atualizarInformações(dtoAtualizar.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}

