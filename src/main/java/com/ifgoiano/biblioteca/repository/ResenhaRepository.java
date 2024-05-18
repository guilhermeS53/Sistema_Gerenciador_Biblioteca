package com.ifgoiano.biblioteca.repository;

import com.ifgoiano.biblioteca.model.Resenha;
import org.springframework.data.jpa.repository.JpaRepository;

// Criação da interface ResenhaRepository do repositório que estendem JpaRepository
public interface ResenhaRepository extends JpaRepository <Resenha, Long>{

}
