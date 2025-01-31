package com.ifgoiano.biblioteca.controller; // Definição do pacote onde a classe está registrada

import java.util.List; // Importação da classe List para utilização em Coleções
import java.util.Scanner; // Importação da classe Scanner para leitura de dados do console

import org.springframework.beans.factory.annotation.Autowired; // Importação para injeção de dependência
import org.springframework.stereotype.Controller; // Importação para definição de controlador Spring

import com.ifgoiano.biblioteca.model.Categoria; // Importação do modelo Categoria
import com.ifgoiano.biblioteca.model.CategoriaComLivrosException;
import com.ifgoiano.biblioteca.model.ResourceNotFoundException; // Importação da exceção personalizada
import com.ifgoiano.biblioteca.service.CategoriaService; // Importação do serviço de categoria

@Controller // Anotação que indica que esta classe é um controlador gerenciado pelo Spring
public class CategoriaController {

    @Autowired // Injeção de dependência do serviço de categoria
    private CategoriaService categoriaService;

    // Método principal que gerencia o menu e as interações com o usuário
    public void run(Scanner scanner) {
        while (true) {
            // Exibição do menu ao usuario
            System.out.println();
            System.out.println("Gerenciar Categorias:");
            System.out.println("1. Listar Categorias");
            System.out.println("2. Adicionar Categoria");
            System.out.println("3. Atualizar Categoria");
            System.out.println("4. Deletar Categoria");
            System.out.println("0. Voltar");
            System.out.println();

            // Leitura da entrada do usuario
            String input = scanner.nextLine();
            int opcao = -1;

            try {
                if (input.isEmpty()) {
                    throw new NumberFormatException();
                }
                opcao = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                continue; // Continua o loop caso a entrada seja inválida
            }

            if (opcao == 0)
                break; // Sai do loop e retorna ao menu principal

            // Chama o método apropriado com base na opção selecionada
            switch (opcao) {
                case 1:
                    listarCategorias();
                    break;
                case 2:
                    adicionarCategoria(scanner);
                    break;
                case 3:
                    atualizarCategoria(scanner);
                    break;
                case 4:
                    deletarCategoria(scanner);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // Método para listar todas as categorias
    public void listarCategorias() {
        List<Categoria> categorias = categoriaService.findAll();
        if (categorias.isEmpty()) {
            System.out.println();
            System.out.println("Nenhuma categoria encontrada.");
        } else {
            categorias.forEach(c -> {
                System.out.println("ID: " + c.getId() + " - Nome: " + c.getNome());
            });
        }
    }

    // Método para adicionar uma nova categoria
    private void adicionarCategoria(Scanner scanner) {
        System.out.println();
        System.out.println("Nome da Categoria (Tecle 0 caso queira voltar):");
        String nome = scanner.nextLine();
        if ("0".equals(nome)) {
            return; // Retorna ao menu principal caso usuário queira
        }
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        categoriaService.save(categoria);
        System.out.println();
        System.out.println("Categoria adicionada com sucesso!");
        System.out.println();
    }

    // Método para atualizar uma categoria existente
    private void atualizarCategoria(Scanner scanner) {
        listarCategorias(); // Lista todas as Categorias cadastradas no sistema
        System.out.println("ID da Categoria a ser atualizada (Tecle 0 caso queira voltar):");
        String idStr = scanner.nextLine();
        if (idStr.equals("0")) {
            return; // Volta ao menu principal
        }
        Long id = -1L;
        try {
            if (idStr.isEmpty()) {
                throw new NumberFormatException();
            }
            id = Long.parseLong(idStr);
            Categoria categoria = categoriaService.findById(id);
            System.out.println();
            System.out.println("Novo nome da Categoria:");
            String nome = scanner.nextLine();
            categoria.setNome(nome);
            categoriaService.save(categoria);
            System.out.println("Categoria atualizada com sucesso!");
            System.out.println();
        } catch (NumberFormatException e) {
            System.out.println("ID inválido! Por favor, insira um número válido.");
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.out.println();
        }
    }

    // Método para deletar uma categoria existente
    private void deletarCategoria(Scanner scanner) {
        listarCategorias(); // Lista todas as Categorias cadastradas no sistema
        System.out.println("ID da Categoria a ser deletada (Tecle 0 para voltar):");
        String idStr = scanner.nextLine();
        if (idStr.equals("0")) {
            return; // Volta ao menu principal
        }
        Long id = -1L;
        try {
            if (idStr.isEmpty()) {
                throw new NumberFormatException();
            }
            id = Long.parseLong(idStr);
            categoriaService.deleteById(id);
            System.out.println("Categoria deletada com sucesso!");
            System.out.println();
        } catch (NumberFormatException e) {
            System.out.println("ID inválido! Por favor, insira um número ID válido.");
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.out.println();
        } catch (CategoriaComLivrosException ex) {
            System.out.println(ex.getMessage());
            System.out.println();
        }
    }
}