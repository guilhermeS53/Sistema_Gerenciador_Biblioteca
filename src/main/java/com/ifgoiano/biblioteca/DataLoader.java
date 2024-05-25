package com.ifgoiano.biblioteca;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifgoiano.biblioteca.model.Categoria;
import com.ifgoiano.biblioteca.service.CategoriaService;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private CategoriaService categoriaService;

    @Override
    public void run(String... args) throws Exception {
        if (categoriaService.findAll().isEmpty()) {
            loadCategoriasFromJson();
        }
    }

    private void loadCategoriasFromJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new ClassPathResource("categorias.json").getInputStream();
            List<Categoria> categorias = mapper.readValue(inputStream, new TypeReference<List<Categoria>>() {
            });
            categorias.forEach(categoriaService::save);
            System.out.println("Categorias carregadas com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao carregar categorias: " + e.getMessage());
        }
    }
}
