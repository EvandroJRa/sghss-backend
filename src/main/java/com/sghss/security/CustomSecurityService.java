package com.sghss.security;

import com.sghss.model.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("customSecurityService") // Damos um nome ao serviço
public class CustomSecurityService {
    public boolean isOwnerOrAdmin(Authentication authentication, Long id) {
        // Se a autenticação for nula ou não estiver autenticada, nega o acesso.
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        // Pega o objeto Usuario que foi carregado no  SecurityFilter
        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();
        // REGRA 1: Se o perfil do usuário for ADMIN, permite o acesso.
        if (usuarioAutenticado.getPerfil() == Usuario.Perfil.ADMIN) {
            return true;
        }
        // REGRA 2: Se o ID do usuário autenticado for o mesmo ID que está sendo solicitado na URL, permite o acesso.
        if (usuarioAutenticado.getIdUsuario().equals(id)) {
            return true;
        }
        // Se nenhuma das regras acima for atendida, nega o acesso.
        return false;
    }
}