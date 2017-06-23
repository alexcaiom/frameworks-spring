package br.com.waiso.autenticacao.controller.usuario.profissional;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.waiso.autenticacao.controller.Controlador;
import br.com.massuda.alexander.autenticacao.orm.dao.finder.impl.usuario.profissional.FinderEmpresaImpl;
import br.com.massuda.alexander.autenticacao.dao.usuario.profissional.impl.EmpresaDAOImpl;
import br.com.massuda.alexander.autenticacao.dao.usuario.profissional.IEmpresaDAO;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.profissional.Empresa;
import br.com.waiso.framework.json.JSONReturn;

public class EmpresaCRUD extends Controlador<JSONReturn> {

	public JSONReturn incluir (ServletRequest requisicao, ServletResponse resposta) {
		Empresa empresa = new Empresa();
		String nome = getStringDaRequisicao(requisicao, "nome");
		empresa.setNome(nome);
		
		IEmpresaDAO dao = new EmpresaDAOImpl();
		empresa = dao.incluir(empresa);
		
		return SUCESSO(empresa);
	}
	
	public JSONReturn listar (ServletRequest requisicao, ServletResponse resposta) {
		List<Empresa> empresas = new FinderEmpresaImpl().listar();
		return SUCESSO(empresas);
	}
	
	public JSONReturn pesquisar (ServletRequest requisicao, ServletResponse resposta) {
		Long id = null;
		String strID = getStringDaRequisicao(requisicao, "id");
		
		try {
			id = Long.parseLong(strID);
		} catch (NumberFormatException nfe) {
			return ERRO(nfe);
		}
		
		Empresa empresa = new FinderEmpresaImpl().pesquisar(id);
		return SUCESSO(empresa);
	}
	
	public JSONReturn pesquisarPorNomeComo (ServletRequest requisicao, ServletResponse resposta) {
		String nome = getStringDaRequisicao(requisicao, "nome");
		List<Empresa> empresas = new FinderEmpresaImpl().pesquisarPorNomeComo(nome);
		return SUCESSO(empresas);
	}
	
}
