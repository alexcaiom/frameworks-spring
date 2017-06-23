package br.com.waiso.autenticacao.controller.usuario.profissional;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.waiso.autenticacao.controller.Controlador;
import br.com.massuda.alexander.autenticacao.orm.dao.finder.impl.usuario.profissional.FinderCargoImpl;
import br.com.massuda.alexander.autenticacao.dao.usuario.profissional.impl.CargoDAOImpl;
import br.com.massuda.alexander.autenticacao.dao.usuario.profissional.ICargoDAO;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.profissional.Cargo;
import br.com.waiso.framework.json.JSONReturn;

public class CargoCRUD extends Controlador<JSONReturn> {

	public CargoCRUD() {
		setCamposObrigatorios("nome");
	}
	
	public JSONReturn incluir (ServletRequest requisicao, ServletResponse resposta) {
		validaCamposObrigatorios(requisicao, resposta);
		Cargo cargo = new Cargo();
		String nome = getStringDaRequisicao(requisicao, "nome");
		cargo.setNome(nome);
		
		ICargoDAO dao = new CargoDAOImpl();
		cargo = dao.incluir(cargo);
		
		return SUCESSO(cargo);
	}
	
	public JSONReturn listar (ServletRequest requisicao, ServletResponse resposta) {
		List<Cargo> cargos = new FinderCargoImpl().listar();
		return SUCESSO(cargos);
	}
	
	public JSONReturn pesquisar (ServletRequest requisicao, ServletResponse resposta) {
		Long id = null;
		String strID = getStringDaRequisicao(requisicao, "id");
		
		try {
			id = Long.parseLong(strID);
		} catch (NumberFormatException nfe) {
			return ERRO(nfe);
		}
		
		Cargo cargo = new FinderCargoImpl().pesquisar(id);
		return SUCESSO(cargo);
	}
	
	public JSONReturn pesquisarPorNomeComo (ServletRequest requisicao, ServletResponse resposta) {
		String nome = getStringDaRequisicao(requisicao, "nome");
		List<Cargo> cargos = new FinderCargoImpl().pesquisarPorNomeComo(nome);
		return SUCESSO(cargos);
	}
	
}
