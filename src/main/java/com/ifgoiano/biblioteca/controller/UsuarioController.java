package com.ifgoiano.biblioteca.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import com.ifgoiano.biblioteca.model.Usuario;
import com.ifgoiano.biblioteca.service.UsuarioService;
// Controlador para interação com os livros da Biblioteca

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    /*Executa o Controlador de Usuários, permitindo interação com os usuários da Biblioteca 
    */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Escolha uma opção abaixo:");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Listar Usuários");
            System.out.println("0. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0)
                break;

            switch (opcao) {
                case 1:
                    System.out.println("Nome:");
                    String nome = scanner.nextLine();
                    System.out.println("Email:");
                    String email = scanner.nextLine();
                    System.out.println("Login:");
                    String login = scanner.nextLine();
                    System.out.println("Senha:");
                    String senha = scanner.nextLine();

                    Usuario usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setLogin(login);
                    usuario.setSenha(senha);

                    usuarioService.save(usuario);
                    System.out.println("Usuário cadastrado com sucesso!");
                    break;
                case 2:
                    List<Usuario> usuarios = usuarioService.findAll();
                    usuarios.forEach(u -> System.out.println(u.getNome()));
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}