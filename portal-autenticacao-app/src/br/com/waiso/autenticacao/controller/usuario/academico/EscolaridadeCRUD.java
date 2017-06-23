package br.com.waiso.autenticacao.controller.usuario.academico;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.massuda.alexander.autenticacao.dao.finder.usuario.academico.impl.FinderEscolaridadeImpl;
import br.com.massuda.alexander.autenticacao.dao.usuario.academico.IEscolaridadeDAO;
import br.com.massuda.alexander.autenticacao.dao.usuario.academico.impl.EscolaridadeDAOImpl;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.academico.Escolaridade;
import br.com.waiso.autenticacao.controller.Controlador;
import br.com.waiso.framework.json.JSONReturn;

public class EscolaridadeCRUD extends Controlador<JSONReturn> {

	public EscolaridadeCRUD() {
		setCamposObrigatorios("nomeDoCurso");
	}
	
	public JSONReturn incluir (ServletRequest requisicao, ServletResponse resposta) {
		validaCamposObrigatorios(requisicao, resposta);
		Escolaridade o = new Escolaridade();
		String nomeDoCurso = getStringDaRequisicao(requisicao, "nomeDoCurso");
		o.setNomeDoCurso(nomeDoCurso);
		
		IEscolaridadeDAO dao = new EscolaridadeDAOImpl();
		o = dao.incluir(o);
		
		return SUCESSO(o);
	}
	
	public JSONReturn listar (ServletRequest requisicao, ServletResponse resposta) {
		List<Escolaridade> objs = new FinderEscolaridadeImpl().listar();
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
		
		Escolaridade o = new FinderEscolaridadeImpl().pesquisar(id);
		return SUCESSO(o);
	}
	
	public JSONReturn pesquisarPorNomeDoCursoComo (ServletRequest requisicao, ServletResponse resposta) {
		String nomeDoCurso = getStringDaRequisicao(requisicao, "nomeDoCurso");
		List<Escolaridade> objs = new FinderEscolaridadeImpl().pesquisarPorNomeComo(nomeDoCurso);
		return SUCESSO(objs);
	}
	
}
