package br.edu.unifio.eventos.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private Integer capacidade;

    private String status;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Local local;

    @ManyToOne
    private Palestrante palestrante;

    @OneToMany(mappedBy = "evento")
    private List<Inscricao> inscricoes = new ArrayList<>();
}