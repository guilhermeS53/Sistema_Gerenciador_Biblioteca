package com.ifgoiano.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifgoiano.biblioteca.model.Usuario;
import com.ifgoiano.biblioteca.repository.UsuarioRepository;

// Criação do serviço de Usuário que encapsula a lógica de negócios
@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario authenticate(String login, String senha) {
        return usuarioRepository.findByLoginAndSenha(login, senha).orElse(null);
    }
}
