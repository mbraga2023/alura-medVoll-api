package br.com.alura.med.voll.alura_medVoll_api.repository;

import br.com.alura.med.voll.alura_medVoll_api.models.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);
}
