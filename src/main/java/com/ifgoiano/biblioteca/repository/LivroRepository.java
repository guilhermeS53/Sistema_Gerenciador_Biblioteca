package com.ifgoiano.biblioteca.repository; // Definição do pacote onde a interface está registrada

import com.ifgoiano.biblioteca.model.Livro; // Importação do modelo Livro

import org.springframework.data.jpa.repository.JpaRepository; // Importação da classe JpaRepository para uso como repositório
import org.springframework.stereotype.Repository; // Importação da anotação Repository para marcar a interface como um repositório Spring

import java.util.List; // Importação do List para uso em Coleções

// Criação da interface LivroRepository do repositório que estendem JpaRepository
@Repository // Anotação que indica que esta interface é um repositório Spring
public interface LivroRepository extends JpaRepository<Livro, Long> {
    
    /**
     * Método para encontrar livros cujo título contém a sequência de caracteres especificada, ignorando maiúsculas e minúsculas.
     * 
     * @param titulo a sequência de caracteres a ser procurada nos títulos dos livros.
     * @return uma lista de livros cujos títulos contêm a sequência fornecida.
     */
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
}
