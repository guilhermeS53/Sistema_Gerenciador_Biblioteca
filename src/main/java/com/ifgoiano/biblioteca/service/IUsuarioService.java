package com.ifgoiano.biblioteca.service; // Definição do pacote onde a classe está registrada

import java.util.List; // Importação da classe List para utilização em Coleções

import com.ifgoiano.biblioteca.model.Usuario; // Importação do modelo Usuario

/*
 * Em linhas gerais, interface que define os métodos para um serviço de gerenciamento de usuários
 * As classes que implementarem esta interface deverão fornecer a lógica para cada método
 */
public interface IUsuarioService {
    
    Usuario save(Usuario usuario); // Salva um novo usuário ou atualiza um existente

    List<Usuario> findAll(); // Retorna uma lista com todos os usuários cadastrados

    Usuario findById(Long id); // Busca um usuário pelo seu ID

    void deleteById(Long id); // Exclui um usuário pelo seu ID

    Usuario authenticate(String telefone); // Autentica um usuário pelo número de telefone
}
