package br.com.waiso.autenticacao.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.waiso.autenticacao.controller.Controlador;
import br.com.waiso.autenticacao.utils.Constantes;
import br.com.waiso.autenticacao.utils.SessaoUsuario;
import br.com.waiso.autenticacao.utils.SessoesUsuario;
import br.com.waiso.framework.GerenciadorDeInstanciasDeClasse;
import br.com.waiso.framework.abstratas.Classe;
import br.com.waiso.framework.exceptions.ErroUsuario;
import br.com.waiso.framework.json.Consequence;
import br.com.waiso.framework.json.JSONReturn;
import br.com.waiso.framework.view.Servlet;
import br.com.massuda.alexander.persistencia.jdbc.utils.GerenciadorConexaoJDBC;

/**
 * Servlet implementation class ServletAcoes
 */
public class Acoes extends Servlet<JSONReturn> {
	private static final long serialVersionUID = 1L;
	PrintWriter out = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Acoes() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processar(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processar(request, response);
	}

	private void processar(HttpServletRequest requisicao, HttpServletResponse resposta) throws IOException {
		resposta.setCharacterEncoding("UTF-8");
		verificaSessoesDeUsuariosLogados(requisicao);
		JSONReturn retorno = null;
		String nomeDaClasseAserInvocada = requisicao.getParameter("classe");
		Object webClass = null;
		if (nomeDaClasseAserInvocada != null && !nomeDaClasseAserInvocada.isEmpty()) {
			try {
				nomeDaClasseAserInvocada = getNomeDaClasseASerInvocadaPorNomeEmPadrao(nomeDaClasseAserInvocada);
				webClass = GerenciadorDeInstanciasDeClasse.getClasse(nomeDaClasseAserInvocada);
				String metodoASerInvocado = requisicao.getParameter("metodo");
				retorno = executarController(requisicao, resposta, webClass, metodoASerInvocado);
			} catch (ErroUsuario e) {
				e.printStackTrace();
				if (e.getCause() instanceof InvocationTargetException) {
					Throwable excecaoOriginal = e.getCause().getCause();
					retorno = JSONReturn.newInstance(Consequence.ERRO).message(excecaoOriginal.getMessage());
				} else {
					retorno = JSONReturn.newInstance(Consequence.ERRO).message(e.getMessage());
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
				retorno = JSONReturn.newInstance(Consequence.ERRO).message(e.getMessage());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				retorno = JSONReturn.newInstance(Consequence.ERRO).message(e.getMessage());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				retorno = JSONReturn.newInstance(Consequence.ERRO).message("Classe nao encontrada!");
			} catch (Exception e) {
				e.printStackTrace();
				retorno = JSONReturn.newInstance(Consequence.ERRO).message("Classe nao encontrada!");
			}
		}
		fecharConexao();
		String respostaASerImpressa = Classe.existe(retorno) ? retorno.serialize() : "Nenhuma acao foi informada!";
		getWriter(out, resposta).print(respostaASerImpressa);
	}

	private void fecharConexao() {
		try {
			GerenciadorConexaoJDBC.fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String getNomeDaClasseASerInvocadaPorNomeEmPadrao(String nomeDaClasseAserInvocada) {
		String nome = nomeDaClasseAserInvocada + Constantes.CLASSE_CONTROLADORA_SUFIXO;
		
		if (Classe.existe(Constantes.controladoresCadastrados)) {
			for (Class controlador : Constantes.controladoresCadastrados) {
				if (controlador.getSimpleName().equals(nome)) {
					return controlador.getName(); 
				}
			}
		}
		
		return nome;
	}

	public JSONReturn executarController (ServletRequest request, ServletResponse response, Object webClass, String invoke) throws Exception{
		JSONReturn retorno = null;
		try {
			retorno = super.executeWebClass(request, response, webClass, invoke);
		} catch (Exception e) {
			throw e;
		}
		return retorno;
	}

	private void verificaSessoesDeUsuariosLogados(HttpServletRequest requisicao) {
		HttpSession sessao = requisicao.getSession();
		SessoesUsuario sessoesAtivas = (SessoesUsuario) sessao.getAttribute(Constantes.USUARIO);
		if (Classe.existe(sessoesAtivas)) {
			if (!sessoesAtivas.existemSessoesAtivas()) {
				fecharConexao();
			}
		}
	}

}