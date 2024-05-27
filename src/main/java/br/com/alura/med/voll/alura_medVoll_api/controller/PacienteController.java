package br.com.alura.med.voll.alura_medVoll_api.controller;

import br.com.alura.med.voll.alura_medVoll_api.dto.DadosCadastroMedico;
import br.com.alura.med.voll.alura_medVoll_api.dto.DadosCadastroPaciente;
import br.com.alura.med.voll.alura_medVoll_api.models.Medico;
import br.com.alura.med.voll.alura_medVoll_api.models.Paciente;
import br.com.alura.med.voll.alura_medVoll_api.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("paciente")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

/*    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroPaciente dadosPaciente){

        System.out.println(dadosPaciente);
    }*/

    @PostMapping("")
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroPaciente dadosCadastroPaciente) {
        pacienteRepository.save(new Paciente(dadosCadastroPaciente));
    }
}
