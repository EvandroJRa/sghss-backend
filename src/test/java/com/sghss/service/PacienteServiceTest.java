
package com.sghss.service;

import com.sghss.model.Paciente;
import com.sghss.repository.PacienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    // Supondo que você tenha uma exceção customizada como planejado
    // Se não tiver, pode trocar para RuntimeException por enquanto
    // import com.sghss.exception.ValidacaoException;

    @InjectMocks
    private PacienteService pacienteService;

    @Test
    void naoDeveCriarPaciente_QuandoCpfJaExistir() {
        // Arrange
        // Crie um DTO com dados fictícios para o teste
        // import com.sghss.controller.dto.PacienteCadastroDTO;
        // PacienteCadastroDTO dadosComCpfRepetido = new PacienteCadastroDTO(...);

        String cpfExistente = "123.456.789-00";
        when(pacienteRepository.existsByCpf(cpfExistente)).thenReturn(true);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            // Você precisará de um método em seu service que receba o DTO
            // pacienteService.criar(dadosComCpfRepetido);
        });

        verify(pacienteRepository, never()).save(any());
    }
}
