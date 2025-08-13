package com.sghss.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@EqualsAndHashCode(of = "idUsuario")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
// A interface UserDetails é implementada aqui, na declaração principal da classe.
public class Usuario implements UserDetails {

    // --- ATRIBUTOS DA ENTIDADE ---
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Perfil perfil;

    private boolean ativo = true;
    private Instant dataCriacao = Instant.now();
    private Instant ultimoLogin;

    // --- ENUM DE PERFIL ---
    public enum Perfil {
        PACIENTE,
        PROFISSIONAL_SAUDE,
        ADMIN
    }

    // --- MÉTODOS DA INTERFACE USERDETAILS ---
    // Todos os métodos @Override ficam aqui, dentro da classe Usuario principal.

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + perfil.name()));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.ativo;
    }
}