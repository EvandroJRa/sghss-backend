package com.sghss.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profissionais_saude")
@PrimaryKeyJoinColumn(name = "id_usuario") // Chave para ligar com a tabela 'usuarios'

public class ProfissionalSaude extends Usuario { // Herda de Usuario

    @Column(nullable = false)
    private String numeroRegistroProfissional;

    @Column(nullable = false)
    private String tipoRegistroProfissional; // Ex: CRM, COREN

    @Column(nullable = false, length = 2) // Limita o tamanho para 2 caracteres (ex: SC, SP)
    private String ufRegistroProfissional;

    private String especialidade;

    // Construtor específico para facilitar a criação de um profissional de saúde
    public ProfissionalSaude(String nomeCompleto, String email, String cpf, String senha,
                             String numeroRegistro, String tipoRegistro, String ufRegistro, String especialidade) {
        // Chama o construtor da classe pai (Usuario)
        super(null, nomeCompleto, email, cpf, senha, Perfil.PROFISSIONAL_SAUDE, true, null, null);
        this.numeroRegistroProfissional = numeroRegistro;
        this.tipoRegistroProfissional = tipoRegistro;
        this.ufRegistroProfissional = ufRegistro;
        this.especialidade = especialidade;
    }
}
