package br.com.waiso.autenticacao.controller.usuario.academico;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.massuda.alexander.autenticacao.dao.finder.usuario.academico.impl.FinderInstituicaoDeEnsinoImpl;
import br.com.massuda.alexander.autenticacao.dao.usuario.academico.IInstituicaoDeEnsinoDAO;
import br.com.massuda.alexander.autenticacao.dao.usuario.academico.impl.InstituicaoDeEnsinoDAOImpl;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.academico.InstituicaoDeEnsino;
import br.com.waiso.autenticacao.controller.Controlador;
import br.com.waiso.autenticacao.dto.mapeadores.usuario.academico.InstituicaoDeEnsinoMapeador;
import br.com.waiso.autenticacao.dto.usuario.academico.InstituicaoDeEnsinoDTO;
import br.com.waiso.framework.json.JSONReturn;

public class InstituicaoDeEnsinoCRUD extends Controlador<JSONReturn> {

	public InstituicaoDeEnsinoCRUD() {
		setCamposObrigatorios("nome");
	}
	
	public JSONReturn incluir (ServletRequest requisicao, ServletResponse resposta) {
		validaCamposObrigatorios(requisicao, resposta);
		InstituicaoDeEnsino o = new InstituicaoDeEnsino();
		String nome = getStringDaRequisicao(requisicao, "nome");
		o.setNome(nome);
		
		IInstituicaoDeEnsinoDAO dao = new InstituicaoDeEnsinoDAOImpl();
		o = dao.incluir(o);
		
		return SUCESSO(o);
	}
	
	public JSONReturn listar (ServletRequest requisicao, ServletResponse resposta) {
		List<InstituicaoDeEnsino> objs = new FinderInstituicaoDeEnsinoImpl().listar();
		List<InstituicaoDeEnsinoDTO> dtos = InstituicaoDeEnsinoMapeador.from(objs);
		return SUCESSO(dtos, "coordenadasGeograficas");
	}
	
	public JSONReturn pesquisar (ServletRequest requisicao, ServletResponse resposta) {
		Long id = null;
		String strID = getStringDaRequisicao(requisicao, "id");
		
		try {
			id = Long.parseLong(strID);
		} catch (NumberFormatException nfe) {
			return ERRO(nfe);
		}
		
		InstituicaoDeEnsino o = new FinderInstituicaoDeEnsinoImpl().pesquisar(id);
		return SUCESSO(o);
	}
	
	public JSONReturn pesquisarPorNomeComo (ServletRequest requisicao, ServletResponse resposta) {
		String nome = getStringDaRequisicao(requisicao, "nome");
		List<InstituicaoDeEnsino> objs = new FinderInstituicaoDeEnsinoImpl().pesquisarPorNomeComo(nome);
		return SUCESSO(objs);
	}
	
}
