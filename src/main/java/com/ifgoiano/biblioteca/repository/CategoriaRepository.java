package com.ifgoiano.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifgoiano.biblioteca.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Categoria findByNome(String nome);
}
