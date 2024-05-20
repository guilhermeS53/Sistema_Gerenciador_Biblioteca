package com.ifgoiano.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifgoiano.biblioteca.model.Livro;
import com.ifgoiano.biblioteca.repository.LivroRepository;

// Criação do serviço que encapsula a lógica de negócios
@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    public Livro findById(Long id) {
        return livroRepository.findById(id).orElse(null);
    }

    public List<Livro> findByTituloContaining(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public Livro save(Livro livro) {
        return livroRepository.save(livro);
    }

    public void deleteById(Long id) {
        livroRepository.deleteById(id);
    }

    public Livro updateLivro(Long id, Livro livroDetails) {
        Livro livro = findById(id);
        if (livro != null) {
            livro.setTitulo(livroDetails.getTitulo());
            livro.setAutor(livroDetails.getAutor());
            livro.setAnoPub(livroDetails.getAnoPub());
            livro.setIsbn(livroDetails.getIsbn());
            return save(livro);
        }
        return null;
    }

    public Livro emprestarLivro(Long id) {
        Livro livro = findById(id);
        if (livro != null && !livro.isEmprestado()) {
            livro.setEmprestado(true);
            return save(livro);
        }
        return null;
    }

    public Livro devolverLivro(Long id) {
        Livro livro = findById(id);
        if (livro != null && livro.isEmprestado()) {
            livro.setEmprestado(false);
            return save(livro);
        }
        return null;
    }
}
