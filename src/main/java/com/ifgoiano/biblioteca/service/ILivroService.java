package com.ifgoiano.biblioteca.service;

import java.util.List;

import com.ifgoiano.biblioteca.model.Livro;

public interface ILivroService {
    List<Livro> findAll();
    List<Livro> findByTituloContaining(String titulo);
    Livro findById(Long id);
    Livro save(Livro livro);
    void deleteById(Long id);
    Livro updateLivro(Long id, Livro livroDetails);
}
