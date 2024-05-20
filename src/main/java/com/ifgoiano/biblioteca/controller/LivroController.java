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

    /*
     * Executa o controlador, permitindo interação com os livros da Biblioteca
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Escolha uma opção abaixo:");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("3. Buscar Livro por ID");
            System.out.println("4. Buscar Livro por Nome");
            System.out.println("5. Atualizar Livro");
            System.out.println("6. Excluir Livro");
            System.out.println("7. Emprestar Livro");
            System.out.println("8. Devolver Livro");
            System.out.println("0. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0)
                break;

            switch (opcao) {
                case 1:
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
                    break;
                case 2:
                    List<Livro> livros = livroService.findAll();
                    livros.forEach(l -> System.out.println(l.getTitulo()));
                    break;
                case 3:
                    System.out.println("ID do Livro:");
                    Long id = Long.parseLong(scanner.nextLine());
                    Livro livroById = livroService.findById(id);
                    if (livroById != null) {
                        System.out.println("Título: " + livroById.getTitulo());
                        System.out.println("Autor: " + livroById.getAutor());
                        System.out.println("Ano de Publicação: " + livroById.getAnoPub());
                        System.out.println("ISBN: " + livroById.getIsbn());
                        System.out.println("Emprestado: " + (livroById.isEmprestado() ? "Sim" : "Não"));
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("Nome do Livro:");
                    String nome = scanner.nextLine();
                    List<Livro> livrosByNome = livroService.findByTituloContaining(nome);
                    livrosByNome.forEach(l -> System.out.println(l.getTitulo()));
                    break;
                case 5:
                    System.out.println("ID do Livro para Atualizar:");
                    Long idToUpdate = Long.parseLong(scanner.nextLine());
                    Livro livroToUpdate = livroService.findById(idToUpdate);
                    if (livroToUpdate != null) {
                        System.out.println("Novo Título:");
                        livroToUpdate.setTitulo(scanner.nextLine());
                        System.out.println("Novo Autor:");
                        livroToUpdate.setAutor(scanner.nextLine());
                        System.out.println("Novo Ano de Publicação:");
                        livroToUpdate.setAnoPub(Integer.parseInt(scanner.nextLine()));
                        System.out.println("Novo ISBN:");
                        livroToUpdate.setIsbn(scanner.nextLine());
                        livroService.save(livroToUpdate);
                        System.out.println("Livro atualizado com sucesso!");
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;
                case 6:
                    System.out.println("ID do Livro para Excluir:");
                    Long idToDelete = Long.parseLong(scanner.nextLine());
                    livroService.deleteById(idToDelete);
                    System.out.println("Livro excluído com sucesso!");
                    break;
                case 7:
                    System.out.println("ID do Livro para Emprestar:");
                    Long idToEmprestar = Long.parseLong(scanner.nextLine());
                    Livro livroEmprestado = livroService.emprestarLivro(idToEmprestar);
                    if (livroEmprestado != null) {
                        System.out.println("Livro emprestado com sucesso!");
                    } else {
                        System.out.println("Livro não encontrado ou já emprestado.");
                    }
                    break;
                case 8:
                    System.out.println("ID do Livro para Devolver:");
                    Long idToDevolver = Long.parseLong(scanner.nextLine());
                    Livro livroDevolvido = livroService.devolverLivro(idToDevolver);
                    if (livroDevolvido != null) {
                        System.out.println("Livro devolvido com sucesso!");
                    } else {
                        System.out.println("Livro não encontrado ou não está emprestado.");
                    }
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }
}