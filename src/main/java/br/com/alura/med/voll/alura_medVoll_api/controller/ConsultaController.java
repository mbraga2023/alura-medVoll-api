package br.com.alura.med.voll.alura_medVoll_api.controller;


import br.com.alura.med.voll.alura_medVoll_api.dto.DadosAgendamentoConsulta;
import br.com.alura.med.voll.alura_medVoll_api.dto.DadosDetalhamentoConsulta;
import br.com.alura.med.voll.alura_medVoll_api.repository.ConsultaRepository;
import br.com.alura.med.voll.alura_medVoll_api.service.AgendaConsultasService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaConsultasService agendaConsultasService;

    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {

        var dto = agendaConsultasService.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("")
    public ResponseEntity<Page<DadosDetalhamentoConsulta>> listarConsultas(
            @PageableDefault(size=10, sort = {"id"}) Pageable paginacao
    ){
        var page = consultaRepository.findAll(paginacao).map(DadosDetalhamentoConsulta::new);
        return ResponseEntity.ok(page);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity  excluir(@PathVariable Long id){
        consultaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}