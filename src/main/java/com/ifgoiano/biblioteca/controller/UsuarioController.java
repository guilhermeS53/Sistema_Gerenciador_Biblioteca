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

    public void run(Scanner scanner) {
        while (true) {
            System.out.println("Gerenciar Usuários:");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Listar Usuários");
            System.out.println("0. Voltar");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0)
                break;

            switch (opcao) {
                case 1:
                    cadastrarUsuario(scanner);
                    break;
                case 2:
                    listarUsuarios();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarUsuario(Scanner scanner) {
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
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        usuarios.forEach(u -> System.out.println(u.getId() + " - " + u.getNome()));
    }
}