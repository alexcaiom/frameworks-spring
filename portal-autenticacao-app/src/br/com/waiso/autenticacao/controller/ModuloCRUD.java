package br.com.waiso.autenticacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.massuda.alexander.autenticacao.dao.IModuloDAO;
import br.com.massuda.alexander.autenticacao.dao.finder.IFinderModulo;
import br.com.massuda.alexander.autenticacao.orm.modelo.sistema.Modulo;

public class ModuloCRUD extends Controlador {

	@Autowired
	private IModuloDAO dao; 
	@Autowired
	private IFinderModulo finder;
	
	public void incluir (Modulo modulo) {
		dao.incluir(modulo);
	}
	
	public List<Modulo> listar () {
		List<Modulo> modulos = finder.listar();
		return modulos;
	}
	
	public Modulo pesquisar (Long id) {
		Modulo modulo = finder.pesquisar(id);
		return modulo;
	}
	
	public List<Modulo> pesquisarPorNomeComo (String nome) {
		List<Modulo> modulos = finder.pesquisarPorNomeComo(nome);
		return modulos;
	}
	
}
