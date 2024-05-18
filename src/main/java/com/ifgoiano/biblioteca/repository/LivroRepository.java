package com.ifgoiano.biblioteca.repository;

import com.ifgoiano.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

// Criação da interface LivroRepository do repositório que estendem JpaRepository
public interface LivroRepository extends JpaRepository<Livro, Long> {

}
