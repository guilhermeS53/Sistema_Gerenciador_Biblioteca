package com.ifgoiano.biblioteca.controller; // Definição do pacote onde a classe está registrada

import java.util.List; // Importação da classe List para utilização em Coleções
import java.util.Scanner;// Importação da classe Scanner para leitura de dados do console

import org.springframework.beans.factory.annotation.Autowired; // Importação para injeção de dependência

import org.springframework.stereotype.Controller; // Importação para definição de controlador Spring

import com.ifgoiano.biblioteca.model.Categoria; // Importação do modelo Categoria
import com.ifgoiano.biblioteca.model.Livro; // Importação do modelo Livro
import com.ifgoiano.biblioteca.model.LivroComEmprestimosException; // Importação de exceção personalizada para relação Emprestimo e Usuario
import com.ifgoiano.biblioteca.model.ResourceNotFoundException; // Importação da exceção personalizada
import com.ifgoiano.biblioteca.service.CategoriaService; // Importação do serviço Categoria
import com.ifgoiano.biblioteca.service.LivroService; // Importação do serviço Livro

// Controlador para interação com os livros da biblioteca
@Controller // Anotação que indica que esta classe é um controlador gerenciado pelo Spring
public class LivroController {

    @Autowired // Injeção de dependência do serviço Livro
    private LivroService livroService;

    @Autowired // Injeção de dependência do serviço Categoria
    private CategoriaService categoriaService;

    @Autowired // Injeção de dependência do controlador Categoria
    private CategoriaController categoriaController;

    // Método para exibir o menu de gerenciamento de livros
    public void run(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("Gerenciar Livros:");
            System.out.println("1. Listar Livros");
            System.out.println("2. Adicionar Livro");
            System.out.println("3. Atualizar Livro");
            System.out.println("4. Deletar Livro");
            System.out.println("5. Buscar Livro por ID");
            System.out.println("6. Buscar Livro por Nome");
            System.out.println("0. Voltar");
            System.out.println();

            String input = scanner.nextLine(); // Leitura da opção do usuário
            int opcao = -1;

            try {
                if (input.isEmpty()) {
                    throw new NumberFormatException();
                }
                opcao = Integer.parseInt(input); // Conversão da entrada para inteiro
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                continue;
            }

            if (opcao == 0) {
                break;
            }

            // Chamada dos métodos conforme a opção selecionada
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

    // Método para listar todos os livros
    private void listarLivros() {
        livroService.findAll().forEach(l -> {
            System.out.println();
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

    // Método para buscar um livro por ID
    private void buscarLivroPorId(Scanner scanner) {
        System.out.println("Digite o ID do Livro (Tecle 0 para voltar):");
        String input = scanner.nextLine();
        Long id = -1L;
    
        try {
            if (input.isEmpty()) {
                throw new NumberFormatException();
            }
            id = Long.parseLong(input); // Conversão da entrada para Long
        } catch (NumberFormatException e) {
            System.out.println("ID inválido! Por favor, insira um número válido.");
            return;
        }
    
        if (id == 0) {
            return;
        }
    
        try {
            Livro livro = livroService.findById(id); // Busca do livro pelo ID
            if (livro != null) {
                System.out.println();
                System.out.println("ID: " + livro.getId());
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Ano de Publicação: " + livro.getAnoPub());
                System.out.println("ISBN: " + livro.getIsbn());
                System.out.println("Categoria: " + (livro.getCategoria() != null ? livro.getCategoria().getNome() : "Sem Categoria"));
                System.out.println();
            }
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage()); // Exibir mensagem de erro ao usuário
            System.out.println();
        }
    }

    // Método para buscar livros por nome
    private void buscarLivroPorNome(Scanner scanner) {
        System.out.println();
        System.out.println("Digite o nome do Livro (mínimo 4 caracteres, ou tecle 0 para voltar): ");
        String nomeLivro = scanner.nextLine();
        if ("0".equals(nomeLivro)) {
            return;
        }

        if (nomeLivro.length() < 4) {
            System.out.println();
            System.out.println("Nome muito curto. Por favor, digite pelo menos 4 caracteres.");
            System.out.println();
            buscarLivroPorNome(scanner); // Recursão para nova tentativa
            return;
        }
        List<Livro> livrosBusca = livroService.findByTituloContaining(nomeLivro); // Busca de livros pelo título

        if (livrosBusca.isEmpty()) {
            System.out.println();
            System.out.println("Nenhum resultado encontrado, tente buscar por outro livro.");
        } else {
            livrosBusca.forEach(livro -> {
                System.out.println();
                System.out.println("ID: " + livro.getId());
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Ano de publicação: " + livro.getAnoPub());
                System.out.println("ISBN: " + livro.getIsbn());
                System.out.println("Emprestado: " + livro.isEmprestado());
                System.out.println("Categoria: " + (livro.getCategoria() != null ? livro.getCategoria().getNome() : "Sem Categoria"));
                System.out.println();
            });
        }
    }

    // Método para adicionar um novo livro
    private void adicionarLivro(Scanner scanner) {
        System.out.println();
        System.out.println("Título do Livro (ou 0 para voltar):");
        String titulo = scanner.nextLine();
        if ("0".equals(titulo)) {
            return;
        }
        System.out.println("Autor do Livro:");
        String autor = scanner.nextLine();
        if ("0".equals(autor)) {
            return;
        }
        System.out.println("Ano de Publicação:");
        String inputAnoPub = scanner.nextLine();
        int anoPub = -1;

        try {
            if (inputAnoPub.isEmpty()) {
                throw new NumberFormatException();
            }
            anoPub = Integer.parseInt(inputAnoPub); // Conversão da entrada para inteiro
        } catch (NumberFormatException e) {
            System.out.println("Ano de publicação inválido! Por favor, insira um número válido.");
            return;
        }

        if (anoPub == 0) {
            return;
        }

        System.out.println("ISBN do Livro:");
        String isbn = scanner.nextLine();
        if ("0".equals(isbn)) {
            return;
        }

        categoriaController.listarCategorias(); // Listar categorias disponíveis
        System.out.println("ID da Categoria: (Caso não encontre a que deseja, informe 0 para adicionar uma nova categoria)");
        String inputCategoriaId = scanner.nextLine();
        Long categoriaId = -1L;

        try {
            if (inputCategoriaId.isEmpty()) {
                throw new NumberFormatException();
            }
            categoriaId = Long.parseLong(inputCategoriaId); // Conversão da entrada para Long
        } catch (NumberFormatException e) {
            System.out.println("ID de categoria inválido! Por favor, insira um número válido.");
            return;
        }

        Categoria categoria = null;
        if (categoriaId == 0) {
            categoria = adicionarNovaCategoria(scanner); // Adicionar nova categoria se ID for 0
        } else {
            categoria = categoriaService.findById(categoriaId);
            if (categoria == null) {
                System.out.println();
                System.out.println("Categoria não encontrada. Que tal adicionar uma nova?");
                categoria = adicionarNovaCategoria(scanner); // Adicionar nova categoria se não encontrada
            }
        }

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAnoPub(anoPub);
        livro.setIsbn(isbn);
        livro.setCategoria(categoria);
        livroService.save(livro); // Salvar novo livro
        System.out.println("Livro adicionado com sucesso!");
        System.out.println();
    }

    // Método para adicionar uma nova categoria
    private Categoria adicionarNovaCategoria(Scanner scanner) {
        while (true) {
            System.out.println("Nome da nova Categoria (Tecle 0 caso queira voltar):");
            String nomeCategoria = scanner.nextLine();
            if ("0".equals(nomeCategoria)) {
                return null;
            }

            if (categoriaService.findByNome(nomeCategoria) != null) {
                System.out.println("Categoria já existente. Tente novamente.");
            } else {
                Categoria novaCategoria = new Categoria();
                novaCategoria.setNome(nomeCategoria);
                return categoriaService.save(novaCategoria); // Salvar nova categoria
            }
        }
    }

    // Método para atualizar um livro existente
    private void atualizarLivro(Scanner scanner) {
        listarLivros();
        System.out.println("ID do Livro a ser atualizado:");
        String input = scanner.nextLine();
        Long id = -1L;
    
        try {
            if (input.isEmpty()) {
                throw new NumberFormatException();
            }
            id = Long.parseLong(input); // Conversão da entrada para Long
        } catch (NumberFormatException e) {
            System.out.println("ID inválido! Por favor, insira um número válido.");
            return;
        }
    
        if (id == 0) {
            return;
        }
    
        try {
            Livro livro = livroService.findById(id);
            System.out.println("Novo título do Livro:");
            String titulo = scanner.nextLine();
            System.out.println("Novo autor do Livro:");
            String autor = scanner.nextLine();
            System.out.println("Novo ano de Publicação:");
            String inputAnoPub = scanner.nextLine();
            int anoPub = -1;
    
            try {
                if (inputAnoPub.isEmpty()) {
                    throw new NumberFormatException();
                }
                anoPub = Integer.parseInt(inputAnoPub); // Conversão da entrada para inteiro
            } catch (NumberFormatException e) {
                System.out.println("Ano de publicação inválido! Por favor, insira um número válido.");
                return;
            }
    
            System.out.println("Novo ISBN do Livro:");
            String isbn = scanner.nextLine();
    
            categoriaController.listarCategorias(); // Listar categorias disponíveis
            System.out.println("ID da Nova Categoria:");
            String inputCategoriaId = scanner.nextLine();
            Long categoriaId = -1L;
    
            try {
                if (inputCategoriaId.isEmpty()) {
                    throw new NumberFormatException();
                }
                categoriaId = Long.parseLong(inputCategoriaId); // Conversão da entrada para Long
            } catch (NumberFormatException e) {
                System.out.println("ID de categoria inválido! Por favor, insira um número válido.");
                return;
            }
    
            Categoria categoria = categoriaService.findById(categoriaId);
            if (categoria == null) {
                System.out.println("Categoria não encontrada.");
                System.out.println();
                return;
            }
    
            livro.setTitulo(titulo);
            livro.setAutor(autor);
            livro.setAnoPub(anoPub);
            livro.setIsbn(isbn);
            livro.setCategoria(categoria);
            livroService.updateLivro(id, livro); // Atualizar livro
            System.out.println("Livro atualizado com sucesso!");
            System.out.println();
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    // Método para deletar um livro
    private void deletarLivro(Scanner scanner) {
        listarLivros();
        System.out.println("ID do Livro a ser deletado:");
        String input = scanner.nextLine();
        Long id = -1L;
    
        try {
            if (input.isEmpty()) {
                throw new NumberFormatException();
            }
            id = Long.parseLong(input); // Conversão da entrada para Long
        } catch (NumberFormatException e) {
            System.out.println("ID inválido! Por favor, insira um número válido.");
            return;
        }
    
        if (id == 0) {
            return;
        }
    
        try {
            livroService.deleteById(id); // Deletar livro pelo ID
            System.out.println("Livro deletado com sucesso!");
            System.out.println();
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.out.println();
        } catch (LivroComEmprestimosException ex) {
            System.out.println(ex.getMessage());
            System.out.println();
        }
    }
}