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

@SpringBootApplication
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
            System.out.println("1. Gerenciar Usuários");
            System.out.println("2. Gerenciar Livros");
            System.out.println("3. Gerenciar Empréstimos");
            System.out.println("4. Gerenciar Categorias");
            System.out.println("5. Login");
            System.out.println("0. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                System.out.println("Encerrando o sistema...");
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
                case 5:
                    usuarioController.login(scanner);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }
}
