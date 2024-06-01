package com.ifgoiano.biblioteca;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import com.ifgoiano.biblioteca.controller.LivroController;
import com.ifgoiano.biblioteca.controller.UsuarioController;
import com.ifgoiano.biblioteca.controller.CategoriaController;
import com.ifgoiano.biblioteca.controller.EmprestimoController;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;

@SpringBootApplication
@Order(2) // Para execução após o DataLoader
public class BibliotecaApplication implements CommandLineRunner {

    @Autowired
    private UsuarioController usuarioController;

    @Autowired
    private LivroController livroController;

    @Autowired
    private EmprestimoController emprestimoController;

    @Autowired
    private CategoriaController categoriaController;

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println();
            System.out.println("1. Gerenciar Usuários");
            System.out.println("2. Gerenciar Livros");
            System.out.println("3. Gerenciar Empréstimos");
            System.out.println("4. Gerenciar Categorias");
            System.out.println("0. Sair");
            System.out.println();

            String input = scanner.nextLine();
            int opcao = -1;

            try {
                if (input.isEmpty()) {
                    throw new NumberFormatException();
                }
                opcao = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Tente novamente.");
                continue;
            }

            if (opcao == 0) {
                System.out.println("Encerrando Sistema de Biblioteca...");
                break;
            }

            switch (opcao) {
                case 1:
                    usuarioController.run(scanner);
                    break;
                case 2:
                    livroController.run(scanner);
                    break;
                case 3:
                    emprestimoController.run(scanner);
                    break;
                case 4:
                    categoriaController.run(scanner);
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente!");
                    System.out.println();
            }
        }
        scanner.close();
    }
}
