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
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("0. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) break;

            switch (opcao) {
                case 1:
                    System.out.println("Título:");
                    String titulo = scanner.nextLine();
                    System.out.println("Autor:");
                    String autor = scanner.nextLine();
                    System.out.println("ISBN:");
                    String isbn = scanner.nextLine();

                    Livro livro = new Livro();
                    livro.setTitulo(titulo);
                    livro.setAutor(autor);
                    livro.setIsbn(isbn);

                    livroService.save(livro);
                    System.out.println("Livro cadastrado com sucesso!");
                    break;
                case 2:
                    List<Livro> livros = livroService.findAll();
                    livros.forEach(l -> System.out.println(l.getTitulo()));
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}