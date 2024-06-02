package com.ifgoiano.biblioteca.repository; // Definição do pacote onde a interface está registrada

import com.ifgoiano.biblioteca.model.Emprestimo; // Importação do modelo Emprestimo

import java.util.List; // Importação do List para uso em Coleções

import org.springframework.data.jpa.repository.JpaRepository; // Importação da classe JpaRepository para uso como repositório

// Criação da interface EmprestimoRepository do repositório que estendem JpaRepository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    /**
     * Método para encontrar empréstimos pelo ID do usuário.
     * 
     * @param usuarioId o ID do usuário cujos empréstimos serão encontrados.
     * @return uma lista de empréstimos do usuário correspondente ao ID fornecido.
     */
    List<Emprestimo> findByUsuarioId(Long usuarioId);

    /**
     * Método para encontrar empréstimos pelo ID do livro.
     * 
     * @param livroId o ID do livro cujos empréstimos serão encontrados.
     * @return uma lista de empréstimos do livro correspondente ao ID fornecido.
     */
    List<Emprestimo> findByLivroId(Long livroId);
}
