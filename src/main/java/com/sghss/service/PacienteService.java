package com.sghss.service;

import com.sghss.controller.dto.PacienteCadastroDTO;
import com.sghss.controller.dto.PacienteDTO;
import com.sghss.model.Paciente;
import com.sghss.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sghss.exception.ResourceNotFoundException;
import com.sghss.controller.dto.PacienteUpdateDTO;
import jakarta.transaction.Transactional;

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
    @Transactional // Anotação que garante que a operação ocorra dentro de uma transação com o banco.
    public PacienteDTO atualizar(Long id, PacienteUpdateDTO dados) {
        var paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new com.sghss.exception.ResourceNotFoundException("Paciente não encontrado com o ID: " + id));

        paciente.atualizarInformacoes(dados);
        // Com o @Transactional, não precisamos chamar o repository.save().
        // O Hibernate detecta a alteração e atualiza o banco de dados automaticamente.
        return new PacienteDTO(paciente);
    }
    public void deletar(Long id) {
        // Primeiro, verificamos se o paciente existe para evitar um erro.
        // Se não existir, a exceção que já criamos será lançada (404 Not Found).
        if (!pacienteRepository.existsById(id)) {
            throw new com.sghss.exception.ResourceNotFoundException("Paciente não encontrado com o ID: " + id);
        }
        // Se o paciente existe, mandamos o repositório deletá-lo.
        pacienteRepository.deleteById(id);
    }
}