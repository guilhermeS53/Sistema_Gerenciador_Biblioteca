package com.ifgoiano.biblioteca;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import com.ifgoiano.biblioteca.controller.LivroController;
import com.ifgoiano.biblioteca.controller.UsuarioController;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner {

    @Autowired
    private UsuarioController usuarioController;
    @Autowired
    private LivroController livroController;

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Escolha: 1 para Gerenciar Usuários ou 2 para Gerenciar Livros:");
            System.out.println("1. Usuários");
            System.out.println("2. Livros");
            System.out.println("0. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) break;

            switch (opcao) {
                case 1:
                    usuarioController.run();
                    break;
                case 2:
                    livroController.run();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }
}
