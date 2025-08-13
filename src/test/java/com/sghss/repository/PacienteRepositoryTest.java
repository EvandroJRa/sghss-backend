// Localização: src/test/java/com/sghss/repository/PacienteRepositoryTest.java
package com.sghss.repository;

import com.sghss.model.Paciente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;

@DataJpaTest
class PacienteRepositoryTest {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void deveRetornarTrue_QuandoCpfExistir() {
        // Arrange
        String cpf = "111.222.333-44";
        var paciente = new Paciente("Nome Teste", "email@teste.com", cpf, "senha", LocalDate.now(), "Endereço");
        entityManager.persistAndFlush(paciente);

        // Act
        boolean resultado = pacienteRepository.existsByCpf(cpf);

        // Assert
        assertThat(resultado).isTrue();
    }

    @Test
    void deveRetornarFalse_QuandoCpfNaoExistir() {
        // Arrange
        String cpf = "999.888.777-66";

        // Act
        boolean resultado = pacienteRepository.existsByCpf(cpf);

        // Assert
        assertThat(resultado).isFalse();
    }
}
