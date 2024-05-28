package br.com.alura.med.voll.alura_medVoll_api.controller;

import br.com.alura.med.voll.alura_medVoll_api.dto.DadosCadastroMedico;
import br.com.alura.med.voll.alura_medVoll_api.dto.DtoAtualizarMedico;
import br.com.alura.med.voll.alura_medVoll_api.dto.DtoListaMedico;
import br.com.alura.med.voll.alura_medVoll_api.models.Medico;
import br.com.alura.med.voll.alura_medVoll_api.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico) {
        medicoRepository.save(new Medico(dadosCadastroMedico));
    }

    /* SEM PAGINAÇÃO
    @GetMapping ("/lista")
    public List<DtoListaMedico> listar(){
        return medicoRepository.findAll().stream().map(DtoListaMedico::new).toList();
    }*/

    @GetMapping ("")
    public Page<DtoListaMedico> listar(@PageableDefault(size=10, sort = {"nome"}) Pageable paginacao){
        return medicoRepository.findAllByAtivoTrue(paginacao).map(DtoListaMedico::new);
    }

    // chamada de paginação via front-end
    //http://localhost:8080/medicos/lista?size=1&page=2
    //ordenação
    //http://localhost:8080/medicos/lista?sort=nome
    //http://localhost:8080/medicos/lista?sort=nome,desc

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DtoAtualizarMedico dtoAtualizar){
        var medico = medicoRepository.getReferenceById(dtoAtualizar.id());
        medico.atualizarInformacoes(dtoAtualizar);
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
    public void excluir(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
    }

}
