package com.sghss.controller;

import com.sghss.controller.dto.PacienteCadastroDTO;
import com.sghss.controller.dto.PacienteDTO;
import com.sghss.controller.dto.PacienteUpdateDTO;
import com.sghss.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDTO> criarPaciente(@RequestBody @Valid PacienteCadastroDTO dados) {
        PacienteDTO novoPaciente = pacienteService.criar(dados);
        // Retorna o status 201 Created com os dados do novo paciente no corpo da resposta
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPaciente);
    }
       @GetMapping("/{id}")
       @PreAuthorize("@customSecurityService.isOwnerOrAdmin(authentication, #id)")
        public ResponseEntity<PacienteDTO> buscarPacientePorId(@PathVariable Long id) {
           var pacienteDTO = pacienteService.buscarPorId(id);
           return ResponseEntity.ok(pacienteDTO);
        }
    @PutMapping("/{id}")
    @PreAuthorize("@customSecurityService.isOwnerOrAdmin(authentication, #id)")
    public ResponseEntity<PacienteDTO> atualizarPaciente(@PathVariable Long id, @RequestBody @Valid PacienteUpdateDTO dados) {
        var pacienteAtualizado = pacienteService.atualizar(id, dados);
        return ResponseEntity.ok(pacienteAtualizado);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')") // Apenas administradores podem deletar usuários.
    @ResponseStatus(HttpStatus.NO_CONTENT) // Retorna o status 204 No Content em caso de sucesso.
    public void deletarPaciente(@PathVariable Long id) {
        pacienteService.deletar(id);
    }

}