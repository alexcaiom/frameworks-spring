package br.com.waiso.autenticacao.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.massuda.alexander.autenticacao.dao.IPerfilDAO;
import br.com.massuda.alexander.autenticacao.dao.finder.impl.FinderPerfilImpl;
import br.com.massuda.alexander.autenticacao.dao.impl.PerfilDAOImpl;
import br.com.massuda.alexander.autenticacao.orm.modelo.NivelHierarquico;
import br.com.massuda.alexander.autenticacao.orm.modelo.Perfil;
import br.com.waiso.framework.abstratas.Classe;
import br.com.waiso.framework.json.JSONReturn;

public class PerfilCRUD extends Controlador<JSONReturn> {
	
	public JSONReturn incluir (ServletRequest requisicao, ServletResponse resposta) {
		Perfil perfil = new Perfil();
		
		String nome = getStringDaRequisicao(requisicao, "nome");
		perfil.setNome(nome);
		NivelHierarquico nivelHierarquico = NivelHierarquico.USUARIO;
		String nivel = getStringDaRequisicao(requisicao, "nivel");
		if (Classe.existe(nivel)) {
			nivelHierarquico = NivelHierarquico.valueOf(nivel);
		}
		perfil.setNivel(nivelHierarquico);
		
		IPerfilDAO dao = new PerfilDAOImpl();
		perfil = dao.incluir(perfil);
		
		return SUCESSO(perfil);
	}
	
	public JSONReturn listar (ServletRequest requisicao, ServletResponse resposta) {
		List<Perfil> perfis = new FinderPerfilImpl().listar();
		return SUCESSO(perfis);
	}
	
	public JSONReturn pesquisar (ServletRequest requisicao, ServletResponse resposta) {
		Long id = null;
		String strID = getStringDaRequisicao(requisicao, "id");
		
		try {
			id = Long.parseLong(strID);
		} catch (NumberFormatException nfe) {
			return ERRO(nfe);
		}
		
		Perfil perfil = new FinderPerfilImpl().pesquisar(id);
		return SUCESSO(perfil);
	}
	
	public JSONReturn pesquisarPorNomeComo (ServletRequest requisicao, ServletResponse resposta) {
		String nome = getStringDaRequisicao(requisicao, "nome");
		List<Perfil> perfis = new FinderPerfilImpl().pesquisarPorNomeComo(nome);
		return SUCESSO(perfis);
	}
	
}
