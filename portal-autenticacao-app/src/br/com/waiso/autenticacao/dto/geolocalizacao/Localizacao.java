package br.com.waiso.autenticacao.dto.geolocalizacao;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name="location")
public class Localizacao implements Serializable {

	@JsonProperty("lat")
	private String latitude;
	@JsonProperty("lng")
	private String longitude;
	
	@Override
	public String toString() {
		return "Localizacao [lat=" + latitude + ", lng=" + longitude + "]";
	}
	
}
