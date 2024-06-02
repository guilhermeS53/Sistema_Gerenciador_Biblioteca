package com.ifgoiano.biblioteca.service; // Definição do pacote onde a classe está registrada

import java.util.List; // Importação da classe List para utilização em Coleções

// Importações de bibliotecas do Spring para injeção de dependência e gestão de serviços
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

// Importação dos modelos de dados da Biblioteca
import com.ifgoiano.biblioteca.model.Emprestimo;
import com.ifgoiano.biblioteca.model.ResourceNotFoundException;
import com.ifgoiano.biblioteca.model.Usuario;
import com.ifgoiano.biblioteca.model.UsuarioComEmprestimosException;

// Importação dos repositórios para acessos aos Dados (DAO)
import com.ifgoiano.biblioteca.repository.EmprestimoRepository;
import com.ifgoiano.biblioteca.repository.UsuarioRepository;

// Criação do serviço de Usuário que encapsula a lógica de negócios
@Service // Indica que esta é uma classe de serviço do Spring
public class UsuarioService implements IUsuarioService {

    // Injeção de dependência dos repositórios (Spring se encarrega de criar as instâncias)
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    // Métodos que implementam a interface IUsuarioService
    // Salva um novo usuário ou atualiza um existente.

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    // Retorna uma lista com todos os usuários cadastrados

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
    // Busca um usuário pelo seu ID

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para o id: " + id));
    }
    // Exclui um usuário pelo seu ID

    @Override
    public void deleteById(Long id) {
        // Verifica se o usuário possui empréstimos ativos
        List<Emprestimo> emprestimos = emprestimoRepository.findByUsuarioId(id);
        if (!emprestimos.isEmpty()) {
            throw new UsuarioComEmprestimosException("Não é possível excluir o usuário, pois já houveram registros de empréstimo na Biblioteca.");
        }
        // Se não houver empréstimos, exclui o usuário
        usuarioRepository.deleteById(id);
    }
    
    @Override
    public Usuario authenticate(String telefone) {
        // Autentica um usuário pelo número de telefone
        return usuarioRepository.findByTelefone(telefone);
    }
}
