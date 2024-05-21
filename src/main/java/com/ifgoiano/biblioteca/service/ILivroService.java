package com.ifgoiano.biblioteca.service;

import java.util.List;

import com.ifgoiano.biblioteca.model.Livro;

public interface ILivroService {
    List<Livro> findAll();
    Livro findById(Long id);
    List<Livro> findByTituloContaining(String titulo);
    Livro save(Livro livro);
    void deleteById(Long id);
    Livro updateLivro(Long id, Livro livroDetails);
    Livro emprestarLivro(Long id);
    Livro devolverLivro(Long id);
}
