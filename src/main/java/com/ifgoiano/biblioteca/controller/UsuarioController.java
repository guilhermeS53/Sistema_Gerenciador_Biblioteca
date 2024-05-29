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
            System.out.println();
            System.out.println("Gerenciar Usuários:");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Listar Usuários");
            System.out.println("3. Atualizar Usuário");
            System.out.println("4. Excluir Usuário");
            System.out.println("5. Login");
            System.out.println("0. Voltar");
            System.out.println();
            int opcao = Integer.parseInt(scanner.nextLine());

            if (opcao == 0) break;

            switch (opcao) {
                case 1:
                    cadastrarUsuario(scanner);
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    atualizarUsuario(scanner);
                    break;
                case 4:
                    excluirUsuario(scanner);
                    break;
                case 5:
                    login(scanner);
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
        System.out.println();
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        usuarios.forEach(u -> System.out.println(u.getId() + " - " + u.getNome()));
    }

    private void atualizarUsuario(Scanner scanner) {
        listarUsuarios();
        System.out.println("Digite o ID do usuário que deseja atualizar:");
        Long id = Long.parseLong(scanner.nextLine());

        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            System.out.println();
            return;
        }

        System.out.println("Nome (atual: " + usuario.getNome() + "):");
        String nome = scanner.nextLine();
        System.out.println("Email (atual: " + usuario.getEmail() + "):");
        String email = scanner.nextLine();
        System.out.println("Login (atual: " + usuario.getLogin() + "):");
        String login = scanner.nextLine();
        System.out.println("Senha (atual: " + usuario.getSenha() + "):");
        String senha = scanner.nextLine();

        usuario.setNome(nome.isEmpty() ? usuario.getNome() : nome);
        usuario.setEmail(email.isEmpty() ? usuario.getEmail() : email);
        usuario.setLogin(login.isEmpty() ? usuario.getLogin() : login);
        usuario.setSenha(senha.isEmpty() ? usuario.getSenha() : senha);

        usuarioService.save(usuario);
        System.out.println("Usuário atualizado com sucesso!");
        System.out.println();
    }

    private void excluirUsuario(Scanner scanner) {
        listarUsuarios(); // Lista usuário em tela
        System.out.println("Digite o ID do usuário que deseja excluir:");
        System.out.println();
        Long id = Long.parseLong(scanner.nextLine());

        usuarioService.deleteById(id);
        System.out.println("Usuário excluído com sucesso!");
        System.out.println();
    }

    public void login(Scanner scanner) {
        System.out.println("Login:");
        String login = scanner.nextLine();
        System.out.println("Senha:");
        String senha = scanner.nextLine();

        Usuario usuario = usuarioService.authenticate(login, senha);
        if (usuario != null) {
            System.out.println("Login realizado com sucesso! Bem-vindo, " + usuario.getNome() + "!");
            System.out.println();
        } else {
            System.out.println("Login ou senha incorretos.");
            System.out.println();
        }
    }
}