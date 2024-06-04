package br.com.alura.med.voll.alura_medVoll_api.service.validacoes;

import br.com.alura.med.voll.alura_medVoll_api.dto.DadosAgendamentoConsulta;
import br.com.alura.med.voll.alura_medVoll_api.infra.ValidacaoException;
import br.com.alura.med.voll.alura_medVoll_api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoConsulta{
    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();

        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);

        var pacientePossuiOutraConsultaNoDia = repository
                .existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario );

        if(pacientePossuiOutraConsultaNoDia){
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia. ");
        }
    }
}
