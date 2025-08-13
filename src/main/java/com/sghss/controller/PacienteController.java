package com.sghss.controller;

import com.sghss.controller.dto.PacienteCadastroDTO;
import com.sghss.controller.dto.PacienteDTO;
import com.sghss.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//import com.sghss.repository.PacienteRepository; // <-- para debug
//import com.sghss.repository.UsuarioRepository;  // <-- para debug
//import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    //@Autowired
    //private PacienteRepository pacienteRepository;

    //@Autowired
   // private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<PacienteDTO> criarPaciente(@RequestBody @Valid PacienteCadastroDTO dados) {
        PacienteDTO novoPaciente = pacienteService.criar(dados);
        // Retorna o status 201 Created com os dados do novo paciente no corpo da resposta
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPaciente);
    }
       @PreAuthorize("@customSecurityService.isOwnerOrAdmin(authentication, #id)")
        public ResponseEntity<PacienteDTO> buscarPacientePorId(@PathVariable Long id) {
            var pacienteDTO = pacienteService.buscarPorId(id);
            return ResponseEntity.ok(pacienteDTO);
        }

//    // Método de debug completo no final da classe
//    @GetMapping("/debug/{id}")
//    public ResponseEntity<String> debugPaciente(@PathVariable Long id) {
//        StringBuilder debugResult = new StringBuilder();
//        debugResult.append("--- INICIANDO DEBUG PARA ID: ").append(id).append(" ---\n\n");
//
//        // Teste 1: Buscar com o Repositório de Usuario (deve funcionar)
//        debugResult.append("1. Buscando com UsuarioRepository.findById... \n");
//        java.util.Optional<com.sghss.model.Usuario> usuarioOptional = usuarioRepository.findById(id);
//        if (usuarioOptional.isPresent()) {
//            com.sghss.model.Usuario usuario = usuarioOptional.get();
//            debugResult.append("   - ENCONTRADO! ID: ").append(usuario.getIdUsuario()).append("\n");
//            debugResult.append("   - Classe Real do Objeto: ").append(usuario.getClass().getName()).append("\n");
//            debugResult.append("   - Perfil: ").append(usuario.getPerfil()).append("\n");
//        } else {
//            debugResult.append("   - ERRO: NÃO ENCONTRADO no UsuarioRepository.\n");
//        }
//        debugResult.append("\n");
//
//        // Teste 2: Buscar com o Repositório de Paciente (o que está falhando)
//        debugResult.append("2. Buscando com PacienteRepository.findById... \n");
//        java.util.Optional<com.sghss.model.Paciente> pacienteOptional = pacienteRepository.findById(id);
//        if (pacienteOptional.isPresent()) {
//            com.sghss.model.Paciente paciente = pacienteOptional.get();
//            debugResult.append("   - ENCONTRADO! ID: ").append(paciente.getIdUsuario()).append("\n");
//        } else {
//            debugResult.append("   - FALHA: NÃO ENCONTRADO no PacienteRepository.\n");
//        }
//        debugResult.append("\n");
//
//        // Teste 3: Listar TODOS os pacientes
//        debugResult.append("3. Buscando TODOS com PacienteRepository.findAll... \n");
//        java.util.List<com.sghss.model.Paciente> todosPacientes = pacienteRepository.findAll();
//        debugResult.append("   - Total de pacientes encontrados: ").append(todosPacientes.size()).append("\n");
//        if (!todosPacientes.isEmpty()) {
//            debugResult.append("   - IDs encontrados: ");
//            todosPacientes.forEach(p -> debugResult.append(p.getIdUsuario()).append(" "));
//            debugResult.append("\n");
//        }
//
//        // Para exibir no navegador, trocamos \n por <br>
//        return ResponseEntity.ok(debugResult.toString().replace("\n", "<br>"));
//    }
}