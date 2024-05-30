package com.ifgoiano.biblioteca.repository;

import com.ifgoiano.biblioteca.model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Criação da interface UsuarioRepository do repositório que estendem JpaRepository
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByTelefone(String telefone);

}
