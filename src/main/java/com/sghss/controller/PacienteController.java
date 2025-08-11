package com.sghss.controller;

import com.sghss.controller.dto.PacienteCadastroDTO;
import com.sghss.controller.dto.PacienteDTO;
import com.sghss.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDTO> criarPaciente(@RequestBody @Valid PacienteCadastroDTO dados) {
        PacienteDTO novoPaciente = pacienteService.criar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPaciente);
    }

    @GetMapping("/{id}") // Mapeia para requisições GET para /api/pacientes/{algum_numero}
    public ResponseEntity<PacienteDTO> buscarPacientePorId(@PathVariable Long id) {
        var pacienteDTO = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(pacienteDTO); // Retorna 200 OK com os dados do paciente.
    }
}