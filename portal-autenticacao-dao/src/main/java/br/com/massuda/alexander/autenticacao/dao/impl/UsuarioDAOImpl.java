package br.com.massuda.alexander.autenticacao.dao.impl;

import org.springframework.stereotype.Component;

import br.com.massuda.alexander.autenticacao.dao.IUsuarioDAO;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.Usuario;
import br.com.waiso.framework.exceptions.ErroUsuario;

@Component
public class UsuarioDAOImpl extends DAO<Usuario>
							implements IUsuarioDAO {

	public UsuarioDAOImpl() {
		super(Usuario.class);
	}
	
	public Usuario incluir(Usuario o) throws ErroUsuario {
		return super.incluir(o);
	}

	public void editar(Usuario o) throws ErroUsuario {
		super.editar(o);
	}

	public void excluir(Usuario o) throws ErroUsuario {
		super.excluir(o);
	}

}
