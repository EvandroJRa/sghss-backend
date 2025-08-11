package com.sghss.controller.dto;

import com.sghss.model.Paciente;
import java.time.LocalDate;

public record PacienteDTO(
        Long idUsuario,
        String nomeCompleto,
        String email,
        LocalDate dataNascimento
) {
    // Construtor que serve como "conversor"
    public PacienteDTO(Paciente paciente) {
        this(paciente.getIdUsuario(), paciente.getNomeCompleto(), paciente.getEmail(), paciente.getDataNascimento());
    }
}