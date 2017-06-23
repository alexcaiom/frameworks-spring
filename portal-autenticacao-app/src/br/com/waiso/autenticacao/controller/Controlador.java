package br.com.waiso.autenticacao.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.waiso.framework.abstratas.Classe;

public class Controlador extends Classe {
	
	@Autowired
	private HttpSession httpSession;

}
