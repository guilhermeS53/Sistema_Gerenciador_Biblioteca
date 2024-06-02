package com.ifgoiano.biblioteca.service; // Definição do pacote onde a classe está registrada

import java.util.List; // Importação da classe List para utilização em Coleções

import com.ifgoiano.biblioteca.model.Livro; // Importação do modelo Livro
import com.ifgoiano.biblioteca.model.LivroComEmprestimosException;
import com.ifgoiano.biblioteca.model.ResourceNotFoundException;

/**
 * Interface que define os métodos para um serviço de gerenciamento de livros
 * As classes que implementarem esta interface deverão fornecer a lógica para cada método
 */

public interface ILivroService {
    
    /**
     * Retorna uma lista com todos os livros cadastrados
     * @return Uma lista de objetos Livro
     */
    List<Livro> findAll(); // Retorna todos os livros cadastrados no sistema

    /**
     * Busca livros cujo título contenha uma determinada string
     * @param titulo A string a ser buscada no título
     * @return Uma lista de objetos Livro que correspondem à busca
     */
    List<Livro> findByTituloContaining(String titulo);

    /**
     * Busca um livro pelo seu ID.
     * @param id O ID do livro a ser buscado.
     * @return O objeto Livro encontrado.
     * @throws ResourceNotFoundException Se o livro não for encontrado (tratamento definido na implementação)
     */
    Livro findById(Long id);

    /**
     * Salva um novo livro ou atualiza um existente
     * @param livro O objeto Livro a ser salvo
     * @return O objeto Livro salvo ou atualizado
     */
    Livro save(Livro livro);

    /**
     * Exclui um livro pelo seu ID
     * @param id O ID do livro a ser excluído
     * @throws LivroComEmprestimosException Se o livro tiver empréstimos associados (tratamento definido na implementação)
     */
    void deleteById(Long id);

     /**
     * Atualiza os dados de um livro existente
     * @param id O ID do livro a ser atualizado
     * @param livroDetails O objeto Livro com os novos dados
     * @return O objeto Livro atualizado
     */
    Livro updateLivro(Long id, Livro livroDetails);
}
