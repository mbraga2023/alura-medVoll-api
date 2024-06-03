package br.com.alura.med.voll.alura_medVoll_api.controller;


import br.com.alura.med.voll.alura_medVoll_api.dto.DadosAgendamentoConsulta;
import br.com.alura.med.voll.alura_medVoll_api.dto.DadosDetalhamentoConsulta;
import br.com.alura.med.voll.alura_medVoll_api.service.AgendaConsultasService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaConsultasService agendaConsultasService;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        //System.out.println(dados);
        agendaConsultasService.agendar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(null, null, null, null));
    }

}