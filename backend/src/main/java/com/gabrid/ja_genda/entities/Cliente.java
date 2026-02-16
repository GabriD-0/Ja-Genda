package com.gabrid.ja_genda.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;
import jakarta.persistence.Column;

/*
Resumo — Construtores e JPA
 • O que é: Construtor
   "Método" com o nome da classe; roda no "new" e inicializa o objeto.

 • O que é: @NoArgsConstructor
   Gera:  Cliente() { }
   Papel: Obrigatório. O Hibernate usa esse construtor para instanciar a entidade ao carregar as linhas do banco.

 • O que é: @AllArgsConstructor
   Gera:  Cliente(id, nome, telefone, dataAtualizacao, dataExclusao)
   Papel: Opcional. Facilita criar objetos manualmente (ex.: em testes).
*/

@Entity // Indica que a classe é uma entidade JPA
@Table(name = "clientes") // Nome da tabela no banco de dados
@Getter // Gera os getters para os atributos da classe
@Setter // Gera os setters para os atributos da classe
@NoArgsConstructor // Construtor sem argumentos
@AllArgsConstructor // Construtor com todos os argumentos
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o ID automaticamente
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 15, unique = true)
    private String telefone;

    @Column(nullable = true)
    private LocalDateTime dataAtualizacao;

    @Column(nullable = true)
    private LocalDateTime dataExclusao;
}