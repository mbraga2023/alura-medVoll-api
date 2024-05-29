package br.com.alura.med.voll.alura_medVoll_api.controller;

import br.com.alura.med.voll.alura_medVoll_api.dto.*;
import br.com.alura.med.voll.alura_medVoll_api.models.Medico;
import br.com.alura.med.voll.alura_medVoll_api.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

/* PRINT OBJETO
    PostMapping("")
    public void cadastrar(@RequestBody String json){
        System.out.println(json);
    }*/

    @PostMapping("")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico, UriComponentsBuilder uriComponentsBuilder) {
        var medico = new Medico(dadosCadastroMedico);
        medicoRepository.save(medico);

        var uri = uriComponentsBuilder.path("/medicos/{id}")
                .buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(new DadosDetalhamentoMedico(medico));
    }

    /* SEM PAGINAÇÃO
    @GetMapping ("/lista")
    public List<DtoListaMedico> listar(){
        return medicoRepository.findAll().stream().map(DtoListaMedico::new).toList();
    }*/

    @GetMapping ("")
    public ResponseEntity<Page<DtoListaMedico>>listar(@PageableDefault(size=10, sort = {"nome"}) Pageable paginacao){
        var page = medicoRepository.findAllByAtivoTrue(paginacao).map(DtoListaMedico::new);
        return ResponseEntity.ok(page);

    }

    // chamada de paginação via front-end
    //http://localhost:8080/medicos/lista?size=1&page=2
    //ordenação
    //http://localhost:8080/medicos/lista?sort=nome
    //http://localhost:8080/medicos/lista?sort=nome,desc

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DtoAtualizarMedico dtoAtualizar){
        var medico = medicoRepository.getReferenceById(dtoAtualizar.id());
        medico.atualizarInformacoes(dtoAtualizar);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

/*    EXCLUSÃO FÍSICA
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
    medicoRepository.deleteById(id);
    }*/

    //exclusão lógica
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

}
