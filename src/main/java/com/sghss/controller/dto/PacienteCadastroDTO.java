// Conteúdo CORRETO para o arquivo PacienteCadastroDTO.java
package com.sghss.controller.dto;

import java.time.LocalDate;

public record PacienteCadastroDTO(
        String nomeCompleto,
        String email,
        String cpf,
        String senha,
        LocalDate dataNascimento,
        String endereco
) {}