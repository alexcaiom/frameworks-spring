package br.com.waiso.autenticacao.utils;

import org.springframework.http.MediaType;

import br.com.waiso.autenticacao.controller.Controlador;
import br.com.waiso.autenticacao.controller.FuncionalidadeCRUD;
import br.com.waiso.autenticacao.controller.ModuloCRUD;
import br.com.waiso.autenticacao.controller.PerfilCRUD;
import br.com.waiso.autenticacao.controller.PermissaoCRUD;
import br.com.waiso.autenticacao.controller.SistemaCRUD;
import br.com.waiso.autenticacao.controller.UsuarioCRUD;
import br.com.waiso.autenticacao.controller.usuario.academico.AreaDeEstudoCRUD;
import br.com.waiso.autenticacao.controller.usuario.academico.AtividadeEscolarCRUD;
import br.com.waiso.autenticacao.controller.usuario.academico.EscolaridadeCRUD;
import br.com.waiso.autenticacao.controller.usuario.academico.InstituicaoDeEnsinoCRUD;
import br.com.waiso.autenticacao.controller.usuario.profissional.CargoCRUD;
import br.com.waiso.autenticacao.controller.usuario.profissional.EmpresaCRUD;
import br.com.waiso.autenticacao.controller.usuario.profissional.ExperienciaProfissionalCRUD;
import br.com.waiso.framework.json.JSONReturn;
import br.com.massuda.alexander.persistencia.jdbc.utils.ConstantesPersistencia;

/**
 * @author Alex
 *
 */
public class Constantes {

	public static final String USUARIO = "usuario";
	
	/**
	 * CONSTANTES DE MENSAGEM
	 */
	public static final String MENSAGEM_SUCESSO = "A {operacao} do {item} foi realizada com sucesso!";
	public static final String MENSAGEM_FALHA = "A {operacao} do {item} falhou! Tente mais tarde!";

	/**
	 * Horario Comercial
	 */
	public static final int HORARIO_MINIMO_HORA = 7;
	public static final int HORARIO_MAXIMO_HORA = 18;
	
	public static final String getMensagemSucesso(Class qualVO, String operacao){
		return MENSAGEM_SUCESSO.replace("{operacao}", operacao).replace("{item}", qualVO.getSimpleName());
	}

	public static final String getMensagemFalha(Class qualVO, String operacao){
		return MENSAGEM_SUCESSO.replace("{operacao}", operacao).replace("{item}", qualVO.getSimpleName());
	}
	
	
	public static final String  CODIFICACAO  = "utf-8";
	public static final String  REST_PRODUCES  = MediaType.APPLICATION_JSON_VALUE;//+";charset="+CODIFICACAO;

	public static final String BANCO_DE_DADOS_LOCAL = "alexcaiom.com.br";
	public static final String BANCO_DE_DADOS_CONEXAO_USUARIO = "alexcaio";
	public static final String BANCO_DE_DADOS_CONEXAO_SENHA = "lifE2sg@3ood";
	public static final String BANCO_DE_DADOS_NOME = "alexcaio_"+ConstantesPersistencia.BD_CONEXAO_NOME_BD;
//	public static final String BANCO_DE_DADOS_LOCAL = "localhost";
//	public static final String BANCO_DE_DADOS_CONEXAO_USUARIO = "root";
//	public static final String BANCO_DE_DADOS_CONEXAO_SENHA = "lifesgood";
//	public static final String BANCO_DE_DADOS_NOME = ConstantesPersistencia.BD_CONEXAO_NOME_BD;

	public static final String PACOTE_CONTROLLER = "br.com.waiso.autenticacao.controller";
	
	
	public static final Class[] controladoresCadastrados = {
		FuncionalidadeCRUD.class,
		ModuloCRUD.class,
		PerfilCRUD.class,
		PermissaoCRUD.class,
		SistemaCRUD.class,
		UsuarioCRUD.class,
		
		AreaDeEstudoCRUD.class,
		AtividadeEscolarCRUD.class,
		EscolaridadeCRUD.class,
		InstituicaoDeEnsinoCRUD.class,
		
		CargoCRUD.class,
		EmpresaCRUD.class,
		ExperienciaProfissionalCRUD.class
	};

	public static final String CLASSE_CONTROLADORA_SUFIXO = "CRUD";

}