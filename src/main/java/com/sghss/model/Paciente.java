package com.sghss.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true) // Importante para o Lombok em classes filhas
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pacientes")
@PrimaryKeyJoinColumn(name = "id_usuario") // Chave para ligar com a tabela 'usuarios'
public class Paciente extends Usuario { // 'extends' cria a herança

    @Column(nullable = false)
    private LocalDate dataNascimento;

    private String endereco;

    // Construtor específico para facilitar a criação de um paciente
    public Paciente(String nomeCompleto, String email, String cpf, String senha, LocalDate dataNascimento, String endereco) {
        // Chama o construtor da classe pai (Usuario) para preencher os dados comuns
        super(null, nomeCompleto, email, cpf, senha, Perfil.PACIENTE, true, null, null);
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }
}
