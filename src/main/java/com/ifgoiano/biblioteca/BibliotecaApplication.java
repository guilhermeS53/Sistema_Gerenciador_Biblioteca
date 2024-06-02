package com.ifgoiano.biblioteca; // Definição do pacote onde a classe está registrada

import java.util.Scanner; // Importações necessárias para uso de Scanner e componentes do Spring Boot

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import com.ifgoiano.biblioteca.controller.LivroController;
import com.ifgoiano.biblioteca.controller.UsuarioController;
import com.ifgoiano.biblioteca.controller.CategoriaController;
import com.ifgoiano.biblioteca.controller.EmprestimoController;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;

// Anotação para indicar que esta é uma aplicação Spring Boot
@SpringBootApplication
// Define a ordem de execução da aplicação, sendo executada após o DataLoader
@Order(2)
public class BibliotecaApplication implements CommandLineRunner {

    // Injeção de dependências dos controladores da aplicação
    @Autowired
    private UsuarioController usuarioController;

    @Autowired
    private LivroController livroController;

    @Autowired
    private EmprestimoController emprestimoController;

    @Autowired
    private CategoriaController categoriaController;

    // Método principal da aplicação, que inicia o Spring Boot
    public static void main(String[] args) {
        SpringApplication.run(BibliotecaApplication.class, args);
    }

    // Método run é executado após a inicialização da aplicação
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Exibição do menu de opções para o usuário
            System.out.println("Escolha uma opção:");
            System.out.println();
            System.out.println("1. Gerenciar Usuários");
            System.out.println("2. Gerenciar Livros");
            System.out.println("3. Gerenciar Empréstimos");
            System.out.println("4. Gerenciar Categorias");
            System.out.println("0. Sair");
            System.out.println();

            // Leitura da opção escolhida pelo usuário
            String input = scanner.nextLine();
            int opcao = -1;

            try {
                if (input.isEmpty()) {
                    throw new NumberFormatException();
                }
                opcao = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                // Tratamento de entrada inválida
                System.out.println("Entrada inválida. Tente novamente.");
                continue;
            }

            // Verifica se o usuário escolheu sair do sistema
            if (opcao == 0) {
                System.out.println("Encerrando Sistema de Biblioteca...");
                break;
            }

            // Executa a ação correspondente à opção escolhida pelo usuário
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
                    // Tratamento de opção inválida
                    System.out.println("Opção inválida! Tente novamente!");
                    System.out.println();
            }
        }
        // Fechamento do scanner
        scanner.close();
    }
}