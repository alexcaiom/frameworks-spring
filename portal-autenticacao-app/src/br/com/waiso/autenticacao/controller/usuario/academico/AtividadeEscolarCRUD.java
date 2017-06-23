package br.com.waiso.autenticacao.controller.usuario.academico;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.massuda.alexander.autenticacao.dao.finder.usuario.academico.impl.FinderAtividadeEscolarImpl;
import br.com.massuda.alexander.autenticacao.dao.usuario.academico.IAtividadeEscolarDAO;
import br.com.massuda.alexander.autenticacao.dao.usuario.academico.impl.AtividadeEscolarDAOImpl;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.academico.AtividadeEscolar;
import br.com.waiso.autenticacao.controller.Controlador;
import br.com.waiso.framework.json.JSONReturn;

public class AtividadeEscolarCRUD extends Controlador<JSONReturn> {

	public AtividadeEscolarCRUD() {
		setCamposObrigatorios("nome" , "descricao");
	}
	
	public JSONReturn incluir (ServletRequest requisicao, ServletResponse resposta) {
		validaCamposObrigatorios(requisicao, resposta);
		AtividadeEscolar o = new AtividadeEscolar();
		String nome = getStringDaRequisicao(requisicao, "nome");
		o.setNome(nome);
		String descricao = getStringDaRequisicao(requisicao, "descricao");
		o.setDescricao(descricao);
		
		IAtividadeEscolarDAO dao = new AtividadeEscolarDAOImpl();
		o = dao.incluir(o);
		
		return SUCESSO(o);
	}
	
	public JSONReturn listar (ServletRequest requisicao, ServletResponse resposta) {
		List<AtividadeEscolar> objs = new FinderAtividadeEscolarImpl().listar();
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
		
		AtividadeEscolar o = new FinderAtividadeEscolarImpl().pesquisar(id);
		return SUCESSO(o);
	}
	
	public JSONReturn pesquisarPorNomeComo (ServletRequest requisicao, ServletResponse resposta) {
		String nome = getStringDaRequisicao(requisicao, "nome");
		List<AtividadeEscolar> objs = new FinderAtividadeEscolarImpl().pesquisarPorNomeComo(nome);
		return SUCESSO(objs);
	}
	
}
