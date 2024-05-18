package com.ifgoiano.biblioteca.repository;

import com.ifgoiano.biblioteca.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

// Criação da interface EmprestimoRepository do repositório que estendem JpaRepository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

}
