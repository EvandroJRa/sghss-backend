package com.sghss.controller;

import com.sghss.controller.dto.DadosAutenticacaoDTO;
import com.sghss.controller.dto.TokenJWTDTO;
import com.sghss.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sghss.security.token.TokenService;
import org.springframework.security.core.AuthenticationException;

@RestController
@RequestMapping("/api/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoDTO dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());

        try {
            var authentication = manager.authenticate(authenticationToken);
            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
            return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));

        } catch (AuthenticationException e) {
            // Bloco para capturar o erro exato de autenticação
            System.err.println("!!! ERRO DE AUTENTICAÇÃO DETALHADO !!!");
            e.printStackTrace(); // Imprime o stack trace completo do erro no console
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Falha na autenticação: " + e.getMessage());
        }
    }




}
