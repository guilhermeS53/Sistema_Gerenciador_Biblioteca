package com.ifgoiano.biblioteca.repository;

import com.ifgoiano.biblioteca.model.Emprestimo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// Criação da interface EmprestimoRepository do repositório que estendem JpaRepository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByUsuarioId(Long usuarioId);
    List<Emprestimo> findByLivroId(Long livroId);
}
