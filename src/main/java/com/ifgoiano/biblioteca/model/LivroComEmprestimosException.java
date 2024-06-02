package com.ifgoiano.biblioteca.model; //Definição do pacote onde a classe está registrada

// Declaração da classe LivroComEmprestimosException que estende RuntimeException
public class LivroComEmprestimosException extends RuntimeException { // Essa classe representa uma exceção personalizada que será lançada quando um livro estiver com empréstimos pendentes
    
    // Construtor da classe que recebe uma mensagem de erro como parâmetro
    // A chamada ao construtor da superclasse (RuntimeException) é realizada para inicializar a mensagem de erro
    public LivroComEmprestimosException(String message) {
        super(message); // Informa a mensagem de erro para o construtor da superclasse
        
    }
}
