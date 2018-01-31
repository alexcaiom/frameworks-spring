package br.com.massuda.alexander.spring.framework.infra.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import br.com.waiso.framework.abstratas.Classe;

@EnableAsync(proxyTargetClass=true)
public abstract class Controlador<T> extends Classe {
	
	@Autowired
	protected HttpSession httpSession;
	
}
