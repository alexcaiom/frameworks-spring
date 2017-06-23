package br.com.waiso.autenticacao.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.massuda.alexander.autenticacao.dao.IPermissaoDAO;
import br.com.massuda.alexander.autenticacao.dao.finder.impl.FinderFuncionalidadeImpl;
import br.com.massuda.alexander.autenticacao.dao.finder.impl.FinderPermissaoImpl;
import br.com.massuda.alexander.autenticacao.dao.finder.impl.usuario.FinderUsuarioImpl;
import br.com.massuda.alexander.autenticacao.dao.impl.PermissaoDAOImpl;
import br.com.massuda.alexander.autenticacao.orm.modelo.Permissao;
import br.com.massuda.alexander.autenticacao.orm.modelo.sistema.Funcionalidade;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.Usuario;
import br.com.waiso.framework.abstratas.Classe;
import br.com.waiso.framework.json.JSONReturn;

public class PermissaoCRUD extends Controlador<JSONReturn> {
	
	public PermissaoCRUD() {
		setCamposObrigatorios(
			"idUsuario",
			"idFuncionalidade");
	}
	
	public JSONReturn incluir (ServletRequest requisicao, ServletResponse resposta) {
		validaCamposObrigatorios(requisicao, resposta);
		Permissao permissao = new Permissao();
		
		Usuario usuario = null;
		String strIDUsuario = getStringDaRequisicao(requisicao, "idUsuario");
		Long idUsuario = Long.parseLong(strIDUsuario);
		usuario = new FinderUsuarioImpl().pesquisar(idUsuario);
		permissao.setUsuario(usuario);
		
		Funcionalidade funcionalidade = null;
		String strIdFuncionalidade = getStringDaRequisicao(requisicao, "idFuncionalidade");
		Long idFuncionalidade = Long.parseLong(strIdFuncionalidade);
		funcionalidade = new FinderFuncionalidadeImpl().pesquisar(idFuncionalidade);
		permissao.setFuncionalidade(funcionalidade);
		
		Boolean acessoEscrita = false;
		if (Classe.existe(getStringDaRequisicao(requisicao, "acessoEscrita"))) {
			String strAcessoEscrita = getStringDaRequisicao(requisicao, "acessoEscrita");
			acessoEscrita = Boolean.parseBoolean(strAcessoEscrita);
		}
		permissao.setAcessoEscrita(acessoEscrita);
		Boolean acessoGravacao = false;
		if (Classe.existe(getStringDaRequisicao(requisicao, "acessoGravacao"))) {
			String strAcessoGravavao = getStringDaRequisicao(requisicao, "acessoGravacao");
			acessoEscrita = Boolean.parseBoolean(strAcessoGravavao);
		}
		permissao.setAcessoGravacao(acessoGravacao);
		
		IPermissaoDAO dao = new PermissaoDAOImpl();
		permissao = dao.incluir(permissao);
		
		return SUCESSO(permissao);
	}
	
	public JSONReturn listar (ServletRequest requisicao, ServletResponse resposta) {
		List<Permissao> permissoes = new FinderPermissaoImpl().listar();
		return SUCESSO(permissoes, "usuario.dataDeCriacao", "usuario.dataDeNascimento");
	}
	
	public JSONReturn pesquisarPorID (ServletRequest requisicao, ServletResponse resposta) {
		Long id = null;
		String strID = getStringDaRequisicao(requisicao, "id");
		
		try {
			id = Long.parseLong(strID);
		} catch (NumberFormatException nfe) {
			return ERRO(nfe);
		}
		
		Permissao permissao = new FinderPermissaoImpl().pesquisar(id);
		return SUCESSO(permissao, "usuario.dataDeCriacao", "usuario.dataDeNascimento");
	}
	
	public JSONReturn pesquisarPorUsuario (ServletRequest requisicao, ServletResponse resposta) {
		String login = getStringDaRequisicao(requisicao, "login");
		Usuario usuario = new FinderUsuarioImpl().pesquisarPorLogin(login);
		
		Permissao permissao = new FinderPermissaoImpl().pesquisarPorUsuario(usuario.getId());
		return SUCESSO(permissao, "usuario.dataDeCriacao", "usuario.dataDeNascimento");
	}
	
}
