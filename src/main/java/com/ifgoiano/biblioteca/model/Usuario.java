package com.ifgoiano.biblioteca.model; // Definição do pacote onde a classe está registrada

import jakarta.persistence.Entity; // Importação da biblioteca do Entity
import jakarta.persistence.GeneratedValue; // Importação da biblioteca
import jakarta.persistence.GenerationType; // Importação do tipo de geração das chaves
import jakarta.persistence.Id; // Importação do campo de Id

// Criação da Entidade Usuário na lógica de negócio em Model
// Adicionado @Entity para que o Hibernate possa encontrar a entidade

@Entity// Anotação que indica que a classe Livro é uma entidade de Java Persistence API (JPA) e será mapeada para uma tabela no banco de dados
public class Usuario {
    
    @Id // Campo Id é a chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a estratégia de geração de valor automático para a chave primária
    private Long id; // Campo que representa o identificador único de Usuario
    private String nome; // Campo que representa o nome do Usuario
    private String email; // Campo que representa o e-mail do Usuario
    private String telefone; // Campo que representa o telefone do Usuario

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
