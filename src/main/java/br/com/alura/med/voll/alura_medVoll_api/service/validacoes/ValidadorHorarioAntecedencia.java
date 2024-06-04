package br.com.alura.med.voll.alura_medVoll_api.service.validacoes;

import br.com.alura.med.voll.alura_medVoll_api.dto.DadosAgendamentoConsulta;
import br.com.alura.med.voll.alura_medVoll_api.infra.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsulta{
        public void validar(DadosAgendamentoConsulta dados){
            var dataConsulta = dados.data();

            var agora = LocalDateTime.now();
            var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

            if(diferencaEmMinutos < 30){
                throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos. ");
            }
        }
}
