package br.com.waiso.autenticacao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.waiso.framework.json.JSONReturn;

public class ListaClassesEMetodos extends Controlador<JSONReturn> {

	public JSONReturn listar (ServletRequest requisicao, ServletResponse resposta) {
		List<String> lista = new ArrayList<>();
		
		Package pacoteController = Package.getPackage("br.com.waiso.autenticacao.controller");
		
		
		return SUCESSO(lista);
	}
	
	public static void main(String[] args) {
		List<String> lista = new ArrayList<>();
		
		Package pacoteController = Package.getPackage("br.com.waiso.autenticacao.controller");
	}
	
}
