package br.com.waiso.autenticacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.massuda.alexander.autenticacao.dao.ISistemaDAO;
import br.com.massuda.alexander.autenticacao.dao.finder.IFinderSistema;
import br.com.massuda.alexander.autenticacao.orm.modelo.sistema.Sistema;

@Controller
@RequestMapping("/sistema")
public class SistemaCRUD extends Controlador {

	@Autowired
	private ISistemaDAO dao;
	@Autowired
	private IFinderSistema finder;
	
	@RequestMapping(method=RequestMethod.POST)
	public void incluir (Sistema sistema) {
		dao.incluir(sistema);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public void editar (Sistema sistema) {
		dao.editar(sistema);
	}

	@ResponseBody
	@RequestMapping
	public List<Sistema> listar () {
		List<Sistema> sistemas = finder.listar();
		return sistemas;
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}")
	public Sistema pesquisar (Long id) {
		Sistema sistema = finder.pesquisar(id);
		return sistema;
	}
	
	@ResponseBody
	@RequestMapping(value="/{nome}")
	public List<Sistema> pesquisarPorNomeComo (String nome) {
		List<Sistema> sistema = finder.pesquisarPorNomeComo(nome);
		return sistema;
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public void excluir (Sistema sistema) {
		dao.excluir(sistema);
	}
	
}
