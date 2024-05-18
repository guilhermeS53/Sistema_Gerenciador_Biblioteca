package com.ifgoiano.biblioteca.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.ifgoiano.biblioteca.model.Usuario;
import com.ifgoiano.biblioteca.repository.UsuarioRepository;

// Criação do serviço de Usuário que encapsula a lógica de negócios
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
