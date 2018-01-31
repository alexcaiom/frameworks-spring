package br.com.waiso.autenticacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.massuda.alexander.autenticacao.orm.modelo.Perfil;
import br.com.massuda.alexander.persistencia.interfaces.IDAO;
import br.com.massuda.alexander.persistencia.interfaces.IFinder;

@Controller
@RequestMapping("/perfil")
public class PerfilCRUD extends Controlador<Perfil> {
	


	@Autowired
	public void setDao(IDAO<Perfil> dao) {
		this.dao = dao;
	}

	@Autowired
	public void setFinder(IFinder<Long, Perfil> finder) {
		this.finder = finder;
	}
	
	
}
