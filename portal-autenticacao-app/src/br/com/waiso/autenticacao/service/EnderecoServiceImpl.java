package br.com.waiso.autenticacao.service;

import javax.ws.rs.core.MediaType;

import br.com.waiso.autenticacao.dto.EnderecoDTO;
import br.com.massuda.alexander.autenticacao.orm.modelo.Endereco;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class EnderecoServiceImpl extends Service {

	private static final String MAPS_GOOGLEAPIS = "http://maps.googleapis.com/maps/api/geocode/json";

	public EnderecoDTO getEndereco (Endereco endereco) {
		 Client cliente = Client.create();
		 WebResource webRecurso = cliente.resource(MAPS_GOOGLEAPIS);
		 
		 EnderecoDTO enderecoDTO = webRecurso
				 .queryParam("latlng", endereco.getCoordenadasGeograficas().getLatitude()+","+endereco.getCoordenadasGeograficas().getLongitude())
				 .accept(MediaType.APPLICATION_JSON_TYPE).get(EnderecoDTO.class);
		 
		 
		 return enderecoDTO;
	}
	
}
