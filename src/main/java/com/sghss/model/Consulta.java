package com.sghss.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsulta;

    // --- Início dos Relacionamentos ---

    @ManyToOne(fetch = FetchType.LAZY) // Define o relacionamento: Muitas Consultas para Um Paciente.
    @JoinColumn(name = "id_paciente", nullable = false) // Define a coluna da Chave Estrangeira (FK).
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY) // Define o relacionamento: Muitas Consultas para Um Profissional.
    @JoinColumn(name = "id_profissional", nullable = false) // Define a coluna da Chave Estrangeira (FK).
    private ProfissionalSaude profissional;

    // --- Fim dos Relacionamentos ---

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConsulta statusConsulta;

    @Lob // Indica que o campo pode armazenar um texto longo.
    private String observacoes;

    // Enum para definir os status possíveis de uma consulta.
    public enum StatusConsulta {
        AGENDADA,
        REALIZADA,
        CANCELADA
    }

    // Construtor para facilitar a criação de uma nova consulta
    public Consulta(Paciente paciente, ProfissionalSaude profissional, LocalDateTime dataHora) {
        this.paciente = paciente;
        this.profissional = profissional;
        this.dataHora = dataHora;
        this.statusConsulta = StatusConsulta.AGENDADA; // Uma nova consulta sempre começa como AGENDADA.
    }
}
