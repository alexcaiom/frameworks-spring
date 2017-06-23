package br.com.waiso.autenticacao.controller.usuario.academico;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.massuda.alexander.autenticacao.dao.finder.usuario.academico.impl.FinderAreaDeEstudoImpl;
import br.com.massuda.alexander.autenticacao.dao.usuario.academico.IAreaDeEstudoDAO;
import br.com.massuda.alexander.autenticacao.dao.usuario.academico.impl.AreaDeEstudoDAOImpl;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.academico.AreaDeEstudo;
import br.com.waiso.autenticacao.controller.Controlador;
import br.com.waiso.framework.json.JSONReturn;

public class AreaDeEstudoCRUD extends Controlador<JSONReturn> {

	public AreaDeEstudoCRUD() {
		setCamposObrigatorios("nome");
	}
	
	public JSONReturn incluir (ServletRequest requisicao, ServletResponse resposta) {
		validaCamposObrigatorios(requisicao, resposta);
		AreaDeEstudo o = new AreaDeEstudo();
		String nome = getStringDaRequisicao(requisicao, "nome");
		o.setNome(nome);
		
		IAreaDeEstudoDAO dao = new AreaDeEstudoDAOImpl();
		o = dao.incluir(o);
		
		return SUCESSO(o);
	}
	
	public JSONReturn listar (ServletRequest requisicao, ServletResponse resposta) {
		List<AreaDeEstudo> objs = new FinderAreaDeEstudoImpl().listar();
		return SUCESSO(objs);
	}
	
	public JSONReturn pesquisar (ServletRequest requisicao, ServletResponse resposta) {
		Long id = null;
		String strID = getStringDaRequisicao(requisicao, "id");
		
		try {
			id = Long.parseLong(strID);
		} catch (NumberFormatException nfe) {
			return ERRO(nfe);
		}
		
		AreaDeEstudo o = new FinderAreaDeEstudoImpl().pesquisar(id);
		return SUCESSO(o);
	}
	
	public JSONReturn pesquisarPorNomeComo (ServletRequest requisicao, ServletResponse resposta) {
		String nome = getStringDaRequisicao(requisicao, "nome");
		List<AreaDeEstudo> objs = new FinderAreaDeEstudoImpl().pesquisarPorNomeComo(nome);
		return SUCESSO(objs);
	}
	
}
