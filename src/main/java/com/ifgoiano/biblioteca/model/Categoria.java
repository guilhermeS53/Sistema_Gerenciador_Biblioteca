package com.ifgoiano.biblioteca.model; // Definição do pacote onde a classe está registrada

import java.util.List; // Importação da classe List para utilização em Coleções

import jakarta.persistence.Entity; // Importação da biblioteca do Entity
import jakarta.persistence.GeneratedValue; // Importação da biblioteca para gerenciamento de estratégia de geração automática do valor de PK
import jakarta.persistence.GenerationType; // Importação do tipo de geração das chaves
import jakarta.persistence.Id; // Importação do campo de Id
import jakarta.persistence.OneToMany; // Importação da biblioteca de relacionamento um para muitos.

@Entity // Anotação que indica que a classe Categoria é uma entidade de Java Persistence API (JPA) e será mapeada para uma tabela no banco de dados
public class Categoria {  

    @Id // Campo Id é a chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a estratégia de geração de valor automático para a chave primária
    private Long id; // Campo que representa o identificador único da categoria
    private String nome; // Campo que representa o nome da categoria

    @OneToMany(mappedBy = "categoria") // Define um relacionamento de um para muitos com a entidade Livro, indicando que uma categoria pode ter vários livros
    private List<Livro> livros; // Lista de livros pertencentes a esta categoria

    // Getters e Setters para os atributos da classe
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

}
