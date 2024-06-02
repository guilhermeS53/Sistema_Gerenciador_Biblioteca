package com.ifgoiano.biblioteca.model; // Definição do pacote onde a classe está registrada

import jakarta.persistence.Entity; // Importação da biblioteca do Entity
import jakarta.persistence.GeneratedValue; // Importação da biblioteca 
import jakarta.persistence.GenerationType; // Importação do tipo de geração das chaves
import jakarta.persistence.Id; // Importação do campo de Id
import jakarta.persistence.JoinColumn; // Importação da anotação JoinColumn
import jakarta.persistence.ManyToOne; // Importação da anotação ManyToOne

// Criação da Entidade Livro na lógica de negócio em Model
@Entity // Anotação que indica que a classe Livro é uma entidade de Java Persistence API (JPA) e será mapeada para uma tabela no banco de dados
public class Livro {

    @Id // Campo Id é a chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a estratégia de geração de valor automático para a chave primária
    private Long id; // Campo que representa o identificador único de Livro
    private String titulo; // Campo que representa o título do Livro
    private String autor; // Campo que representa o autor do Livro
    private int anoPub; // Campo que representa o ano de publicação do Livro
    private String isbn; // Campo que representa o ISBN do Livro
    private boolean emprestado; // Indica status do Livro

    @ManyToOne // Define um relacionamento muitos-para-um com a entidade Categoria, indicando que um livro pertence a uma categoria
    @JoinColumn(name = "categoria_id") // Define a coluna na tabela de livros que armazena a chave estrangeira para a categoria
    
    private Categoria categoria; // Categoria associada ao livro

    // Getters e Setters para os atributos da classe

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPub() {
        return anoPub;
    }

    public void setAnoPub(int anoPub) {
        this.anoPub = anoPub;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}