package com.sghss.service;

import com.sghss.controller.dto.AdminCadastroDTO;
import com.sghss.model.Administrador;
import com.sghss.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void criarAdmin(AdminCadastroDTO dados) {
        var senhaCriptografada = passwordEncoder.encode(dados.senha());
        var admin = new Administrador(
                dados.nomeCompleto(),
                dados.email(),
                dados.cpf(),
                senhaCriptografada,
                dados.cargo()
        );
        administradorRepository.save(admin);
    }
}
