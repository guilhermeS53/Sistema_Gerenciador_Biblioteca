package com.ifgoiano.biblioteca.service;

import java.util.List;

import com.ifgoiano.biblioteca.model.Usuario;

public interface IUsuarioService {
    Usuario save(Usuario usuario);
    List<Usuario> findAll();
    Usuario findById(Long id);
    void deleteById(Long id);
    Usuario authenticate(String telefone);
}
