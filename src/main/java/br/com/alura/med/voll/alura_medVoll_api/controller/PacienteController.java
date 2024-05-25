package br.com.alura.med.voll.alura_medVoll_api.controller;

import br.com.alura.med.voll.alura_medVoll_api.dto.DadosCadastroMedico;
import br.com.alura.med.voll.alura_medVoll_api.dto.DadosCadastroPaciente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("paciente")
public class PacienteController {

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroPaciente dadosPaciente){
        System.out.println(dadosPaciente);
    }
}
