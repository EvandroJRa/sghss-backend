package com.sghss.repository;

import com.sghss.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Método para buscar um usuário pelo seu endereço de email.
     * O Spring Data JPA cria a implementação automaticamente.
     */
    UserDetails findByEmail(String email);

}
