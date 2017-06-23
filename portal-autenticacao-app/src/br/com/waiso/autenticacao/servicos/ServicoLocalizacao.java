package br.com.waiso.autenticacao.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import br.com.waiso.autenticacao.dto.geolocalizacao.EnderecoGoogleMapsAPI;
import br.com.waiso.autenticacao.dto.geolocalizacao.RespostaGoogleMapsAPI;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

public class ServicoLocalizacao extends Servico {
	
	private static ServicoLocalizacao instancia = null;
	
	private  ServicoLocalizacao() {}
	
	public List<EnderecoGoogleMapsAPI> getEnderecoPorLogradouro(String logradouro) {
		ClientResponse resposta = null;
		List<EnderecoGoogleMapsAPI> resultado = new ArrayList<EnderecoGoogleMapsAPI>();
		try {
			WebResource recurso = cliente.resource("http://maps.googleapis.com/maps/api/geocode/json?address?="+ logradouro);
			
			resposta = recurso.accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
			RespostaGoogleMapsAPI respostaGoogleMapsAPI = resposta.getEntity(new GenericType<RespostaGoogleMapsAPI>(){});
			
			if (existe(respostaGoogleMapsAPI) && existe(respostaGoogleMapsAPI.getResults()) && !respostaGoogleMapsAPI.getResults().isEmpty() ) {
				return respostaGoogleMapsAPI.getResults();
			}
			
			System.out.println(resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public String getEnderecoPorCoordenadasGeograficas(double latitude, double longitude) {
		ClientResponse resposta = null;
		String resultado = null;
		try {
			WebResource recurso = cliente.resource("http://maps.googleapis.com/maps/api/geocode/json?latlng="+ latitude +","+ longitude);
			
			resposta = recurso.accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
			RespostaGoogleMapsAPI respostaGoogleMapsAPI = resposta.getEntity(new GenericType<RespostaGoogleMapsAPI>(){});
			
			if (existe(respostaGoogleMapsAPI) && existe(respostaGoogleMapsAPI.getResults()) && !respostaGoogleMapsAPI.getResults().isEmpty() ) {
				EnderecoGoogleMapsAPI enderecoGoogleMapsAPI = respostaGoogleMapsAPI.getResults().get(0);
				return enderecoGoogleMapsAPI.enderecoFormatado;
			}
			
			System.out.println(resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	/*public static void main(String[] args) {
		Response resposta = null;
		
		try {
			Client cliente = Client.create();
			WebResource recurso = cliente.resource("http://maps.googleapis.com/maps/api/geocode/json?latlng=-23.5740406,-46.6234089");
			
			String resultado = recurso.accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);
			
			System.out.println(resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public static ServicoLocalizacao getInstance() {
		if (naoExiste(instancia)) {
			instancia = new ServicoLocalizacao();
		}
		return instancia;
	}
	
	
	/*public static void main(String[] args) {
		Client cliente = Client.create();
		
		WebResource recurso = cliente.resource("http://maps.googleapis.com/maps/api/geocode/json?latlng=-23.5740406,-46.6234089");
		
		String resposta = recurso.accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);
		
		System.out.println(resposta);
	}*/
}
