package br.com.alura.med.voll.alura_medVoll_api.repository;

import br.com.alura.med.voll.alura_medVoll_api.dto.DadosCadastroMedico;
import br.com.alura.med.voll.alura_medVoll_api.dto.DadosCadastroPaciente;
import br.com.alura.med.voll.alura_medVoll_api.dto.DadosEndereco;
import br.com.alura.med.voll.alura_medVoll_api.models.Consulta;
import br.com.alura.med.voll.alura_medVoll_api.models.Especialidades;
import br.com.alura.med.voll.alura_medVoll_api.models.Medico;
import br.com.alura.med.voll.alura_medVoll_api.models.Paciente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE )
@ActiveProfiles("test")
class MedicoRepositoryTest_v2 {
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @BeforeEach
    void setUp() {
        // Clear the database before setting up test data
        em.clear();

        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        // Generate unique email and CRM for each doctor
        String uniqueEmail = "medico" + UUID.randomUUID().toString() + "@voll.med";
        String uniqueCrm = generateRandomCrm();

        var medico = new Medico(dadosMedico("Medico", uniqueEmail, uniqueCrm, Especialidades.CARDIOLOGIA));
        var paciente = new Paciente(dadosPaciente("Paciente", "paciente@email.com", "00000000000"));

        em.persist(medico);
        em.persist(paciente);
        em.persist(new Consulta(null, medico, paciente, proximaSegundaAs10));
        em.flush(); // Flush to synchronize with the database
    }

    private String generateRandomCrm() {
        // Generate a random 6-digit number for the CRM
        int min = 100000;
        int max = 999999;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return String.format("%06d", randomNum);
    }

    @Test
    @DisplayName("Deveria devolver null quando único médico cadastrado não está disponível na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidades.CARDIOLOGIA, proximaSegundaAs10);
        assertThat(medicoLivre).isNull();
    }

   @Test
    @DisplayName("Deveria devolver médico quando ele estiver disponível na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var medico = new Medico(dadosMedico("Medico", "medico@voll.med", "123456", Especialidades.CARDIOLOGIA));
        em.persist(medico);

        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidades.CARDIOLOGIA, proximaSegundaAs10);
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidades especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                crm,
                especialidade,
                dadosEndereco(),
                "61999999999"
        );
    }


    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPaciente(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}
