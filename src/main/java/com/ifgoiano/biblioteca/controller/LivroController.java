package com.ifgoiano.biblioteca.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import com.ifgoiano.biblioteca.model.Categoria;
import com.ifgoiano.biblioteca.model.Livro;
import com.ifgoiano.biblioteca.service.CategoriaService;
import com.ifgoiano.biblioteca.service.LivroService;

// Controlador para interação com os livros da biblioteca

@Controller
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaController categoriaController;

    public void run(Scanner scanner) {
        while (true) {
            System.out.println("Gerenciar Livros:");
            System.out.println("1. Listar Livros");
            System.out.println("2. Adicionar Livro");
            System.out.println("3. Atualizar Livro");
            System.out.println("4. Deletar Livro");
            System.out.println("5. Buscar Livro por ID");
            System.out.println("6. Buscar Livro por Nome");
            System.out.println("0. Voltar");
            int opcao = Integer.parseInt(scanner.nextLine());

            if (opcao == 0)
                break;

            switch (opcao) {
                case 1:
                    listarLivros();
                    break;
                case 2:
                    adicionarLivro(scanner);
                    break;
                case 3:
                    atualizarLivro(scanner);
                    break;
                case 4:
                    deletarLivro(scanner);
                    break;
                case 5:
                    buscarLivroPorId(scanner);
                    break;
                case 6:
                    buscarLivroPorNome(scanner);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void listarLivros() {
        livroService.findAll().forEach(l -> {
            System.out.println("ID: " + l.getId());
            System.out.println("Título: " + l.getTitulo());
            System.out.println("Autor: " + l.getAutor());
            System.out.println("Ano de Publicação: " + l.getAnoPub());
            System.out.println("ISBN: " + l.getIsbn());
            System.out.println("Emprestado: " + l.isEmprestado());
            System.out.println("Categoria: " + (l.getCategoria() != null ? l.getCategoria().getNome() : "Sem Categoria"));
            System.out.println();
        });
    }

    private void buscarLivroPorId(Scanner scanner) {
        System.out.println("Digite o ID do livro:");
        Long id = Long.parseLong(scanner.nextLine());

        Livro livro = livroService.findById(id);
        if (livro != null) {
            livroService.findAll().forEach(l -> {
            System.out.println("ID: " + l.getId());
            System.out.println("Título: " + l.getTitulo());
            System.out.println("Autor: " + l.getAutor());
            System.out.println("Ano de Publicação: " + l.getAnoPub());
            System.out.println("ISBN: " + l.getIsbn());
            System.out.println("Categoria: " + (l.getCategoria() != null ? l.getCategoria().getNome() : "Sem Categoria"));
            System.out.println();
        });
    } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private void buscarLivroPorNome (Scanner scanner){
        System.out.println("Digite o nome do livro (mínimo 4 caractéres): ");
        String nomeLivro = scanner.nextLine();

        if(nomeLivro.length() < 4){
            System.out.println("Nome muito curto. Por favor, digite pelo menos 4 caracteres.");
            System.out.println();
            buscarLivroPorNome(scanner);
            return;
        }
        List<Livro> livrosBusca = livroService.findByTituloContaining(nomeLivro);

        if(livrosBusca.isEmpty()){
            System.out.println("Nenhum resultado encontrado, tente buscar por outro livro.");
        } else {
            livrosBusca.forEach(livro -> {
                System.out.println("ID: " + livro.getId());
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Ano de publicação: " + livro.getAnoPub());
                System.out.println("ISBN: " + livro.getIsbn());
                System.out.println("Emprestado: " + livro.isEmprestado());
                System.out.println("Categoria: " + (livro.getCategoria() != null ? livro.getCategoria().getNome() : "Sem Categoria\b"));
            });
        }
    }

    private void adicionarLivro(Scanner scanner) {
        System.out.println("Título do Livro:");
        String titulo = scanner.nextLine();
        System.out.println("Autor do Livro:");
        String autor = scanner.nextLine();
        System.out.println("Ano de Publicação:");
        int anoPub = Integer.parseInt(scanner.nextLine());
        System.out.println("ISBN do Livro:");
        String isbn = scanner.nextLine();

        categoriaController.listarCategorias();
        System.out.println("ID da Categoria: (Caso não encontre a que deseja, informe 0 para adicionar uma nova)");
        Long categoriaId = Long.parseLong(scanner.nextLine());

        Categoria categoria;
        if (categoriaId == 0) {
            categoria = adicionarNovaCategoria(scanner);
        } else {
            categoria = categoriaService.findById(categoriaId);
            if (categoria == null) {
                System.out.println("Categoria não encontrada. Que tal adicionar uma nova?");
                categoria = adicionarNovaCategoria(scanner);
            }
        }

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAnoPub(anoPub);
        livro.setIsbn(isbn);
        livro.setCategoria(categoria);
        livroService.save(livro);
        System.out.println("Livro adicionado com sucesso!");
    }

    private Categoria adicionarNovaCategoria(Scanner scanner) {
        System.out.println("Nome da nova Categoria:");
        String nomeCategoria = scanner.nextLine();
        Categoria novaCategoria = new Categoria(); 
        novaCategoria.setNome(nomeCategoria);
        return categoriaService.save(novaCategoria);
    }

    private void atualizarLivro(Scanner scanner) {
        System.out.println("ID do Livro a ser atualizado:");
        Long id = Long.parseLong(scanner.nextLine());
        Livro livro = livroService.findById(id);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        System.out.println("Novo título do Livro:");
        String titulo = scanner.nextLine();
        System.out.println("Novo autor do Livro:");
        String autor = scanner.nextLine();
        System.out.println("Novo ano de Publicação:");
        int anoPub = Integer.parseInt(scanner.nextLine());
        System.out.println("Novo ISBN do Livro:");
        String isbn = scanner.nextLine();

        categoriaController.listarCategorias();
        System.out.println("ID da Nova Categoria:");
        Long categoriaId = Long.parseLong(scanner.nextLine());

        Categoria categoria = categoriaService.findById(categoriaId);
        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAnoPub(anoPub);
        livro.setIsbn(isbn);
        livro.setCategoria(categoria);
        livroService.updateLivro(id, livro);
        System.out.println("Livro atualizado com sucesso!");
    }

    private void deletarLivro(Scanner scanner) {
        System.out.println("ID do Livro a ser deletado:");
        Long id = Long.parseLong(scanner.nextLine());
        livroService.deleteById(id);
        System.out.println("Livro deletado com sucesso!");
    }
}