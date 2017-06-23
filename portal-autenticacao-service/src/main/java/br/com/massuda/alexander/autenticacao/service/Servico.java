package br.com.massuda.alexander.autenticacao.service;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;

import br.com.waiso.framework.abstratas.Classe;

public class Servico extends Classe {

	WebTarget target = null;
	Client cliente = Client.create();
	Response resposta = null;
	
}
