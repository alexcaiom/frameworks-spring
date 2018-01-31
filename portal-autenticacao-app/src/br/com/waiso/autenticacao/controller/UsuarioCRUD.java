package br.com.waiso.autenticacao.controller;

import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.massuda.alexander.autenticacao.orm.bo.BOUsuario;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.Usuario;
import br.com.massuda.alexander.persistencia.interfaces.IDAO;
import br.com.massuda.alexander.persistencia.interfaces.IFinder;
import br.com.massuda.alexander.spring.framework.infra.excecoes.ErroNegocio;
import br.com.waiso.autenticacao.utils.Constantes;
import br.com.waiso.autenticacao.utils.SessaoUsuario;
import br.com.waiso.autenticacao.utils.SessoesUsuario;
import br.com.waiso.framework.abstratas.Classe;

@Controller
@RequestMapping("/usuario")
public class UsuarioCRUD extends Controlador<Usuario> {
	
	@Autowired
	private 

	@Override
	@RequestMapping(method=RequestMethod.POST)
	public void incluir(Usuario usuario) {
		/*if (existe(usuario.getFotos()) && !usuario.getFotos().isEmpty()) {
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
		}*/
		
		bo.incluir(usuario);
	}
	
	@RequestMapping(value="/autenticar", method=RequestMethod.POST)
	public ResponseEntity<? extends Object> autenticar(String login, String senha) {
		Usuario usuario = null;
		try {
			usuario = bo.autentica(login, senha);
			adicionarSessaoDeUsuario(usuario);
		} catch (ErroNegocio e) {
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(usuario, HttpStatus.OK);
	}

	@RequestMapping(value="/usuarioEstaLogado", method=RequestMethod.POST)
	public ResponseEntity<? extends Object> usuarioEstaLogado(String login) {
		try {
			SessoesUsuario sessoes = getSessoesUsuario();
			boolean usuarioLogado = sessoes.tem(login);
			if (usuarioLogado) {
				Boolean valida = sessoes.valida(login);
				return new ResponseEntity<Boolean>(valida, HttpStatus.OK);
			}
		} catch (ErroNegocio e) {
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(false, HttpStatus.OK);
	}
	
	private SessoesUsuario adicionarSessaoDeUsuario(Usuario usuario) {
		SessoesUsuario sessoes = getSessoesUsuario();
		
		SessaoUsuario sessaoDeUsuario = new SessaoUsuario(usuario.getLogin(), usuario, GregorianCalendar.getInstance());
		sessoes.add(usuario.getLogin(), sessaoDeUsuario);
		return sessoes;
	}

	private SessoesUsuario getSessoesUsuario() {
		SessoesUsuario sessoes = (SessoesUsuario) httpSession.getAttribute(Constantes.USUARIO);
		
		if (Classe.naoExiste(sessoes)) {
			sessoes = new SessoesUsuario();
			httpSession.setAttribute(Constantes.USUARIO, sessoes);
		}
		return sessoes;
	}
	


	@Autowired
	public void setDao(IDAO<Usuario> dao) {
		this.dao = dao;
	}

	@Autowired
	public void setFinder(IFinder<Long, Usuario> finder) {
		this.finder = finder;
	}
	

}
