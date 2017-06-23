package br.com.waiso.autenticacao.controller.usuario.profissional;

import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.waiso.autenticacao.controller.Controlador;
import br.com.massuda.alexander.autenticacao.orm.dao.finder.impl.usuario.profissional.FinderCargoImpl;
import br.com.massuda.alexander.autenticacao.orm.dao.finder.impl.usuario.profissional.FinderEmpresaImpl;
import br.com.massuda.alexander.autenticacao.dao.usuario.profissional.impl.ExperienciaProfissionalDAOImpl;
import br.com.massuda.alexander.autenticacao.dao.usuario.profissional.IExperienciaProfissionalDAO;
import br.com.massuda.alexander.autenticacao.orm.modelo.UsuarioNivelVisualizacao;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.profissional.Cargo;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.profissional.Empresa;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.profissional.ExperienciaProfissional;
import br.com.waiso.framework.abstratas.Classe;
import br.com.waiso.framework.json.JSONReturn;

public class ExperienciaProfissionalCRUD extends Controlador<JSONReturn> {

	public ExperienciaProfissionalCRUD() {
		setCamposObrigatorios("idCargo", "idEmpresa");
	}
	
	public JSONReturn incluir (ServletRequest requisicao, ServletResponse resposta) {
		validaCamposObrigatorios(requisicao, resposta);
		ExperienciaProfissional experienciaProfissional = new ExperienciaProfissional();
		Cargo cargo = null;
		if (Classe.existe(getStringDaRequisicao(requisicao, "idCargo"))) {
			String strIdCargo = getStringDaRequisicao(requisicao, "idCargo");
			Long idCargo = Long.parseLong(strIdCargo);
			cargo = new FinderCargoImpl().pesquisar(idCargo);
		}
		experienciaProfissional.setCargo(cargo);
		
		Empresa empresa = null;
		if (Classe.existe(getStringDaRequisicao(requisicao, "idEmpresa"))) {
			String strIdEmpresa = getStringDaRequisicao(requisicao, "idEmpresa");
			Long idEmpresa = Long.parseLong(strIdEmpresa);
			empresa = new FinderEmpresaImpl().pesquisar(idEmpresa);
		}
		experienciaProfissional.setEmpresa(empresa);
		
		Calendar inicio = null;
		if (Classe.existe(getStringDaRequisicao(requisicao, "dataInicio"))) {
			String strDataInicio = getStringDaRequisicao(requisicao, "dataInicio");
			Long dataHoraLong = Long.parseLong(strDataInicio);
			inicio = Calendar.getInstance();
			inicio.setTimeInMillis(dataHoraLong);
		}
		experienciaProfissional.setInicio(inicio);
		
		Calendar fim = null;
		if (Classe.existe(getStringDaRequisicao(requisicao, "dataFim"))) {
			String strDataFim = getStringDaRequisicao(requisicao, "dataFim");
			Long dataHoraLong = Long.parseLong(strDataFim);
			fim = Calendar.getInstance();
			fim.setTimeInMillis(dataHoraLong);
		}
		experienciaProfissional.setFim(fim);
		UsuarioNivelVisualizacao nivelDeAcesso = null;
		experienciaProfissional.setNivelDeAcesso(nivelDeAcesso);
		
		IExperienciaProfissionalDAO dao = new ExperienciaProfissionalDAOImpl();
		experienciaProfissional = dao.incluir(experienciaProfissional);
		
		return SUCESSO(experienciaProfissional);
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
