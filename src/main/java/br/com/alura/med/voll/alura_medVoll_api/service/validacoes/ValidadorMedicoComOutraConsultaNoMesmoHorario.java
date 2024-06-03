package br.com.alura.med.voll.alura_medVoll_api.service.validacoes;

import br.com.alura.med.voll.alura_medVoll_api.dto.DadosAgendamentoConsulta;
import br.com.alura.med.voll.alura_medVoll_api.infra.ValidacaoException;
import br.com.alura.med.voll.alura_medVoll_api.repository.ConsultaRepository;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorMedicoComOutraConsultaNoMesmoHorario {
    private ConsultaRepository repository;

        public void validar(DadosAgendamentoConsulta dados){

            var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
            if(medicoPossuiOutraConsultaNoMesmoHorario){
                throw new ValidacaoException("Médico já tem consulta agendada para este horário. ");
            }
        }
}
