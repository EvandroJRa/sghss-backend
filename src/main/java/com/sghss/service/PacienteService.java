package com.sghss.service;

import com.sghss.controller.dto.PacienteCadastroDTO;
import com.sghss.controller.dto.PacienteDTO;
import com.sghss.model.Paciente;
import com.sghss.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sghss.exception.ResourceNotFoundException;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PacienteDTO criar(PacienteCadastroDTO dados) {
        if (pacienteRepository.existsByCpf(dados.cpf())) {
            throw new RuntimeException("CPF já cadastrado no sistema.");
        }
        // Criptografa a senha antes de passar para a entidade
        var senhaCriptografada = passwordEncoder.encode(dados.senha());

        var paciente = new Paciente(
                dados.nomeCompleto(),
                dados.email(),
                dados.cpf(),
                senhaCriptografada,
                dados.dataNascimento(),
                dados.endereco()
        );
        var pacienteSalvo = pacienteRepository.save(paciente);
        return new PacienteDTO(pacienteSalvo);
    }
    //Logica de Busca
    public PacienteDTO buscarPorId(Long id) {
        // Agora, ao invés de um RuntimeException genérico...
        var paciente = pacienteRepository.findById(id)
                //  lançamos a exceção específica.
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com o ID: " + id));

        return new PacienteDTO(paciente);
    }
}