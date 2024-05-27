package br.com.alura.med.voll.alura_medVoll_api.controller;

import br.com.alura.med.voll.alura_medVoll_api.dto.DadosCadastroMedico;
import br.com.alura.med.voll.alura_medVoll_api.models.Medico;
import br.com.alura.med.voll.alura_medVoll_api.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

/*
    PostMapping("")
    public void cadastrar(@RequestBody String json){ // recebe o Json completo
        System.out.println(json);
    }*/

    @PostMapping("")
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico) {
    medicoRepository.save(new Medico(dadosCadastroMedico));
    }
}
