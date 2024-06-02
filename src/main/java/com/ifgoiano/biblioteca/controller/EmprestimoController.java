package com.ifgoiano.biblioteca.controller; // Definição do pacote onde a classe está registrada

import java.time.LocalDate; // Importação da classe LocalDate para visualização de data local
import java.util.List; // Importação da classe List para utilização em Coleções
import java.util.Scanner; // Importação da classe Scanner para leitura de dados do console

import org.springframework.stereotype.Controller; // Importação para definição de controlador Spring
import org.springframework.beans.factory.annotation.Autowired; // Importação para injeção de dependência

import com.ifgoiano.biblioteca.model.Emprestimo; // Importação do modelo Emprestimo
import com.ifgoiano.biblioteca.service.IEmprestimoService; // Importação do serviço de Emprestimo
import com.ifgoiano.biblioteca.model.StatusEmprestimo; // Importação do enum StatusEmprestimo
import com.ifgoiano.biblioteca.model.Livro; // Importação do modelo Livro
import com.ifgoiano.biblioteca.model.ResourceNotFoundException; // Importação da exceção personalizada
import com.ifgoiano.biblioteca.model.Usuario; // Importação do modelo Usuario
import com.ifgoiano.biblioteca.service.ILivroService; // Importação do serviço de Livro
import com.ifgoiano.biblioteca.service.IUsuarioService; // Importação do serviço de Usuario

@Controller // Anotação que indica que esta classe é um controlador gerenciado pelo Spring
public class EmprestimoController {

    @Autowired // Injeção de dependência do serviço de empréstimo
    private IEmprestimoService emprestimoService;

    @Autowired // Injeção de dependência do serviço de livros
    private ILivroService livroService;

    @Autowired // Injeção de dependência do serviço de usuários
    private IUsuarioService usuarioService;

    // Injeção de dependência do serviço de usuários
    public void run(Scanner scanner) {
        while (true) {
            // Exibição do menu ao usuario
            System.out.println();
            System.out.println("Gerenciar Empréstimos:");
            System.out.println("1. Registrar Empréstimo");
            System.out.println("2. Registrar Devolução");
            System.out.println("3. Listar Empréstimos");
            System.out.println("0. Voltar");
            System.out.println();
            
            String input = scanner.nextLine(); // Leitura da entrada do usuario
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

            if (opcao == 0)
            break; // Sai do loop e retorna ao menu principal
            
            // Chama o método apropriado com base na opção selecionada
            switch (opcao) {
                case 1:
                    registrarEmprestimo(scanner);
                    break;
                case 2:
                    registrarDevolucao(scanner);
                    break;
                case 3:
                    listarEmprestimos();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // Método para registrar um novo empréstimo
    private void registrarEmprestimo(Scanner scanner) {
        try {
            listarLivrosDisponiveis(); // Lista todos os livros disponíveis
            System.out.println();
            System.out.println("ID do Livro (Tecle 0 se quiser voltar):");
            String livroIdStr = scanner.nextLine();
            if (livroIdStr.equals("0")) return;

            Long livroId = -1L;
            try {
                if (livroIdStr.isEmpty()) {
                    throw new NumberFormatException();
                }
                livroId = Long.parseLong(livroIdStr); // Converte a entrada para um número longo
            } catch (NumberFormatException e) {
                System.out.println("ID do livro inválido! Por favor, insira um número válido.");
                return;
            }

            Livro livro = livroService.findById(livroId); // Busca o livro pelo ID
            if (livro.isEmprestado()) { // Verifica se o livro está disponível
                System.out.println();
                System.out.println("Livro indisponível.");
                return;
            }

            listarUsuarios(); // Lista todos os usuários
            System.out.println();
            System.out.println("ID do Usuário (Tecle 0 se quiser voltar):");
            String usuarioIdStr = scanner.nextLine();
            if (usuarioIdStr.equals("0")) return;

            Long usuarioId = -1L;
            try {
                if (usuarioIdStr.isEmpty()) {
                    throw new NumberFormatException();
                }
                usuarioId = Long.parseLong(usuarioIdStr); // Converte a entrada para um número longo
            } catch (NumberFormatException e) {
                System.out.println("ID do usuário inválido! Por favor, insira um número válido.");
                return;
            }

            Usuario usuario = usuarioService.findById(usuarioId); // Busca o usuário pelo ID

            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setLivro(livro);
            emprestimo.setUsuario(usuario);
            emprestimo.setDataEmprestimo(LocalDate.now()); // Define a data atual como data do empréstimo
            emprestimo.setStatus(StatusEmprestimo.ATIVO); // Define o status do empréstimo como ativo

            livro.setEmprestado(true); // Marca o livro como emprestado
            emprestimoService.save(emprestimo); // Salva o empréstimo
            livroService.save(livro); // Atualiza o status do livro

            System.out.println("Empréstimo registrado com sucesso!");
            System.out.println();
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Erro ao registrar empréstimo: " + ex.getMessage());
            System.out.println();
        }
    }

    // Método para registrar a devolução de um empréstimo
    private void registrarDevolucao(Scanner scanner) {
        try {
            listarEmprestimosAtivos(); // Lista todos os empréstimos ativos
            System.out.println("ID do Empréstimo (Tecle 0 se quiser voltar):");
            String emprestimoIdStr = scanner.nextLine();
            if (emprestimoIdStr.equals("0")) return;

            Long emprestimoId = -1L;
            try {
                if (emprestimoIdStr.isEmpty()) {
                    throw new NumberFormatException();
                }
                emprestimoId = Long.parseLong(emprestimoIdStr); // Converte a entrada para um número longo
            } catch (NumberFormatException e) {
                System.out.println("ID do empréstimo inválido! Por favor, insira um número válido.");
                return;
            }

            Emprestimo emprestimo = emprestimoService.findById(emprestimoId); // Busca o empréstimo pelo ID
            if (emprestimo.getStatus() == StatusEmprestimo.DEVOLVIDO) { // Verifica se o empréstimo já foi devolvido
                System.out.println("Empréstimo já devolvido.");
                System.out.println();
                return;
            }

            emprestimo.setDataDevolucao(LocalDate.now()); // Define a data atual como data de devolução
            emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO); // Define o status do empréstimo como devolvido
            emprestimo.getLivro().setEmprestado(false); // Marca o livro como não emprestado

            emprestimoService.save(emprestimo); // Atualiza o empréstimo
            livroService.save(emprestimo.getLivro()); // Atualiza o status do livro

            System.out.println("Devolução registrada com sucesso!");
            System.out.println();
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.out.println();
        } catch (Exception ex) {
            System.out.println("Erro ao registrar devolução: " + ex.getMessage());
            System.out.println();
        }
    }

    // Método para listar todos os empréstimos
    private void listarEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoService.findAll(); // Busca todos os empréstimos
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println("ID: " + emprestimo.getId() + " | Livro: " + emprestimo.getLivro().getTitulo() +
                " | Usuário: " + emprestimo.getUsuario().getNome() + " | Status: " + emprestimo.getStatus() +
                " | Data Empréstimo: " + emprestimo.getDataEmprestimo() + 
                (emprestimo.getDataDevolucao() != null ? " | Data Devolução: " + emprestimo.getDataDevolucao() : ""));
            System.out.println();
        }
    }

    // Método para listar todos os livros disponíveis para empréstimo
    private void listarLivrosDisponiveis() {
        List<Livro> livros = livroService.findAll(); // Busca todos os livros
        System.out.println("Livros Disponíveis:");
        for (Livro livro : livros) {
            if (!livro.isEmprestado()) { // Verifica se o livro está disponível
                System.out.println("ID: " + livro.getId() + " | Título: " + livro.getTitulo() + " | Autor: " + livro.getAutor());
                System.out.println();
            }
        }
    }

    // Método para listar todos os usuários
    private void listarUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll(); // Busca todos os usuários
        System.out.println("Usuários:");
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId() + " | Nome: " + usuario.getNome());
            System.out.println();
        }
    }

    // Método para listar todos os empréstimos ativos
    private void listarEmprestimosAtivos() {
        List<Emprestimo> emprestimos = emprestimoService.findAll(); // Busca todos os empréstimos
        System.out.println("Empréstimos Ativos:");
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getStatus() == StatusEmprestimo.ATIVO) { // Verifica se o status é ativo
                System.out.println("ID: " + emprestimo.getId() + " | Livro: " + emprestimo.getLivro().getTitulo() +
                    " | Usuário: " + emprestimo.getUsuario().getNome() + " | Data Empréstimo: " + emprestimo.getDataEmprestimo());
                System.out.println();
            }
        }
    }
}