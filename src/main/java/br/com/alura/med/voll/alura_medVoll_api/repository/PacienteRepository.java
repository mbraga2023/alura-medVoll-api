package br.com.alura.med.voll.alura_medVoll_api.repository;

import br.com.alura.med.voll.alura_medVoll_api.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
