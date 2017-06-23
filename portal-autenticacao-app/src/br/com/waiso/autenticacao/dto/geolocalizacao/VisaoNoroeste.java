package br.com.waiso.autenticacao.dto.geolocalizacao;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name="northwest")
public class VisaoNoroeste implements Serializable {

	@JsonProperty("lat")
	private Double lat;
	@JsonProperty("lng")
	private Double lng;
	
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	
	@Override
	public String toString() {
		return "VisaoNoroeste [lat=" + lat + ", lng=" + lng + "]";
	}
	
}
