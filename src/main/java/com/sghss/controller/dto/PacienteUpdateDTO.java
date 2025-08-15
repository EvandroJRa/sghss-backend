
package com.sghss.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PacienteUpdateDTO(
        @NotBlank
        @Size(min = 3, max = 100)
        String nomeCompleto,

        String endereco
) {}