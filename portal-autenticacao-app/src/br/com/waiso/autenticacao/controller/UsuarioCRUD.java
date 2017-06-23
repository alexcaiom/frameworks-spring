package br.com.waiso.autenticacao.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.massuda.alexander.autenticacao.excecoes.ErroNegocio;
import br.com.massuda.alexander.autenticacao.orm.bo.BOUsuario;
import br.com.massuda.alexander.autenticacao.orm.bo.BOUsuarioImpl;
import br.com.massuda.alexander.autenticacao.orm.modelo.Foto;
import br.com.massuda.alexander.autenticacao.orm.modelo.Perfil;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.Usuario;
import br.com.waiso.autenticacao.dto.UsuarioDTO;
import br.com.waiso.autenticacao.dto.mapeadores.UsuarioMapeador;
import br.com.waiso.autenticacao.utils.Constantes;
import br.com.waiso.autenticacao.utils.SessaoUsuario;
import br.com.waiso.autenticacao.utils.SessoesUsuario;
import br.com.waiso.framework.abstratas.Classe;
import br.com.waiso.framework.exceptions.ErroUsuario;
import br.com.waiso.framework.json.JSONReturn;

@Controller
@RequestMapping("/usuario")
public class UsuarioCRUD extends Controlador {
	
	@Autowired
	private BOUsuario bo;

	@RequestMapping(method=RequestMethod.POST)
	public void incluir(Usuario usuario) {
		if (existe(usuario.getFotos()) && !usuario.getFotos().isEmpty()) {
			try {
				String raiz = System.getProperty("catalina.home");
				File pastaArquivosTemporarios = new File(raiz + "/temp/");
				if (!pastaArquivosTemporarios.exists()) {
					pastaArquivosTemporarios.mkdirs();
				}
				try{
					String storage = "/storage/";
					File caminhoArquivo = new File(raiz+storage);
					if (!caminhoArquivo.exists()) {
						caminhoArquivo.mkdirs();
					}
					caminhoArquivo = caminhoArquivo + "/";

					while (i.hasNext()) {
						FileItem item = i.next();
						if (!item.isFormField()) {
							String nomeCampo = item.getFieldName();
							String nomeArquivo = item.getName();
							String tipoConteudo = item.getContentType();
							boolean estaEmMemoria = item.isInMemory();
							long tamanhoEmBytes = item.getSize();

							if (nomeArquivo.lastIndexOf("/") >= 0) {
								arquivo = new File(caminhoArquivo + nomeArquivo.substring(nomeArquivo.lastIndexOf("\\")));
							} else {
								arquivo = new File(caminhoArquivo + nomeArquivo.substring(nomeArquivo.lastIndexOf("\\")+1));
							}

							item.write(arquivo);
							Foto foto = new Foto();
							foto.setCaminho(arquivo.getAbsolutePath());
							foto.setData(GregorianCalendar.getInstance());
							foto.setDescricao(arquivo.getName());
							fotos.add(foto);
						} else {
						}
					}
				} catch (Exception e) {
					getWriter(null, resposta).println("Erro: "+e.getMessage());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		bo.incluir(usuario);
	}
	
	public JSONReturn autenticar(ServletRequest requisicao, ServletResponse resposta) {
		String login = getStringDaRequisicao(requisicao, "login");
		String senha = getStringDaRequisicao(requisicao, "senha");
		Usuario usuario = null;
		try {
			usuario = bo.autentica(login, senha);
			
			adicionarSessaoDeUsuario(requisicao, usuario);
		} catch (ErroNegocio e) {
			return ATENCAO(e.getErro());
		}
		return SUCESSO(usuario, "dataDeNascimento", "dataDeCriacao");
	}
	
	public JSONReturn usuarioEstaLogado(ServletRequest requisicao, ServletResponse resposta) {
		String login = getStringDaRequisicao(requisicao, "login");
		try {
			SessoesUsuario sessoes = getSessoesUsuario(requisicao);
			boolean usuarioLogado = sessoes.tem(login);
			if (usuarioLogado) {
				boolean valida = sessoes.valida(login);
				return SUCESSO(valida);
			}
		} catch (ErroNegocio e) {
			return ATENCAO(e.getErro());
		}
		return SUCESSO(false);
	}
	
	private SessoesUsuario adicionarSessaoDeUsuario(ServletRequest requisicao, Usuario usuario) {
		SessoesUsuario sessoes = getSessoesUsuario(requisicao);
		
		SessaoUsuario sessaoDeUsuario = new SessaoUsuario(usuario.getLogin(), usuario, GregorianCalendar.getInstance());
		sessoes.add(usuario.getLogin(), sessaoDeUsuario);
		return sessoes;
	}

	private SessoesUsuario getSessoesUsuario(ServletRequest requisicao) {
		HttpSession sessao = ((HttpServletRequest)requisicao).getSession();
		SessoesUsuario sessoes = (SessoesUsuario) sessao.getAttribute(Constantes.USUARIO);
		
		if (Classe.naoExiste(sessoes)) {
			sessoes = new SessoesUsuario();
			sessao.setAttribute(Constantes.USUARIO, sessoes);
		}
		return sessoes;
	}

	public JSONReturn listar(ServletRequest requisicao, ServletResponse resposta) {
		List<Usuario> usuarios = null;
		try {
			 usuarios = bo.listarUsuarios();
		} catch(Exception e) {
			throw new ErroUsuario(e.getCause().getMessage());
		}
		List<UsuarioDTO> usuariosDTO = UsuarioMapeador.from(usuarios);
		return SUCESSO(usuariosDTO, "dataDeNascimento", "dataDeCriacao"); 
	}
	
	public JSONReturn pesquisarPorNome(ServletRequest requisicao, ServletResponse resposta) {
		String nome = getStringDaRequisicao(requisicao, "nome");
		List<Usuario> usuarios = bo.pesquisarPorNome(nome);
		List<UsuarioDTO> usuariosDTO = UsuarioMapeador.from(usuarios);
		return SUCESSO(usuariosDTO, "dataDeNascimento", "dataDeCriacao"); 
	}
	
}
