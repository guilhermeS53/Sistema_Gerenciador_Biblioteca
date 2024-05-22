package com.ifgoiano.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifgoiano.biblioteca.repository.EmprestimoRepository;
import com.ifgoiano.biblioteca.model.Emprestimo;

@Service
public class EmprestimoService implements IEmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Override
    public Emprestimo save(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    @Override
    public Emprestimo findById(Long id) {
        return emprestimoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }
}