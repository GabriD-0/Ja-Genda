package com.gabrid.ja_genda.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "agendamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome_agendamento;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private LocalDateTime hora;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String observacoes;

    /*
    Relacionamento com a entidade Cliente:
    - @ManyToOne: Indica que a entidade Agendamento tem um relacionamento muitos-para-um com a entidade Cliente
    - @JoinColumn: Indica que a coluna cliente_id na tabela agendamentos é a chave estrangeira que relaciona a entidade Agendamento com a entidade Cliente
     */
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
}