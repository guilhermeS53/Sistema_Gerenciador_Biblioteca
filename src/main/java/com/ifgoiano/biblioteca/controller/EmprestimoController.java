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

            if (opcao == 0) break;

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
        System.out.println("ID do Livro:");
        Long livroId = Long.parseLong(scanner.nextLine());
        Livro livro = livroService.findById(livroId);
        if (livro == null || livro.isEmprestado()) {
            System.out.println("Livro indisponível.");
            return;
        }

        System.out.println("ID do Usuário:");
        Long usuarioId = Long.parseLong(scanner.nextLine());
        Usuario usuario = usuarioService.findById(usuarioId);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuario);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setStatus(StatusEmprestimo.ATIVO);

        livro.setEmprestado(true);
        emprestimoService.save(emprestimo);
        livroService.save(livro);

        System.out.println("Empréstimo registrado com sucesso!");
    }

    private void registrarDevolucao(Scanner scanner) {
        System.out.println("ID do Empréstimo:");
        Long emprestimoId = Long.parseLong(scanner.nextLine());
        Emprestimo emprestimo = emprestimoService.findById(emprestimoId);
        if (emprestimo == null || emprestimo.getStatus() == StatusEmprestimo.DEVOLVIDO) {
            System.out.println("Empréstimo não encontrado ou já devolvido.");
            return;
        }

        emprestimo.setDataDevolucao(LocalDate.now());
        if (emprestimo.getDataDevolucao().isAfter(emprestimo.getDataEmprestimo().plusWeeks(2))) {
            emprestimo.setStatus(StatusEmprestimo.ATRASADO);
            emprestimo.setMulta(10.0);  // Exemplo de cálculo de multa
        } else {
            emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);
        }

        Livro livro = emprestimo.getLivro();
        livro.setEmprestado(false);

        emprestimoService.save(emprestimo);
        livroService.save(livro);

        System.out.println("Devolução registrada com sucesso!");
    }

    private void listarEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoService.findAll();
        emprestimos.forEach(e -> System.out.println(e.getId() + " - " + e.getLivro().getTitulo() + " - " + e.getStatus()));
    }
}