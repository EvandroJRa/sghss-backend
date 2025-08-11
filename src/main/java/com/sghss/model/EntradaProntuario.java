package com.sghss.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entradas_prontuario")
public class EntradaProntuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrada;

    // --- Relacionamentos ---

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prontuario", nullable = false)
    private Prontuario prontuario; // O campo que 'mappedBy' na classe Prontuario está procurando.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profissional_responsavel", nullable = false)
    private ProfissionalSaude profissionalResponsavel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consulta_associada") // Pode ser nulo, pois uma entrada pode não vir de uma consulta.
    private Consulta consultaAssociada;

    // --- Fim dos Relacionamentos ---

    @Column(nullable = false)
    private Instant dataHoraEntrada = Instant.now();

    private String tipoEntrada;

    @Lob
    @Column(nullable = false)
    private String descricao;
}
