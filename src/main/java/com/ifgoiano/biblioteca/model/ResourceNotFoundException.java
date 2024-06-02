package com.ifgoiano.biblioteca.model; //Definição do pacote onde a classe está registrada

// Declaração da classe ResourceNotFoundException que estende RuntimeException

public class ResourceNotFoundException extends RuntimeException { // Essa classe representa uma exceção personalizada que será lançada quando um recurso não for encontrado

    // Construtor da classe que recebe uma mensagem de erro como parâmetro
    // A chamada ao construtor da superclasse (RuntimeException) é realizada para inicializar a mensagem de erro
    public ResourceNotFoundException(String message) {
        super(message); // Informa a mensagem de erro para o construtor da superclasse

    }
}
