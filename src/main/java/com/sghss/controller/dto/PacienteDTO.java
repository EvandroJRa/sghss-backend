package com.sghss.controller.dto;

import com.sghss.model.Paciente;
import java.time.LocalDate;

public record PacienteDTO(
        Long idUsuario,
        String nomeCompleto,
        String email,
        LocalDate dataNascimento,
        String endereco // dever ser removido na produção para preservar a informação do usuário
) {
    public PacienteDTO(Paciente paciente) {
        this(paciente.getIdUsuario(), paciente.getNomeCompleto(), paciente.getEmail(), paciente.getDataNascimento(), paciente.getEndereco());
    }
}