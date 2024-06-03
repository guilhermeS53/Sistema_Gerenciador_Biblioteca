package com.ifgoiano.biblioteca.model; //Definição do pacote onde a classe está registrada

// Declaração da classe CategoriaComLivrosException que estende RuntimeException
public class CategoriaComLivrosException extends RuntimeException {

    // Construtor da classe que recebe uma mensagem de erro como parâmetro
    // A chamada ao construtor da superclasse (RuntimeException) é realizada para inicializar a mensagem de erro
    public CategoriaComLivrosException(String message) {
        super(message); // Informa a mensagem de erro para o construtor da superclasse

    }
}
