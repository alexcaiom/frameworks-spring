package br.com.waiso.autenticacao.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.massuda.alexander.persistencia.interfaces.IDAO;
import br.com.massuda.alexander.persistencia.interfaces.IFinder;
import br.com.waiso.framework.abstratas.Classe;

@EnableAsync(proxyTargetClass=true)
public abstract class Controlador<T> extends Classe {
	
	@Autowired
	protected HttpSession httpSession;
	
	protected IDAO<T> dao;
	protected IFinder<Long, T> finder;
	
	@RequestMapping(method=RequestMethod.POST)
	public void incluir (T o) {
		dao.incluir(o);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public void editar (T o) {
		dao.editar(o);
	}
	
	@ResponseBody
	@RequestMapping
	public List<T> listar () {
		List<T> objs = finder.listar();
		return objs;
	}
	

	@ResponseBody
	@RequestMapping("/pesquisarPorNomeComo/{nome}")
	public List<T> pesquisar (String nome) {
		List<T> obs = finder.pesquisarPorNomeComo(nome);
		return obs;
	}
	
	@ResponseBody
	@RequestMapping("/pesquisarPorId/{id}")
	public T pesquisar (Long id) {
		T o = finder.pesquisar(id);
		return o;
	}
	

	@RequestMapping(method=RequestMethod.DELETE)
	public void excluir (T o) {
		dao.excluir(o);
	}

}
