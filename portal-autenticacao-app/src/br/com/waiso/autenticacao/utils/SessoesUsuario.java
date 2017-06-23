package br.com.waiso.autenticacao.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex
 *
 */
public class SessoesUsuario {

	private final Map<String, SessaoUsuario> sessoesUsuario = new HashMap<String, SessaoUsuario>();
	
	public synchronized final SessaoUsuario get(String login){
		return sessoesUsuario.get(login);
	}
	
	public synchronized final void add(String login, SessaoUsuario valor){
		sessoesUsuario.put(login, valor);
	}
	
	public synchronized final boolean tem(String login){
		return sessoesUsuario.containsKey(login);
	}
	
	public synchronized final boolean usuarioLogado(String login){
		return tem(Constantes.USUARIO) && get(login) != null;
	}
	
	public synchronized final void remover(String login){
		sessoesUsuario.remove(login);
	}
	
	public synchronized final void expirarSessao(String login){
		if (usuarioLogado(login)) {
			remover(login);
		}
	}
	
	public synchronized final boolean existemSessoesAtivas() {
		return !sessoesUsuario.isEmpty();
	}
	
	public synchronized final boolean valida(String login) {
		if (tem(login)) {
			SessaoUsuario sessao = get(login);
			Calendar agora = GregorianCalendar.getInstance();
			
			return true;
		}
		return false;
	}
}