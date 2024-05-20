package com.ifgoiano.biblioteca.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import com.ifgoiano.biblioteca.model.Livro;
import com.ifgoiano.biblioteca.service.LivroService;

// Controlador para interação com os livros da biblioteca

@Controller
public class LivroController {

    @Autowired
    private LivroService livroService;

    public void run(Scanner scanner) {
        while (true) {
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
            int opcao = scanner.nextInt();
            scanner.nextLine();

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
        livro.setEmprestado(false); // Novo livro não está emprestado por padrão

        livroService.save(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    private void listarLivros() {
        List<Livro> livros = livroService.findAll();
        livros.forEach(l -> System.out.println(l.getId() + " - " + l.getTitulo() + " (Emprestado " + (l.isEmprestado() ? "Sim" : "Não") + ")"));
    }

    private void buscarLivroPorId(Scanner scanner) {
        System.out.println("ID do Livro:");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Livro livro = livroService.findById(id);
        if (livro != null) {
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private void buscarLivroPorNome(Scanner scanner) {
        System.out.println("Nome do Livro:");
        String nome = scanner.nextLine();
        List<Livro> livros = livroService.findByTituloContaining(nome);
        livros.forEach(l -> System.out.println(l.getId() + " - " + l.getTitulo() + " (Emprestado: " + (l.isEmprestado() ? "Sim" : "Não") + ")"));
    }

    private void atualizarLivro(Scanner scanner) {
        System.out.println("ID do Livro:");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Livro livro = livroService.findById(id);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        System.out.println("Novo Título (deixe vazio para não alterar):");
        String titulo = scanner.nextLine();
        if (!titulo.isEmpty()) {
            livro.setTitulo(titulo);
        }
        System.out.println("Novo Autor (deixe vazio para não alterar):");
        String autor = scanner.nextLine();
        if (!autor.isEmpty()) {
            livro.setAutor(autor);
        }
        System.out.println("Novo Ano de Publicação (deixe vazio para não alterar):");
        String anoPubStr = scanner.nextLine();
        if (!anoPubStr.isEmpty()) {
            livro.setAnoPub(Integer.parseInt(anoPubStr));
        }
        System.out.println("Novo ISBN (deixe vazio para não alterar):");
        String isbn = scanner.nextLine();
        if (!isbn.isEmpty()) {
            livro.setIsbn(isbn);
        }
        livroService.save(livro);
        System.out.println("Livro atualizado com sucesso!");
    }

    private void excluirLivro(Scanner scanner) {
        System.out.println("ID do Livro:");
        Long id = scanner.nextLong();
        scanner.nextLine();
        livroService.deleteById(id);
        System.out.println("Livro excluído com sucesso!");
    }

    private void emprestarLivro(Scanner scanner) {
        System.out.println("ID do Livro:");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Livro livro = livroService.findById(id);
        if (livro != null && !livro.isEmprestado()) {
            livro.setEmprestado(true);
            livroService.save(livro);
            System.out.println("Livro emprestado com sucesso!");
        } else if (livro == null) {
            System.out.println("Livro não encontrado.");
        } else {
            System.out.println("Livro já está emprestado.");
        }
    }

    private void devolverLivro(Scanner scanner) {
        System.out.println("ID do Livro:");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Livro livro = livroService.findById(id);
        if (livro != null && livro.isEmprestado()) {
            livro.setEmprestado(false);
            livroService.save(livro);
            System.out.println("Livro devolvido com sucesso!");
        } else if (livro == null) {
            System.out.println("Livro não encontrado.");
        } else {
            System.out.println("Livro não está emprestado.");
        }
    }
}