package com.ifgoiano.biblioteca.service; // Definição do pacote onde a classe está registrada

import java.util.List; // Importação da classe List para utilização em Coleções

import com.ifgoiano.biblioteca.model.Categoria; // Importação do modelo Categoria

/*
 * Em linhas gerais, interface que define os métodos para um serviço de gerenciamento de categorias.
 * As classes que implementarem esta interface deverão fornecer a lógica para cada método.
 */
public interface ICategoriaService {
    List<Categoria> findAll(); // Retorna uma lista com todas as categorias cadastradas

    Categoria findById(Long id); // Busca categoria pelo ID

    Categoria save(Categoria categoria); // Método para salvar ou atualizar uma categoria

    void deleteById(Long id); // Método para deletar uma categoria pelo seu ID

    Categoria findByNome(String nome); // Método para encontrar uma categoria pelo seu nome
}
