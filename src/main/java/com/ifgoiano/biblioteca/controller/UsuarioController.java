package com.ifgoiano.biblioteca.controller; // Definição do pacote onde a classe está registrada

import java.util.List; // Importação da classe List para utilização em Coleções
import java.util.Scanner; // Importação da classe Scanner para leitura de dados do console

import org.springframework.beans.factory.annotation.Autowired; // Importação para injeção de dependência

// Importação para definição de controlador Spring
import org.springframework.stereotype.Controller;

import com.ifgoiano.biblioteca.model.ResourceNotFoundException; // Importação da exceção personalizada
import com.ifgoiano.biblioteca.model.Usuario; // Importação do modelo Usuario
import com.ifgoiano.biblioteca.model.UsuarioComEmprestimosException; // Importação de exceção personalizada para relação Emprestimo e Usuario
import com.ifgoiano.biblioteca.service.UsuarioService;
// Controlador para interação com os livros da Biblioteca

@Controller // Anotação que indica que esta classe é um controlador gerenciado pelo Spring
public class UsuarioController {

    @Autowired // Injeção de dependência do serviço de usuario
    private UsuarioService usuarioService;

    // Método principal que gerencia o menu e as interações com o usuário
    public void run(Scanner scanner) {
        while (true) {
            // Exibição do menu
            System.out.println();
            System.out.println("Gerenciar Usuários:");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Listar Usuários");
            System.out.println("3. Atualizar Usuário");
            System.out.println("4. Excluir Usuário");
            System.out.println("0. Voltar");
            System.out.println();
            
            String input = scanner.nextLine(); // Leitura da entrada do usuário
            int opcao = -1;

            try {
                if (input.isEmpty()) {
                    throw new NumberFormatException(); // Lança exceção se a entrada estiver vazia
                }
                opcao = Integer.parseInt(input); // Converte a entrada para um número inteiro
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                continue; // Continua o loop em caso de entrada inválida
            }

            if (opcao == 0) {
                break; // Sai do loop e retorna ao menu principal
            }

            // Chama o método apropriado com base na opção selecionada
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
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // Método para cadastrar um novo usuário
    private void cadastrarUsuario(Scanner scanner) {
        System.out.println("Nome (Tecle 0 se quiser voltar):");
        String nome = scanner.nextLine();
        if (nome.equals("0")) {
            return; // Retorna ao menu anterior se o usuário digitar '0'
        }

        System.out.println("Email (Tecle 0 se quiser voltar):");
        String email = scanner.nextLine();
        if (email.equals("0")) {
            return; // Retorna ao menu anterior se o usuário digitar '0'
        }

        System.out.println("Telefone (Tecle 0 se quiser voltar):");
        String telefone = scanner.nextLine();
        if (telefone.equals("0")) {
            return; // Retorna ao menu anterior se o usuário digitar '0'
        }

        // Cria um novo objeto Usuario e define seus atributos
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setTelefone(telefone);

        // Salva o novo usuário no banco de dados
        usuarioService.save(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
        System.out.println();
    }

    // Método para listar todos os usuários
    private void listarUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll(); // Busca todos os usuários do banco de dados
        usuarios.forEach(u -> System.out.println(u.getId() + " - " + u.getNome())); // Exibe cada usuário no console
        System.out.println();
    }

    // Método para atualizar as informações de um usuário
    private void atualizarUsuario(Scanner scanner) {
        listarUsuarios(); // Lista todos os usuários para que o usuário escolha qual deseja atualizar
        System.out.println("Digite o ID do usuário que deseja atualizar (Tecle 0 se quiser voltar):");
        String idStr = scanner.nextLine();
        if (idStr.equals("0")) {
            return; // Retorna ao menu anterior se o usuário digitar '0'
        }

        Long id;
        try {
            id = Long.parseLong(idStr); // Converte a entrada para um número longo
        } catch (NumberFormatException e) {
            System.out.println("ID inválido! Por favor, insira um número válido.");
            return;
        }

        Usuario usuario;
        try {
            // Busca o usuário pelo ID
            usuario = usuarioService.findById(id);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println();
            return;
        }

        // Atualiza os dados do usuário
        System.out.println("Nome (atual: " + usuario.getNome() + ")(Tecle 0 se quiser voltar):");
        String nome = scanner.nextLine();
        if (nome.equals("0")) {
            return;
        }

        System.out.println("Email (atual: " + usuario.getEmail() + ")(Tecle 0 se quiser voltar):");
        String email = scanner.nextLine();
        if (email.equals("0")) {
            return;
        }

        System.out.println("Telefone (atual: " + usuario.getTelefone() + ")(Tecle 0 se quiser voltar):");
        String telefone = scanner.nextLine();
        if (telefone.equals("0")) {
            return;
        }

        // Atualiza os campos do usuário, mantendo os valores atuais se os novos estiverem vazios
        usuario.setNome(nome.isEmpty() ? usuario.getNome() : nome);
        usuario.setEmail(email.isEmpty() ? usuario.getEmail() : email);
        usuario.setTelefone(telefone.isEmpty() ? usuario.getTelefone() : telefone);

        // Salva as alterações no banco de dados
        usuarioService.save(usuario);
        System.out.println("Usuário atualizado com sucesso!");
        System.out.println();
    }

    // Método para excluir um usuário
    private void excluirUsuario(Scanner scanner) {
        listarUsuarios(); // Lista todos os usuários para que o usuário escolha qual deseja excluir
        System.out.println("Digite o ID do usuário que deseja excluir (Tecle 0 se quiser voltar):");
        String idStr = scanner.nextLine();
        if (idStr.equals("0")) {
            return; // Retorna ao menu anterior se o usuário digitar '0'
        }

        Long id;
        try {
            id = Long.parseLong(idStr); // Converte a entrada para um número longo
        } catch (NumberFormatException e) {
            System.out.println("ID inválido! Por favor, insira um número válido.");
            return;
        }

        try {
            // Tenta excluir o usuário pelo ID
            usuarioService.deleteById(id);
            System.out.println("Usuário excluído com sucesso!");
            System.out.println();
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.out.println();
        } catch (UsuarioComEmprestimosException ex) {
            System.out.println(ex.getMessage());
            System.out.println();
        }
    }
}