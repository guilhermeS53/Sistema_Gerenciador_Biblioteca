package com.ifgoiano.biblioteca.service; // Definição do pacote onde a classe está registrada

import java.util.List; // Importação da classe List para utilização em Coleções 

import com.ifgoiano.biblioteca.model.Emprestimo; //Importação do modelo de Emprestimo
import com.ifgoiano.biblioteca.model.ResourceNotFoundException; // Importação da classe de Exceção

public interface IEmprestimoService {

    /**
     * Salva um novo empréstimo ou atualiza um existente
     * @param emprestimo O objeto Emprestimo a ser salvo
     * @return O objeto Emprestimo salvo ou atualizado
     */
    Emprestimo save(Emprestimo emprestimo);

    /**
     * Busca um empréstimo pelo seu ID
     // @param id O ID do empréstimo a ser buscado
     // @return O objeto Emprestimo encontrado
     * @throws ResourceNotFoundException Se o empréstimo não for encontrado (tratamento definido na implementação)
     */
    Emprestimo findById(Long id);

    /**
     * Retorna uma lista com todos os empréstimos cadastrados
     * @return Uma lista de objetos Emprestimo
     */
    List<Emprestimo> findAll();
}
