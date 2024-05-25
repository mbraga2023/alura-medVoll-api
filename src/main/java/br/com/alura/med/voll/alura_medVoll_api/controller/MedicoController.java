package br.com.alura.med.voll.alura_medVoll_api.controller;

import br.com.alura.med.voll.alura_medVoll_api.dto.DadosCadastroMedico;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

/*
    PostMapping("")
    public void cadastrar(@RequestBody String json){ // recebe o Json completo
        System.out.println(json);
    }*/

    @PostMapping("")
    public void cadastrar(@RequestBody DadosCadastroMedico dadosCadastroMedico) {
        System.out.println(dadosCadastroMedico);
    }
}
