package com.ifgoiano.biblioteca.service;

import java.util.List;
import com.ifgoiano.biblioteca.model.Emprestimo;

public interface IEmprestimoService {
    Emprestimo save(Emprestimo emprestimo);
    Emprestimo findById(Long id);
    List<Emprestimo> findAll();
}
