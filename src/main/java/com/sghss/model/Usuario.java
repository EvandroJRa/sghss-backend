package com.sghss.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Inheritance(strategy = InheritanceType.JOINED)

@Data // Anotação do Lombok: gera Getters, Setters, toString, equals, hashCode.
@NoArgsConstructor // Lombok: gera um construtor sem argumentos.
@AllArgsConstructor // Lombok: gera um construtor com todos os argumentos.
@Entity // Anotação do JPA: informa que esta classe é uma entidade do banco de dados.
@Table(name = "usuarios") // Define o nome da tabela no banco de dados.

public class Usuario {

    @Id // Marca o campo como a chave primária (Primary Key) da tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco de dados irá gerar o valor do ID automaticamente.
    private Long idUsuario;

    @Column(nullable = false) // A coluna não pode ser nula no banco.
    private String nomeCompleto;

    @Column(nullable = false, unique = true) // Não pode ser nulo e deve ser único.
    private String email;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String senha; // Armazenaremos a senha já criptografada (hash).

    @Enumerated(EnumType.STRING) // Diz ao JPA para guardar o nome do Enum ("PACIENTE") em vez do número (0).
    @Column(nullable = false)
    private Perfil perfil;

    private boolean ativo = true;

    private Instant dataCriacao = Instant.now();

    private Instant ultimoLogin;

    // Enum para definir os perfis de usuário possíveis no sistema.
    public enum Perfil {
        PACIENTE,
        PROFISSIONAL_SAUDE,
        ADMIN
    }
}
