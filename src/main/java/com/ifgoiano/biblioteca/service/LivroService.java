package com.ifgoiano.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifgoiano.biblioteca.model.Livro;
import com.ifgoiano.biblioteca.model.ResourceNotFoundException;
import com.ifgoiano.biblioteca.repository.LivroRepository;

// Criação do serviço que encapsula a lógica de negócios
@Service
public class LivroService implements ILivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    @Override
    public Livro findById(Long id) {
        return livroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado para o id: " + id));
    }

    @Override
    public Livro save(Livro livro) {
        return livroRepository.save(livro);
    }

    @Override
    public void deleteById(Long id) {
        livroRepository.deleteById(id);
    }

    @Override
    public Livro updateLivro(Long id, Livro livroDetails) {
        Livro livro = findById(id);
        if (livro != null) {
            livro.setTitulo(livroDetails.getTitulo());
            livro.setAutor(livroDetails.getAutor());
            livro.setAnoPub(livroDetails.getAnoPub());
            livro.setIsbn(livroDetails.getIsbn());
            livro.setEmprestado(livroDetails.isEmprestado());
            livro.setCategoria(livroDetails.getCategoria());
            return save(livro);
        }
        return null;
    }
}