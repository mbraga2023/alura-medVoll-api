package br.com.alura.med.voll.alura_medVoll_api.controller;

import br.com.alura.med.voll.alura_medVoll_api.dto.*;
import br.com.alura.med.voll.alura_medVoll_api.models.Medico;
import br.com.alura.med.voll.alura_medVoll_api.models.Paciente;
import br.com.alura.med.voll.alura_medVoll_api.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dadosCadastroPaciente) {
        pacienteRepository.save(new Paciente(dadosCadastroPaciente));
    }

    @GetMapping("/lista")
    public Page<DtoListaPaciente> listar(@PageableDefault(size=10, sort = {"nome"}) Pageable paginacao){
        return pacienteRepository.findAllByAtivoTrue(paginacao).map(DtoListaPaciente::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DtoAtualizarPaciente dtoAtualizar){
        var paciente = pacienteRepository.getReferenceById(dtoAtualizar.id());
        paciente.atualizarInformacoes(dtoAtualizar);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir();
    }
}
