package com.sghss.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record PacienteCadastroDTO(
        @NotBlank(message = "Nome não pode estar em branco")
        @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
        String nomeCompleto,

        @NotBlank(message = "Email не pode estar em branco")
        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "CPF не pode estar em branco")
        String cpf,

        @NotBlank(message = "Senha не pode estar em branco")
        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        String senha,

        @NotNull(message = "Data de nascimento não pode ser nula")
        LocalDate dataNascimento,

        String endereco
) {}