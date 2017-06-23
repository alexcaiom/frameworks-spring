package br.com.massuda.alexander.autenticacao.dao.impl;

import org.springframework.stereotype.Component;

import br.com.massuda.alexander.autenticacao.dao.IModuloDAO;
import br.com.massuda.alexander.autenticacao.orm.modelo.sistema.Modulo;
import br.com.waiso.framework.exceptions.ErroUsuario;

@Component
public class ModuloDAOImpl extends DAO<Modulo>
							implements IModuloDAO {

	public ModuloDAOImpl() {
		super(Modulo.class);
	}
	
	public Modulo incluir(Modulo o) throws ErroUsuario {
		return super.incluir(o);
	}

	public void editar(Modulo o) throws ErroUsuario {
		super.editar(o);
	}

	public void excluir(Modulo o) throws ErroUsuario {
		super.excluir(o);
	}

}
