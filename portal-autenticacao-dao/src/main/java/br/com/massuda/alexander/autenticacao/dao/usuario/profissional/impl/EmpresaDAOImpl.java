package br.com.massuda.alexander.autenticacao.dao.usuario.profissional.impl;

import org.springframework.stereotype.Component;

import br.com.massuda.alexander.autenticacao.dao.impl.DAO;
import br.com.massuda.alexander.autenticacao.dao.usuario.profissional.IEmpresaDAO;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.profissional.Empresa;

@Component
public class EmpresaDAOImpl extends DAO<Empresa> implements IEmpresaDAO {

	public EmpresaDAOImpl() {
		super(Empresa.class);
	}

}
