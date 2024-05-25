package com.ifgoiano.biblioteca.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import com.ifgoiano.biblioteca.model.Categoria;
import com.ifgoiano.biblioteca.model.Livro;
import com.ifgoiano.biblioteca.service.CategoriaService;
import com.ifgoiano.biblioteca.service.ILivroService;
import com.ifgoiano.biblioteca.service.LivroService;

// Controlador para interação com os livros da biblioteca

@Controller
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private CategoriaService categoriaService;

    public void run(Scanner scanner) {
        while (true) {
            System.out.println("Gerenciar Livros:");
            System.out.println("1. Listar Livros");
            System.out.println("2. Adicionar Livro");
            System.out.println("3. Atualizar Livro");
            System.out.println("4. Deletar Livro");
            System.out.println("0. Voltar");
            int opcao = Integer.parseInt(scanner.nextLine());

            if (opcao == 0)
                break;

            switch (opcao) {
                case 1:
                    listarLivros();
                    break;
                case 2:
                    adicionarLivro(scanner);
                    break;
                case 3:
                    atualizarLivro(scanner);
                    break;
                case 4:
                    deletarLivro(scanner);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void listarLivros() {
        livroService.findAll().forEach(l -> {
            System.out.println("ID: " + l.getId());
            System.out.println("Título: " + l.getTitulo());
            System.out.println("Autor: " + l.getAutor());
            System.out.println("Ano de Publicação: " + l.getAnoPub());
            System.out.println("ISBN: " + l.getIsbn());
            System.out.println("Emprestado: " + l.isEmprestado());
            System.out
                    .println("Categoria: " + (l.getCategoria() != null ? l.getCategoria().getNome() : "Sem Categoria"));
            System.out.println();
        });
    }

    private void adicionarLivro(Scanner scanner) {
        System.out.println("Título do Livro:");
        String titulo = scanner.nextLine();
        System.out.println("Autor do Livro:");
        String autor = scanner.nextLine();
        System.out.println("Ano de Publicação:");
        int anoPub = Integer.parseInt(scanner.nextLine());
        System.out.println("ISBN do Livro:");
        String isbn = scanner.nextLine();
        System.out.println("Nome da Categoria:");
        String categoriaNome = scanner.nextLine();

        Categoria categoria = categoriaService.findByNome(categoriaNome);
        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAnoPub(anoPub);
        livro.setIsbn(isbn);
        livro.setCategoria(categoria);
        livroService.save(livro);
        System.out.println("Livro adicionado com sucesso!");
    }

    private void atualizarLivro(Scanner scanner) {
        System.out.println("ID do Livro a ser atualizado:");
        Long id = Long.parseLong(scanner.nextLine());
        Livro livro = livroService.findById(id);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        System.out.println("Novo título do Livro:");
        String titulo = scanner.nextLine();
        System.out.println("Novo autor do Livro:");
        String autor = scanner.nextLine();
        System.out.println("Novo ano de Publicação:");
        int anoPub = Integer.parseInt(scanner.nextLine());
        System.out.println("Novo ISBN do Livro:");
        String isbn = scanner.nextLine();
        System.out.println("Novo Nome da Categoria:");
        String categoriaNome = scanner.nextLine();

        Categoria categoria = categoriaService.findByNome(categoriaNome);
        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAnoPub(anoPub);
        livro.setIsbn(isbn);
        livro.setCategoria(categoria);
        livroService.updateLivro(id, livro);
        System.out.println("Livro atualizado com sucesso!");
    }

    private void deletarLivro(Scanner scanner) {
        System.out.println("ID do Livro a ser deletado:");
        Long id = Long.parseLong(scanner.nextLine());
        livroService.deleteById(id);
        System.out.println("Livro deletado com sucesso!");
    }
}