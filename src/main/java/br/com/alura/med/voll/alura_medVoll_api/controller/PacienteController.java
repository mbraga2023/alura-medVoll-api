package br.com.alura.med.voll.alura_medVoll_api.controller;

import br.com.alura.med.voll.alura_medVoll_api.dto.*;
import br.com.alura.med.voll.alura_medVoll_api.models.Medico;
import br.com.alura.med.voll.alura_medVoll_api.models.Paciente;
import br.com.alura.med.voll.alura_medVoll_api.repository.PacienteRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("paciente")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

/*    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroPaciente dadosPaciente){

        System.out.println(dadosPaciente);
    }*/

    @PostMapping("")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dadosCadastroPaciente, UriComponentsBuilder uriComponentsBuilder) {
        var paciente = new Paciente(dadosCadastroPaciente);
        pacienteRepository.save(paciente);
        var uri = uriComponentsBuilder.path("/paciente/{id}")
                .buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping("/lista")
    public ResponseEntity<Page<DtoListaPaciente>> listar(@PageableDefault(size=10, sort = {"nome"}) Pageable paginacao){
        var page = pacienteRepository.findAllByAtivoTrue(paginacao).map(DtoListaPaciente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DtoAtualizarPaciente dtoAtualizar){
        var paciente = pacienteRepository.getReferenceById(dtoAtualizar.id());
        paciente.atualizarInformacoes(dtoAtualizar);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
}
