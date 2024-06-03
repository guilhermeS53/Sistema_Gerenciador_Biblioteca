package com.ifgoiano.biblioteca.service; // Define o pacote onde a classe está localizada

import java.util.List; // Importa a classe List para trabalhar com coleções de dados

// Importações relacionadas ao Spring para injeção de dependência e gerenciamento de serviços
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Importações dos modelos de dados da biblioteca
import com.ifgoiano.biblioteca.model.Emprestimo;
import com.ifgoiano.biblioteca.model.Livro;
import com.ifgoiano.biblioteca.model.LivroComEmprestimosException;
import com.ifgoiano.biblioteca.model.ResourceNotFoundException;

// Importações dos repositórios para acesso aos dados
import com.ifgoiano.biblioteca.repository.LivroRepository;
import com.ifgoiano.biblioteca.repository.EmprestimoRepository;

// Criação do serviço que encapsula a lógica de negócios
@Service // Classe de Serviço do Spring
public class LivroService implements ILivroService {

    // Injeção de dependência dos repositórios (Spring se encarrega de criar as
    // instâncias)
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    // Métodos que implementam a interface ILivroService

    //Retorna uma lista com todos os livros cadastrados
    // @return Uma lista de objetos Livro

    @Override
    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    @Override
    public Livro findById(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado para o id: " + id));
    }

    /**
     * Busca um livro pelo seu ID
     // @param id O ID do livro a ser buscado
     // @return O objeto Livro encontrado
     // @throws ResourceNotFoundException Se o livro não for encontrado
     */

    public List<Livro> findByTituloContaining(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    /**
     * Busca livros cujo título contenha uma determinada String
     // @param titulo A string a ser buscada no título
     // @return Uma lista de objetos Livro
     */

    @Override
    public Livro save(Livro livro) {
        return livroRepository.save(livro);
    }

    /**
     * Exclui um livro pelo seu ID
     // @param id O ID do livro a ser excluído
     // @throws LivroComEmprestimosException Se o livro tiver empréstimos associados
     */

    @Override
    public void deleteById(Long id) {
        // Verifica se o livro possui empréstimos ativos
        List<Emprestimo> emprestimos = emprestimoRepository.findByLivroId(id);
        if (!emprestimos.isEmpty()) {
            System.out.println();
            throw new LivroComEmprestimosException("Não é possível excluir o livro selecionado, porque já foi emprestado em algum momento.");
        }
        // Se não houver empréstimos, exclui o livro
        livroRepository.deleteById(id);
    }

    /**
     * Atualiza os dados de um livro existente
     // @param id           O ID do livro a ser atualizado
     // @param livroDetails O objeto Livro com os novos dados
     // @return O objeto Livro atualizado
     */

    @Override
    public Livro updateLivro(Long id, Livro livroDetails) {
        Livro livro = findById(id); // Busca o livro pelo ID
        System.out.println();
        if (livro != null) { // Se o livro existir, atualiza seus dados
            livro.setTitulo(livroDetails.getTitulo());
            livro.setAutor(livroDetails.getAutor());
            livro.setAnoPub(livroDetails.getAnoPub());
            livro.setIsbn(livroDetails.getIsbn());
            livro.setEmprestado(livroDetails.isEmprestado());
            livro.setCategoria(livroDetails.getCategoria());
            return save(livro); // Salva o livro atualizado
        }
        return null; // Retorna nulo se o livro não for encontrado
    }
}