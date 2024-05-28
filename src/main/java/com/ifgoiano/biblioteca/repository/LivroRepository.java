package com.ifgoiano.biblioteca.repository;

import com.ifgoiano.biblioteca.model.Livro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Criação da interface LivroRepository do repositório que estendem JpaRepository
@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
}
