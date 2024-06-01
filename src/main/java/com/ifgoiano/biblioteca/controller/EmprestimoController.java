package com.ifgoiano.biblioteca.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import com.ifgoiano.biblioteca.model.Emprestimo;
import com.ifgoiano.biblioteca.service.IEmprestimoService;
import com.ifgoiano.biblioteca.model.StatusEmprestimo;
import com.ifgoiano.biblioteca.model.Livro;
import com.ifgoiano.biblioteca.model.ResourceNotFoundException;
import com.ifgoiano.biblioteca.model.Usuario;
import com.ifgoiano.biblioteca.service.ILivroService;
import com.ifgoiano.biblioteca.service.IUsuarioService;

@Controller
public class EmprestimoController {

    @Autowired
    private IEmprestimoService emprestimoService;

    @Autowired
    private ILivroService livroService;

    @Autowired
    private IUsuarioService usuarioService;

    public void run(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("Gerenciar Empréstimos:");
            System.out.println("1. Registrar Empréstimo");
            System.out.println("2. Registrar Devolução");
            System.out.println("3. Listar Empréstimos");
            System.out.println("0. Voltar");
            System.out.println();
            
            String input = scanner.nextLine();
            int opcao = -1;

            try {
                if (input.isEmpty()) {
                    throw new NumberFormatException();
                }
                opcao = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                continue;
            }

            if (opcao == 0)
                break;

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

    private void registrarEmprestimo(Scanner scanner) {
        try {
            listarLivrosDisponiveis();
            System.out.println();
            System.out.println("ID do Livro (Tecle 0 se quiser voltar):");
            String livroIdStr = scanner.nextLine();
            if (livroIdStr.equals("0")) return;

            Long livroId = -1L;
            try {
                if (livroIdStr.isEmpty()) {
                    throw new NumberFormatException();
                }
                livroId = Long.parseLong(livroIdStr);
            } catch (NumberFormatException e) {
                System.out.println("ID do livro inválido! Por favor, insira um número válido.");
                return;
            }

            Livro livro = livroService.findById(livroId);
            if (livro.isEmprestado()) {
                System.out.println();
                System.out.println("Livro indisponível.");
                return;
            }

            listarUsuarios();
            System.out.println();
            System.out.println("ID do Usuário (Tecle 0 se quiser voltar):");
            String usuarioIdStr = scanner.nextLine();
            if (usuarioIdStr.equals("0")) return;

            Long usuarioId = -1L;
            try {
                if (usuarioIdStr.isEmpty()) {
                    throw new NumberFormatException();
                }
                usuarioId = Long.parseLong(usuarioIdStr);
            } catch (NumberFormatException e) {
                System.out.println("ID do usuário inválido! Por favor, insira um número válido.");
                return;
            }

            Usuario usuario = usuarioService.findById(usuarioId);

            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setLivro(livro);
            emprestimo.setUsuario(usuario);
            emprestimo.setDataEmprestimo(LocalDate.now());
            emprestimo.setStatus(StatusEmprestimo.ATIVO);

            livro.setEmprestado(true);
            emprestimoService.save(emprestimo);
            livroService.save(livro);

            System.out.println("Empréstimo registrado com sucesso!");
            System.out.println();
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Erro ao registrar empréstimo: " + ex.getMessage());
            System.out.println();
        }
    }

    private void registrarDevolucao(Scanner scanner) {
        try {
            listarEmprestimosAtivos();
            System.out.println("ID do Empréstimo (Tecle 0 se quiser voltar):");
            String emprestimoIdStr = scanner.nextLine();
            if (emprestimoIdStr.equals("0")) return;

            Long emprestimoId = -1L;
            try {
                if (emprestimoIdStr.isEmpty()) {
                    throw new NumberFormatException();
                }
                emprestimoId = Long.parseLong(emprestimoIdStr);
            } catch (NumberFormatException e) {
                System.out.println("ID do empréstimo inválido! Por favor, insira um número válido.");
                return;
            }

            Emprestimo emprestimo = emprestimoService.findById(emprestimoId);
            if (emprestimo.getStatus() == StatusEmprestimo.DEVOLVIDO) {
                System.out.println("Empréstimo já devolvido.");
                System.out.println();
                return;
            }

            emprestimo.setDataDevolucao(LocalDate.now());
            emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);
            emprestimo.getLivro().setEmprestado(false);

            emprestimoService.save(emprestimo);
            livroService.save(emprestimo.getLivro());

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

    private void listarEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoService.findAll();
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println("ID: " + emprestimo.getId() + " | Livro: " + emprestimo.getLivro().getTitulo() +
                " | Usuário: " + emprestimo.getUsuario().getNome() + " | Status: " + emprestimo.getStatus() +
                " | Data Empréstimo: " + emprestimo.getDataEmprestimo() + 
                (emprestimo.getDataDevolucao() != null ? " | Data Devolução: " + emprestimo.getDataDevolucao() : ""));
            System.out.println();
        }
    }

    private void listarLivrosDisponiveis() {
        List<Livro> livros = livroService.findAll();
        System.out.println("Livros Disponíveis:");
        for (Livro livro : livros) {
            if (!livro.isEmprestado()) {
                System.out.println("ID: " + livro.getId() + " | Título: " + livro.getTitulo() + " | Autor: " + livro.getAutor());
                System.out.println();
            }
        }
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        System.out.println("Usuários:");
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId() + " | Nome: " + usuario.getNome());
            System.out.println();
        }
    }

    private void listarEmprestimosAtivos() {
        List<Emprestimo> emprestimos = emprestimoService.findAll();
        System.out.println("Empréstimos Ativos:");
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getStatus() == StatusEmprestimo.ATIVO) {
                System.out.println("ID: " + emprestimo.getId() + " | Livro: " + emprestimo.getLivro().getTitulo() +
                    " | Usuário: " + emprestimo.getUsuario().getNome() + " | Data Empréstimo: " + emprestimo.getDataEmprestimo());
                System.out.println();
            }
        }
    }
}