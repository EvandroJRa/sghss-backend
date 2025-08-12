package com.sghss.controller.dto;

public record AdminCadastroDTO(
        String nomeCompleto,
        String email,
        String cpf,
        String senha,
        String cargo
) {}