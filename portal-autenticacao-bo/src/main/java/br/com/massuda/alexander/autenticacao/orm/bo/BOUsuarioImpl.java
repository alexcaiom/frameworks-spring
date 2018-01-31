package br.com.massuda.alexander.autenticacao.orm.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.massuda.alexander.autenticacao.dao.IUsuarioDAO;
import br.com.massuda.alexander.autenticacao.dao.finder.usuario.IFinderUsuario;
import br.com.massuda.alexander.autenticacao.orm.modelo.NivelHierarquico;
import br.com.massuda.alexander.autenticacao.orm.modelo.RespostaUsuarioAutenticacao;
import br.com.massuda.alexander.autenticacao.orm.modelo.RespostaUsuarioCadastro;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.Usuario;
import br.com.massuda.alexander.spring.framework.infra.excecoes.Erro;
import br.com.massuda.alexander.spring.framework.infra.excecoes.ErroNegocio;
import br.com.massuda.alexander.spring.framework.infra.excecoes.SysErr;
import br.com.massuda.alexander.spring.framework.infra.utils.UtilsData;

/**
 * @author Alex
 *
 */
@Component
public class BOUsuarioImpl extends BO
						implements BOUsuario, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4058157133186995621L;

	@Autowired
	private IUsuarioDAO dao/* = new UsuarioDAOImpl();*/;
	
	@Autowired
	private IFinderUsuario finder/* = new FinderUsuarioImpl()*/;
	
	
	public List<Usuario> listarUsuarios(){
		log("Listando "+getNomeEntidade()+"s");
		List<Usuario> usuarios = null;
		
		usuarios = finder.listar();
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
		
		return usuarios;
	}
	
	public List<String> listarUsuariosString(){
		List<String> usuarios = new ArrayList<String>();
		for(Usuario u: listarUsuarios()){
			usuarios.add(u.toString());
		}
		return usuarios;
	}

	public Usuario incluir(Usuario usuario) throws SysErr, ErroNegocio {
		log("Inserindo "+getNomeEntidade());
		boolean usuarioJaExiste = finder.pesquisarPorLogin(usuario.getLogin()) != null;
		if(usuarioJaExiste){
			throw new ErroNegocio(RespostaUsuarioCadastro.USUARIO_DUPLICADO.getMensagem());
		} else {
			usuario.setDataDeCriacao(Calendar.getInstance());

			Usuario uCadastrado = dao.incluir(usuario);
			boolean ocorreuAlgumErro = uCadastrado == null;
			if (ocorreuAlgumErro){
				throw new ErroNegocio(RespostaUsuarioCadastro.ERRO_AO_CRIAR_USUARIO.getMensagem());
			} else {
//				SessaoUsuario sessao = new SessaoUsuario(uCadastrado.getLogin(), uCadastrado, Calendar.getInstance());
//				SessoesUsuario.add(uCadastrado.getLogin(), sessao);
				return uCadastrado;
			}
		}
		
	}

	public void alterar(Usuario usuario) throws SysErr {
		log("Alterando "+getNomeEntidade());
		dao.editar(usuario);		
	}
	
	public Object liberarUsuario(String login) {
		Usuario u = pesquisarPorLogin(login);
		u.setContadorSenhaInvalida(0);
		u.setStatus(RespostaUsuarioAutenticacao.SUCESSO);
		alterar(u);
		return "Liberado";
	}

	public void excluir(Usuario usuario) throws SysErr {
		log("Excluindo "+getNomeEntidade());
		dao.excluir(usuario);		
	}
	
	public Usuario pesquisar(Long id){
		Usuario usuario = finder.pesquisar(id);
		System.out.println(usuario);
		return usuario;
	}
	
	public Usuario pesquisarPorLogin(String login){
		return finder.pesquisarPorLogin(login);
	}
	
	public List<Usuario> pesquisarPorLoginComo(String login) {
		List<Usuario> usuarios = finder.pesquisarPorLoginComo(login);
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}

		return usuarios;
	}
	
	/*public Boolean usuarioEstaLogado(String login, String senha)  {
		Object o = null;
		try{
			o = autentica(login, senha);
		} catch (ErroNegocio e){
			if (e.getErro().equals(RespostaUsuarioAutenticacao.USUARIO_INEXISTENTE.getMensagem())) {
				return false;
			}
		}
		Usuario u = null;
		if (existe(o) && o instanceof Usuario) {
			u = (Usuario) o;
		}
		boolean usuarioEstaLogado = false;
		if (existe(u)) {
			usuarioEstaLogado = Sessao.usuarioLogado(u.getLogin());
		}
		return usuarioEstaLogado;
	}*/

	/*public void deslogar(String login) {
		Sessao.expirarSessao(login);
	}*/
	
	private void trataSenhaInvalidaDoUsuario(Usuario usuarioRecuperadoDoBD) {
		int numeroDeChancesRestantes = 3 - usuarioRecuperadoDoBD.getContadorSenhaInvalida();
		boolean deveBloquearUsuario = numeroDeChancesRestantes == 0;
		if (deveBloquearUsuario) {
			usuarioRecuperadoDoBD.setStatus(RespostaUsuarioAutenticacao.USUARIO_BLOQUEADO);				
		}
		dao.editar(usuarioRecuperadoDoBD);
	}
	
	private boolean ehAdm(Usuario usuarioRecuperadoDoBD) {
		return usuarioRecuperadoDoBD.getPerfil().getNivel() == NivelHierarquico.OPERADOR;
	}

	public List<Usuario> pesquisarPorNome(String nome) throws Erro {
		return finder.pesquisarPorNomeComo(nome);
	}
}