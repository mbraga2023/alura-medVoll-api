package br.com.alura.med.voll.alura_medVoll_api.service.validacoes;

import br.com.alura.med.voll.alura_medVoll_api.dto.DadosAgendamentoConsulta;
import br.com.alura.med.voll.alura_medVoll_api.infra.ValidacaoException;
import br.com.alura.med.voll.alura_medVoll_api.repository.MedicoRepository;
import br.com.alura.med.voll.alura_medVoll_api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta{

    @Autowired
    private PacienteRepository repository;

        public void validar(DadosAgendamentoConsulta dados){

            var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());

            if (!pacienteEstaAtivo) {
                throw new ValidacaoException("Consulta não pode ser agendada com paciente inativo");
            }
        }
}
