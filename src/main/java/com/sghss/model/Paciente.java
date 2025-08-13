package com.sghss.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pacientes")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Paciente extends Usuario {

    @Column(nullable = false)
    private LocalDate dataNascimento;

    private String endereco;

    public Paciente(String nomeCompleto, String email, String cpf, String senha, LocalDate dataNascimento, String endereco) {
        super(null, nomeCompleto, email, cpf, senha, Perfil.PACIENTE, true, Instant.now(), null);
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }
}