package br.com.waiso.autenticacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.massuda.alexander.autenticacao.orm.modelo.sistema.Funcionalidade;
import br.com.massuda.alexander.persistencia.interfaces.IDAO;
import br.com.massuda.alexander.persistencia.interfaces.IFinder;

@Controller
@RequestMapping("/funcionalidade")
public class FuncionalidadeCRUD extends Controlador<Funcionalidade> {

	@Autowired
	public void setDao(IDAO<Funcionalidade> dao) {
		this.dao = dao;
	}

	@Autowired
	public void setFinder(IFinder<Long, Funcionalidade> finder) {
		this.finder = finder;
	}
	
}
