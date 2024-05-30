package com.ifgoiano.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifgoiano.biblioteca.model.Emprestimo;
import com.ifgoiano.biblioteca.model.Livro;
import com.ifgoiano.biblioteca.model.LivroComEmprestimosException;
import com.ifgoiano.biblioteca.model.ResourceNotFoundException;
import com.ifgoiano.biblioteca.repository.LivroRepository;
import com.ifgoiano.biblioteca.repository.EmprestimoRepository;

// Criação do serviço que encapsula a lógica de negócios
@Service
public class LivroService implements ILivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Override
    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    @Override
    public Livro findById(Long id) {
        return livroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado para o id: " + id));
    }

    public List<Livro> findByTituloContaining(String titulo){
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    @Override
    public Livro save(Livro livro) {
        return livroRepository.save(livro);
    }

    @Override
    public void deleteById(Long id) {
        List<Emprestimo> emprestimos = emprestimoRepository.findByLivroId(id);
        if (!emprestimos.isEmpty()) {
            throw new LivroComEmprestimosException("Não é possível excluir o livro selecionado, porque já foi emprestado em algum momento.");
        }
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