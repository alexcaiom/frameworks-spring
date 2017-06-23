package br.com.waiso.autenticacao.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.massuda.alexander.autenticacao.dao.IModuloDAO;
import br.com.massuda.alexander.autenticacao.dao.finder.impl.FinderModuloImpl;
import br.com.massuda.alexander.autenticacao.dao.impl.ModuloDAOImpl;
import br.com.massuda.alexander.autenticacao.orm.modelo.sistema.Modulo;
import br.com.massuda.alexander.persistencia.jdbc.utils.TipoOperacao;
import br.com.waiso.framework.json.JSONReturn;

public class ModuloCRUD extends Controlador<JSONReturn> {

	public JSONReturn incluir (ServletRequest requisicao, ServletResponse resposta) {
		Modulo modulo = new Modulo();
		String nome = getStringDaRequisicao(requisicao, "nome");
		modulo.setNome(nome);
		
		IModuloDAO dao = new ModuloDAOImpl();
		modulo = dao.incluir(modulo);
		
		return SUCESSO(modulo);
	}
	
	public JSONReturn listar (ServletRequest requisicao, ServletResponse resposta) {
		List<Modulo> modulos = new FinderModuloImpl(TipoOperacao.PESQUISA_COM_MULTIPLAS_TABELAS).listar();
		return SUCESSO(modulos);
	}
	
	public JSONReturn pesquisar (ServletRequest requisicao, ServletResponse resposta) {
		Long id = null;
		String strID = getStringDaRequisicao(requisicao, "id");
		
		try {
			id = Long.parseLong(strID);
		} catch (NumberFormatException nfe) {
			return ERRO(nfe);
		}
		
		Modulo modulo = new FinderModuloImpl().pesquisar(id);
		return SUCESSO(modulo);
	}
	
	public JSONReturn pesquisarPorNomeComo (ServletRequest requisicao, ServletResponse resposta) {
		String nome = getStringDaRequisicao(requisicao, "nome");
		List<Modulo> modulos = new FinderModuloImpl().pesquisarPorNomeComo(nome);
		return SUCESSO(modulos);
	}
	
}
