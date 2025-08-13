package com.sghss.repository;

import com.sghss.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    /**
     * O Spring Data JPA cria a consulta automaticamente a partir do nome do método.
     * "exists" - Verifica a existência.
     * "By" - Indica um filtro (WHERE).
     * "Cpf" - O nome do atributo na entidade Paciente.
     * Ele vai gerar o SQL: SELECT count(*) FROM pacientes WHERE cpf = ?
     */
    boolean existsByCpf(String cpf);
}
