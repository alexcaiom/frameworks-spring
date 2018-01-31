package br.com.massuda.alexander.autenticacao.orm.bo;

import java.util.List;

import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.Usuario;
import br.com.massuda.alexander.spring.framework.infra.excecoes.Erro;

public interface BOUsuario extends IBO<Long, Usuario> {

	public List<Usuario> listarUsuarios() throws Erro;
	public Usuario incluir(Usuario u)  throws Erro ;
	public List<Usuario> pesquisarPorNome(String nome) throws Erro ;
	public Usuario pesquisarPorLogin(String nome);
	
}
