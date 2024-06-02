package com.ifgoiano.biblioteca.model; // Definição do pacote onde a classe está registrada

import jakarta.persistence.Entity; // Importação da biblioteca do Entity
import jakarta.persistence.GeneratedValue; // Importação da biblioteca para gerenciamento de estratégia de geração automática do valor de PK
import jakarta.persistence.GenerationType; // Importação do tipo de geração das chaves
import jakarta.persistence.Id; // Importação do campo de Id
import jakarta.persistence.JoinColumn; // Importação da anotação JoinColumn
import jakarta.persistence.ManyToOne; // Importação da anotação ManyToOne

import java.time.LocalDate;

// Criação da Entidade Empréstimo na lógica de negócio em Model
@Entity // Anotação que indica que a classe Emprestimo é uma entidade de Java Persistence API (JPA) e será mapeada para uma tabela no banco de dados
public class Emprestimo {

    @Id // Indica que o campo id é a chave primária da entidade.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a estratégia de geração de valor automático para a chave primária.
    private Long id; // Campo que representa o identificador único da categoria
    private LocalDate dataEmprestimo; // Campo que representa a data de empréstimo do livro.
    private LocalDate dataDevolucao; // Campo que representa a data de devolução do livro.
    private StatusEmprestimo status; // Campo que representa o status do empréstimo
    private Double multa; // Campo que representa a multa aplicada caso o livro seja devolvido após a data prevista.

    @ManyToOne // Define um relacionamento muitos-para-um com a entidade Usuario, indicando que um empréstimo pertence a um usuário.

    @JoinColumn(name = "usuario_id") // Define a coluna na tabela de empréstimos que armazena a chave estrangeira para o usuário.
    
    private Usuario usuario; // Usuario associado ao empréstimo.

    @ManyToOne // Define um relacionamento muitos para um com a entidade Livro, indicando que um empréstimo é de um livro.

    @JoinColumn(name = "livro_id") // Define a coluna na tabela de empréstimos que armazena a chave estrangeira para o livro.

    private Livro livro; // Livro associado ao empréstimo.

    // Getters e Setters para os atributos de classe

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public StatusEmprestimo getStatus() {
        return status;
    }

    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }

    public Double getMulta() {
        return multa;
    }

    public void setMulta(Double multa) {
        this.multa = multa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}