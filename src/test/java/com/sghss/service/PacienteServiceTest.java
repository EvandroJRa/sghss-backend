
package com.sghss.service;

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

    @InjectMocks
    private PacienteService pacienteService;

    @Test
    void naoDeveCriarPaciente_QuandoCpfJaExistir() {

        String cpfExistente = "123.456.789-00";
        when(pacienteRepository.existsByCpf(cpfExistente)).thenReturn(true);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            // precisará de um método em seu service que receba o DTO
            // pacienteService.criar(dadosComCpfRepetido);
        });

        verify(pacienteRepository, never()).save(any());
    }
}
