package br.com.waiso.autenticacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.massuda.alexander.autenticacao.orm.modelo.sistema.Sistema;
import br.com.massuda.alexander.persistencia.interfaces.IDAO;
import br.com.massuda.alexander.persistencia.interfaces.IFinder;

@Controller
@RequestMapping("/sistema")
public class SistemaCRUD extends Controlador<Sistema> {
	

	@Autowired
	public void setDao(IDAO<Sistema> dao) {
		this.dao = dao;
	}

	@Autowired
	public void setFinder(IFinder<Long, Sistema> finder) {
		this.finder = finder;
	}
	
	
}
