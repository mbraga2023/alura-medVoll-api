package br.com.alura.med.voll.alura_medVoll_api.repository;

import br.com.alura.med.voll.alura_medVoll_api.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
