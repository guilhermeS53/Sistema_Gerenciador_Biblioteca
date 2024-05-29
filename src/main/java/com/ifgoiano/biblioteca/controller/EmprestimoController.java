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
            System.out.println("Gerenciar Empréstimos:");
            System.out.println("1. Registrar Empréstimo");
            System.out.println("2. Registrar Devolução");
            System.out.println("3. Listar Empréstimos");
            System.out.println("0. Voltar");
            int opcao = Integer.parseInt(scanner.nextLine());

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
            System.out.println("ID do Livro:");
            Long livroId = Long.parseLong(scanner.nextLine());
            Livro livro = livroService.findById(livroId);
            if (livro.isEmprestado()) {
                System.out.println("Livro indisponível.");
                return;
            }

            System.out.println("ID do Usuário:");
            Long usuarioId = Long.parseLong(scanner.nextLine());
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
            System.out.println("ID do Empréstimo:");
            Long emprestimoId = Long.parseLong(scanner.nextLine());
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
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
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
        }
    }
}
