package com.sghss.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "administradores")
@PrimaryKeyJoinColumn(name = "id_usuario") // Chave para ligar com a tabela 'usuarios'

public class Administrador extends Usuario { // Herda de Usuario

    private String cargo;

    // Construtor específico para facilitar a criação de um administrador
    public Administrador(String nomeCompleto, String email, String cpf, String senha, String cargo) {
        // Chama o construtor da classe pai (Usuario)
        super(null, nomeCompleto, email, cpf, senha, Perfil.ADMIN, true, null, null);
        this.cargo = cargo;
    }
}
