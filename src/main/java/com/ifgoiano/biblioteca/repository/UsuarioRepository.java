package com.ifgoiano.biblioteca.repository; // Definição do pacote onde a interface está registrada

import com.ifgoiano.biblioteca.model.Usuario; // Importação do modelo Usuario

import org.springframework.data.jpa.repository.JpaRepository; // Importação da classe JpaRepository para uso como repositório
import org.springframework.stereotype.Repository; // Importação da anotação Repository para marcar a interface como um repositório Spring

// Criação da interface UsuarioRepository do repositório que estendem JpaRepository
@Repository // Anotação que indica que esta interface é um repositório Spring
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Método para encontrar um usuário pelo seu telefone.
     * 
     * @param telefone o telefone do usuário a ser encontrado.
     * @return o usuário correspondente ao telefone fornecido.
     */
    Usuario findByTelefone(String telefone);

}
