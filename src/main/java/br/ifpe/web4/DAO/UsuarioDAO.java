package br.ifpe.web4.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifpe.web4.entity.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, String>{

	Usuario findByLoginAndSenha(String login, String senha);

}
