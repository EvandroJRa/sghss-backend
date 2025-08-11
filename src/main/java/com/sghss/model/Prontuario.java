package com.sghss.model;

import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prontuarios")
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProntuario;

    // --- Relacionamentos ---

    @OneToOne(fetch = FetchType.LAZY) // Define o relacionamento: Um Prontuário para Um Paciente.
    @JoinColumn(name = "id_paciente", nullable = false, unique = true) // Garante que um paciente só pode ter um prontuário.
    private Paciente paciente;

    @OneToMany(mappedBy = "prontuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // Define o relacionamento: Um Prontuário para Muitas Entradas.
    private List<EntradaProntuario> entradas = new ArrayList<>();

    // --- Fim dos Relacionamentos ---

    private Instant dataUltimaAtualizacao;

    @Lob
    private String alergiasConhecidas;

    @Lob
    private String condicoesMedicasPreexistentes;
}
