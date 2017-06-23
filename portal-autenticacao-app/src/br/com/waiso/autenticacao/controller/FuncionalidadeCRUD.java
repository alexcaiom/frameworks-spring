package br.com.waiso.autenticacao.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.massuda.alexander.autenticacao.dao.IFuncionalidadeDAO;
import br.com.massuda.alexander.autenticacao.dao.finder.impl.FinderFuncionalidadeImpl;
import br.com.massuda.alexander.autenticacao.dao.impl.FuncionalidadeDAOImpl;
import br.com.massuda.alexander.autenticacao.orm.modelo.sistema.Funcionalidade;
import br.com.waiso.framework.json.JSONReturn;

public class FuncionalidadeCRUD extends Controlador<JSONReturn> {

	public JSONReturn incluir (ServletRequest requisicao, ServletResponse resposta) {
		Funcionalidade funcionalidade = new Funcionalidade();
		String nome = getStringDaRequisicao(requisicao, "nome");
		funcionalidade.setNome(nome);
		String acesso = getStringDaRequisicao(requisicao, "acesso");
		funcionalidade.setAcesso(acesso);
		
		IFuncionalidadeDAO dao = new FuncionalidadeDAOImpl();
		funcionalidade = dao.incluir(funcionalidade);
		
		return SUCESSO(funcionalidade);
	}
	
	public JSONReturn listar (ServletRequest requisicao, ServletResponse resposta) {
		List<Funcionalidade> funcionalidades = new FinderFuncionalidadeImpl().listar();
		return SUCESSO(funcionalidades);
	}
	
	public JSONReturn pesquisar (ServletRequest requisicao, ServletResponse resposta) {
		Long id = null;
		String strID = getStringDaRequisicao(requisicao, "id");
		
		try {
			id = Long.parseLong(strID);
		} catch (NumberFormatException nfe) {
			return ERRO(nfe);
		}
		
		Funcionalidade funcionalidade = new FinderFuncionalidadeImpl().pesquisar(id);
		return SUCESSO(funcionalidade);
	}
	
	public JSONReturn pesquisarPorNomeComo (ServletRequest requisicao, ServletResponse resposta) {
		String nome = getStringDaRequisicao(requisicao, "nome");
		
		/*if (Classe.naoExiste(getStringDaRequisicao(requisicao, "idUsuario"))) {
			throw new ErroUsuario("Campo obrigatorio nao informado: idUsuario");
		}
		String strIdUsuario = getStringDaRequisicao(requisicao, "idUsuario");
		Long idUsuario = Long.parseLong(strIdUsuario);
		Usuario usuario = new FinderUsuarioImpl().pesquisar(idUsuario);
		List<Funcionalidade> funcionalidade = new FinderFuncionalidadeImpl().pesquisarPorAcesso(usuario, nome);*/
		List<Funcionalidade> funcionalidade = new FinderFuncionalidadeImpl().pesquisarPorNomeComo(nome);
		return SUCESSO(funcionalidade);
	}
	
}
