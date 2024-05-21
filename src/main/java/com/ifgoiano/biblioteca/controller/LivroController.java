package com.ifgoiano.biblioteca.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import com.ifgoiano.biblioteca.model.Livro;
import com.ifgoiano.biblioteca.service.ILivroService;
import com.ifgoiano.biblioteca.service.LivroService;

// Controlador para interação com os livros da biblioteca

@Controller
public class LivroController {

    @Autowired
    private ILivroService livroService;

    public void run(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Gerenciar Livros:");
                System.out.println("1. Cadastrar Livro");
                System.out.println("2. Listar Livros");
                System.out.println("3. Buscar Livro por ID");
                System.out.println("4. Buscar Livro por Nome");
                System.out.println("5. Atualizar Livro");
                System.out.println("6. Excluir Livro");
                System.out.println("7. Emprestar Livro");
                System.out.println("8. Devolver Livro");
                System.out.println("0. Voltar");
                int opcao = Integer.parseInt(scanner.nextLine());

                if (opcao == 0) break;

                switch (opcao) {
                    case 1:
                        cadastrarLivro(scanner);
                        break;
                    case 2:
                        listarLivros();
                        break;
                    case 3:
                        buscarLivroPorId(scanner);
                        break;
                    case 4:
                        buscarLivroPorNome(scanner);
                        break;
                    case 5:
                        atualizarLivro(scanner);
                        break;
                    case 6:
                        excluirLivro(scanner);
                        break;
                    case 7:
                        emprestarLivro(scanner);
                        break;
                    case 8:
                        devolverLivro(scanner);
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void cadastrarLivro(Scanner scanner) {
        System.out.println("Título:");
        String titulo = scanner.nextLine();
        System.out.println("Autor:");
        String autor = scanner.nextLine();
        System.out.println("Ano de Publicação:");
        int anoPub = Integer.parseInt(scanner.nextLine());
        System.out.println("ISBN:");
        String isbn = scanner.nextLine();

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAnoPub(anoPub);
        livro.setIsbn(isbn);

        livroService.save(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    private void listarLivros() {
        List<Livro> livros = livroService.findAll();
        livros.forEach(l -> System.out.println(l.getId() + " - " + l.getTitulo()));
    }

    private void buscarLivroPorId(Scanner scanner) {
        System.out.println("Digite o ID do livro:");
        Long id = Long.parseLong(scanner.nextLine());

        Livro livro = livroService.findById(id);
        if (livro != null) {
            System.out.println("ID: " + livro.getId());
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Ano de Publicação: " + livro.getAnoPub());
            System.out.println("ISBN: " + livro.getIsbn());
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private void buscarLivroPorNome(Scanner scanner) {
        System.out.println("Digite o nome do livro:");
        String nome = scanner.nextLine();

        List<Livro> livros = livroService.findByTituloContaining(nome);
        livros.forEach(l -> System.out.println(l.getId() + " - " + l.getTitulo()));
    }

    private void atualizarLivro(Scanner scanner) {
        System.out.println("Digite o ID do livro que deseja atualizar:");
        Long id = Long.parseLong(scanner.nextLine());

        Livro livro = livroService.findById(id);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        System.out.println("Título (atual: " + livro.getTitulo() + "):");
        String titulo = scanner.nextLine();
        System.out.println("Autor (atual: " + livro.getAutor() + "):");
        String autor = scanner.nextLine();
        System.out.println("Ano de Publicação (atual: " + livro.getAnoPub() + "):");
        int anoPub = Integer.parseInt(scanner.nextLine());
        System.out.println("ISBN (atual: " + livro.getIsbn() + "):");
        String isbn = scanner.nextLine();

        livro.setTitulo(titulo.isEmpty() ? livro.getTitulo() : titulo);
        livro.setAutor(autor.isEmpty() ? livro.getAutor() : autor);
        livro.setAnoPub(anoPub == 0 ? livro.getAnoPub() : anoPub);
        livro.setIsbn(isbn.isEmpty() ? livro.getIsbn() : isbn);

        livroService.save(livro);
        System.out.println("Livro atualizado com sucesso!");
    }

    private void excluirLivro(Scanner scanner) {
        System.out.println("Digite o ID do livro que deseja excluir:");
        Long id = Long.parseLong(scanner.nextLine());

        livroService.deleteById(id);
        System.out.println("Livro excluído com sucesso!");
    }

    private void emprestarLivro(Scanner scanner) {
        System.out.println("Digite o ID do livro que deseja emprestar:");
        Long id = Long.parseLong(scanner.nextLine());

        Livro livro = livroService.findById(id);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        livro.setEmprestado(true);
        livroService.save(livro);
        System.out.println("Livro emprestado com sucesso!");
    }

    private void devolverLivro(Scanner scanner) {
        System.out.println("Digite o ID do livro que deseja devolver:");
        Long id = Long.parseLong(scanner.nextLine());

        Livro livro = livroService.findById(id);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        livro.setEmprestado(false);
        livroService.save(livro);
        System.out.println("Livro devolvido com sucesso!");
    }
}