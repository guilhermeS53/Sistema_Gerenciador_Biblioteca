package com.ifgoiano.biblioteca.repository;

import com.ifgoiano.biblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

// Criação da interface UsuarioRepository do repositório que estendem JpaRepository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
