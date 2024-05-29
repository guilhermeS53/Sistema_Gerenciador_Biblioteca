package com.ifgoiano.biblioteca.service;

import java.util.List;

import com.ifgoiano.biblioteca.model.Categoria;

public interface ICategoriaService {
    List<Categoria> findAll();
    Categoria findById(Long id);
    Categoria save(Categoria categoria);
    void deleteById(Long id);
    Categoria findByNome(String nome);
}
