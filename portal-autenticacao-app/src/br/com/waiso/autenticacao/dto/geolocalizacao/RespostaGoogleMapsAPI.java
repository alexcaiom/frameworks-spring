package br.com.waiso.autenticacao.dto.geolocalizacao;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name="")
public class RespostaGoogleMapsAPI {
	
	@JsonProperty("results")
	private List<EnderecoGoogleMapsAPI> results;
	private String status;
	
	public List<EnderecoGoogleMapsAPI> getResults() {
		return results;
	}
	public void setResults(List<EnderecoGoogleMapsAPI> results) {
		this.results = results;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "RespostaGoogleMapsAPI [results=" + results + ", status="
				+ status + "]";
	}
	
}
