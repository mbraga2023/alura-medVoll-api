package br.com.alura.med.voll.alura_medVoll_api.models;

import br.com.alura.med.voll.alura_medVoll_api.dto.DadosCadastroMedico;
import br.com.alura.med.voll.alura_medVoll_api.dto.DtoAtualizarMedico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Medico")
@Table(name= "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidades especialidades;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Medico(DadosCadastroMedico dadosCadastroMedico) {
        this.nome = dadosCadastroMedico.nome();
        this.crm = dadosCadastroMedico.crm();
        this.email = dadosCadastroMedico.email();
        this.endereco = new Endereco(dadosCadastroMedico.endereco());
        this.especialidades = dadosCadastroMedico.especialidade();
        this.telefone = dadosCadastroMedico.telefone();
        this.ativo = true;
    }

    public void atualizarInformacoes(DtoAtualizarMedico dtoAtualizar) {
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
