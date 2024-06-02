package com.ifgoiano.biblioteca.repository; // Definição do pacote onde a interface está registrada

import org.springframework.data.jpa.repository.JpaRepository; // Importação da classe JpaRepository para uso como repositório

import com.ifgoiano.biblioteca.model.Categoria; // Importação do modelo Categoria

// Criação da interface CategoriaRepository que estende JpaRepository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    /**
     * Método para encontrar uma categoria pelo nome
     * 
     * @param nome o nome da categoria a ser encontrada
     * @return a categoria correspondente ao nome fornecido
     */
    Categoria findByNome(String nome);
}
