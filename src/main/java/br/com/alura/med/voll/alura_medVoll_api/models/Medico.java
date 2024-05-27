package br.com.alura.med.voll.alura_medVoll_api.models;

import br.com.alura.med.voll.alura_medVoll_api.dto.DadosCadastroMedico;
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

    public Medico(DadosCadastroMedico dadosCadastroMedico) {
        this.nome = dadosCadastroMedico.nome();
        this.crm = dadosCadastroMedico.crm();
        this.email = dadosCadastroMedico.email();
        this.endereco = new Endereco(dadosCadastroMedico.endereco());
        this.especialidades = dadosCadastroMedico.especialidade();
        this.telefone = dadosCadastroMedico.telefone();
    }
}
