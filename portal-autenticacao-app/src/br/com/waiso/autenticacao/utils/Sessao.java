package br.com.waiso.autenticacao.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex
 *
 */
public class Sessao {

	private static final Map<String, Object> parametros = new HashMap<String, Object>();
	
	public synchronized static final Object get(String nome){
		return parametros.get(nome);
	}
	
	public synchronized static final void add(String chave, Object valor){
		parametros.put(chave, valor);
	}
	
	public synchronized static final boolean temValor(String chave){
		return parametros.containsKey(chave);
	}
		
	public synchronized static final void remover(String login){
		parametros.remove(login);
	}
}