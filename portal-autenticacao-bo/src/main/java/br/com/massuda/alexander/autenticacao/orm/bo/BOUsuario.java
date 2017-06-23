package br.com.massuda.alexander.autenticacao.orm.bo;

import java.util.List;

import br.com.massuda.alexander.autenticacao.excecoes.Erro;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.Usuario;

public interface BOUsuario extends IBO<Usuario> {

	public Usuario autentica(String login, String senha) throws Erro;
	public List<Usuario> listarUsuarios() throws Erro;
	public Usuario incluir(Usuario u)  throws Erro ;
	public List<Usuario> pesquisarPorNome(String nome) throws Erro ;
	
}
