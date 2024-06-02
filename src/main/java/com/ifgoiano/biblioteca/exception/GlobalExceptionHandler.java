package com.ifgoiano.biblioteca.exception; // Declaração do pacote onde a classe está registrada

// Importações necessárias para o tratamento de exceções e resposta HTTP no Spring
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ifgoiano.biblioteca.model.ResourceNotFoundException; // Importação da exceção personalizada ResourceNotFoundException

// Declaração da classe GlobalExceptionHandler que irá tratar exceções de forma global em controladores REST
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Método para tratar a exceção ResourceNotFoundException
    // Anotado com @ExceptionHandler para indicar que este método deve ser chamado quando essa exceção for lançada
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        // Retorna uma ResponseEntity com a mensagem de erro e o status HTTP 404 (Não Encontrado)
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Método para tratar exceções genéricas
    // Anotado com @ExceptionHandler para indicar que este método deve ser chamado para qualquer exceção não tratada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        // Retorna uma ResponseEntity com a mensagem de erro e o status HTTP 500 (Internal Server Error)
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
