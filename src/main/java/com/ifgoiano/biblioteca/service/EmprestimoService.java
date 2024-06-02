package com.ifgoiano.biblioteca.service; // Definição do pacote onde a classe está registrada

import java.util.List; // Importação da classe List para utilização em Coleções 

// Importações relacionadas ao Spring para injeção de dependência e gerenciamento de serviços
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Importações dos repositórios para acesso aos dados
import com.ifgoiano.biblioteca.repository.EmprestimoRepository;

// Importações dos modelos de dados da biblioteca
import com.ifgoiano.biblioteca.model.Emprestimo;
import com.ifgoiano.biblioteca.model.ResourceNotFoundException;

/**
 * Serviço responsável pela lógica de negócios relacionada a empréstimos.
 * Implementa a interface IEmprestimoService.
 */
@Service // Classe de Serviço do Spring
public class EmprestimoService implements IEmprestimoService {

    // Injeção de dependência do repositório (Spring se encarrega de criar a instância)
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    // Métodos que implementam a interface IEmprestimoService

    /**
     * Salva um novo empréstimo ou atualiza um existente
     * @param emprestimo O objeto Emprestimo a ser salvo
     * @return O objeto Emprestimo salvo ou atualizado
     */
    @Override
    public Emprestimo save(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    /**
     * Busca um empréstimo pelo seu ID
     * @param id O ID do empréstimo a ser buscado
     * @return O objeto Emprestimo encontrado
     * @throws ResourceNotFoundException Se o empréstimo não for encontrado.
     */
    @Override
    public Emprestimo findById(Long id) {
        return emprestimoRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("Empréstimo não encontrado para o id: " + id));
    }

    /**
     * Retorna uma lista com todos os empréstimos cadastrados
     * @return Uma lista de objetos Emprestimo
     */
    @Override
    public List<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }
}