package com.ifgoiano.biblioteca; // Definição do pacote onde a classe está registrada

import java.io.InputStream; // Importação para manipulação de streams de entrada
import java.util.List; // Importação da classe List para utilização em Coleções

import org.springframework.beans.factory.annotation.Autowired; // Importação para injeção de dependência
import org.springframework.boot.CommandLineRunner; // Importação da interface CommandLineRunner
import org.springframework.core.annotation.Order; // Importação para definir a ordem de execução
import org.springframework.core.io.ClassPathResource; // Importação para acesso a recursos do ClassPath
import org.springframework.stereotype.Component; // Importação para definição de componente Spring

import com.fasterxml.jackson.core.type.TypeReference; // Importação para referência de tipo do Jackson
import com.fasterxml.jackson.databind.ObjectMapper; // Importação para mapeamento de JSON
import com.ifgoiano.biblioteca.model.Categoria; // Importação do modelo Categoria
import com.ifgoiano.biblioteca.service.CategoriaService; // Importação do serviço Categoria

// Definição do componente DataLoader com prioridade de execução
@Component
@Order(1) // Prioriza a execução para carregar previamente algumas categorias no sistema
public class DataLoader implements CommandLineRunner {
    @Autowired // Injeção de dependência do serviço Categoria
    private CategoriaService categoriaService;

    @Override
    public void run(String... args) throws Exception {
        // Verifica se o banco de dados de categorias está vazio e carrega categorias do JSON se necessário
        if (categoriaService.findAll().isEmpty()) {
            loadCategoriasFromJson();
        }
    }

    // Método para carregar categorias a partir de um arquivo JSON
    private void loadCategoriasFromJson() {
        try {
            ObjectMapper mapper = new ObjectMapper(); // Inicializa o mapeador de objetos
            InputStream inputStream = new ClassPathResource("categorias.json").getInputStream(); // Carrega o arquivo JSON do classpath
            // Lê e mapeia o conteúdo do arquivo JSON para uma lista de objetos Categoria
            List<Categoria> categorias = mapper.readValue(inputStream, new TypeReference<List<Categoria>>() {});
            // Salva cada categoria no banco de dados
            categorias.forEach(categoriaService::save);
            System.out.println("Categorias carregadas com sucesso!");
            System.out.println();
        } catch (Exception e) {
            System.out.println("Erro ao carregar categorias: " + e.getMessage());
            System.out.println();
        }
    }
}