package com.ifgoiano.biblioteca.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ifgoiano.biblioteca.model.Categoria;
import com.ifgoiano.biblioteca.service.CategoriaService;

@Controller
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    public void run(Scanner scanner) {
        while (true) {
            System.out.println("Gerenciar Categorias:");
            System.out.println("1. Listar Categorias");
            System.out.println("0. Voltar");
            int opcao = Integer.parseInt(scanner.nextLine());

            if (opcao == 0)
                break;

            switch (opcao) {
                case 1:
                    listarCategorias();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void listarCategorias() {
        categoriaService.findAll().forEach(c -> System.out.println(c.getId() + " - " + c.getNome()));
    }
}