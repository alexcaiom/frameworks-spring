package br.com.waiso.autenticacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.massuda.alexander.autenticacao.dao.finder.IFinderPermissao;
import br.com.massuda.alexander.autenticacao.dao.finder.usuario.IFinderUsuario;
import br.com.massuda.alexander.autenticacao.orm.modelo.Permissao;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.Usuario;

@Controller
@RequestMapping("/permissao")
public class PermissaoCRUD extends Controlador<Permissao> {
	
	@Autowired
	private IFinderPermissao finder;
	@Autowired
	private IFinderUsuario finderUsuario;
	
	public Permissao pesquisarPorUsuario (String login) {
		Usuario usuario = finderUsuario.pesquisarPorLogin(login);
		
		Permissao permissao = finder.pesquisarPorUsuario(usuario.getId());
		return permissao;
	}
	
}
