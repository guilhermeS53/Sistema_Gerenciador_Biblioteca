package com.ifgoiano.biblioteca.service; // Definição do pacote onde a classe está registrada

import java.util.List; // Importação da classe List para utilização em Coleções 

// Importações relacionadas ao Spring para injeção de dependência e gerenciamento de serviços
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Importações dos modelos de dados da biblioteca
import com.ifgoiano.biblioteca.model.Categoria;
import com.ifgoiano.biblioteca.model.CategoriaComLivrosException;
import com.ifgoiano.biblioteca.model.ResourceNotFoundException;

// Importações dos repositórios para acesso aos dados
import com.ifgoiano.biblioteca.repository.CategoriaRepository;
import com.ifgoiano.biblioteca.repository.LivroRepository;

/**
 * Serviço responsável pela lógica de negócios relacionada a categoria.
 * Implementa a interface ICategoriaService.
 */
@Service // Anotação que a classe utilizada é um serviço gerenciado pelo Spring
public class CategoriaService implements ICategoriaService {

    @Autowired // Anotação que permite ao Spring injetar automaticamente a dependência do repositório
    private CategoriaRepository categoriaRepository;

    @Autowired // Anotação que permite ao Spring injetar dependência
    private LivroRepository livroRepository;

    @Override // Sobrescreve o método da interface ICategoriaService
    public List<Categoria> findAll() {
        return categoriaRepository.findAll(); // Retorna todas as categorias
    }

    @Override // Sobrescreve o método da interface ICategoriaService
    public Categoria findById(Long id) {
        // Procura uma categoria pelo ID. Se não encontrar, lança uma exceção personalizada
        System.out.println();
        return categoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada para o id: " + id));
    }

    @Override // Sobrescreve o método da interface ICategoriaService
    public Categoria save(Categoria categoria) {
        // Salva uma nova categoria ou atualiza uma existente
        return categoriaRepository.save(categoria);
    }

    @Override // Sobrescreve o método da interface ICategoriaService
    public void deleteById(Long id) {
        // Verifica se a categoria existe antes de deletar, caso contrário lança uma exceção
        if (!categoriaRepository.existsById(id)) {
            System.out.println();
            throw new ResourceNotFoundException("Categoria não encontrada para o id: " + id);
        } if (livroRepository.existsByCategoriaId(id)) {
            System.out.println();
            throw new CategoriaComLivrosException("Não é possível excluir a categoria selecionada, porque já foi vinculada em algum momento a um livro");
        }
        categoriaRepository.deleteById(id); // Faz a deleção da categoria pelo ID
    }

    public Categoria findByNome(String nome) { // Método específico para encontrar uma categoria pelo nome
        return categoriaRepository.findByNome(nome); // Retorna a categoria que corresponde ao nome
    }
}