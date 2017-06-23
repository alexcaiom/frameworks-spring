package br.com.massuda.alexander.autenticacao.utils;

import org.springframework.http.MediaType;

import br.com.massuda.alexander.persistencia.jdbc.utils.ConstantesPersistencia;
import br.com.waiso.framework.json.JSONReturn;

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

	public static final String PACOTE_CONTROLLER = "br.com.waiso.autenticacao.controller";
	
	
	public static final String CLASSE_CONTROLADORA_SUFIXO = "CRUD";

}