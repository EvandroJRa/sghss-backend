package com.sghss.controller;

import com.sghss.controller.dto.AdminCadastroDTO;
import com.sghss.service.AdministradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ROLE_ADMIN')") // SÓ ADMIN PODE ACESSAR!
    public ResponseEntity<String> acessarDashboardAdmin() {
        return ResponseEntity.ok("Dados do dashboard do administrador carregados com sucesso.");
    }

    // ---  ENDPOINT TEMPORÁRIO (ADM)
    @PostMapping("/criar-admin")
    public ResponseEntity criarAdmin(@RequestBody @Valid AdminCadastroDTO dados) {
        administradorService.criarAdmin(dados);
        return ResponseEntity.ok("Administrador criado com sucesso!");
    }
}
