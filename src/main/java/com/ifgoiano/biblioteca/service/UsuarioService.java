package com.ifgoiano.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifgoiano.biblioteca.model.Emprestimo;
import com.ifgoiano.biblioteca.model.ResourceNotFoundException;
import com.ifgoiano.biblioteca.model.Usuario;
import com.ifgoiano.biblioteca.model.UsuarioComEmprestimosException;
import com.ifgoiano.biblioteca.repository.EmprestimoRepository;
import com.ifgoiano.biblioteca.repository.UsuarioRepository;

// Criação do serviço de Usuário que encapsula a lógica de negócios
@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

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
        return usuarioRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("Usuário não encontrado para o id: " + id));
    }

    @Override
    public void deleteById(Long id) {
    List<Emprestimo> emprestimos = emprestimoRepository.findByUsuarioId(id);
    if (!emprestimos.isEmpty()) {
        throw new UsuarioComEmprestimosException("Não é possível excluir o usuário, pois já houveram registros de empréstimo na Biblioteca.");
    }
    usuarioRepository.deleteById(id);
}

    @Override
    public Usuario authenticate(String login, String senha) {
        return usuarioRepository.findByLoginAndSenha(login, senha).orElseThrow(() -> 
            new ResourceNotFoundException("Usuário não encontrado para o Login: " + login));
    }
}
