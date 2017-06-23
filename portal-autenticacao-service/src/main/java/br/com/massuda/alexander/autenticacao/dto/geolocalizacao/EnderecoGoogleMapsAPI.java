package br.com.massuda.alexander.autenticacao.dto.geolocalizacao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnderecoGoogleMapsAPI implements Serializable {

	@JsonProperty("address_components")
	public List<ComponenteDoEndereco> componentes;
	@JsonProperty("formatted_address")
	public String enderecoFormatado;
	@JsonProperty("geometry")
	public Geometria geometria;
	@JsonProperty("place_id")
	public String id;
	@JsonProperty("types")
	public String[] tipos;
	
	public String toString() {
		return "EnderecoGoogleMapsAPI [componentes=" + componentes 
				+ ", enderecoFormatado=" + enderecoFormatado 
				+ ", geometria=" + geometria 
				+ ", id=" + id 
				+ ", tipos=" + Arrays.toString(tipos) + "]";
	}
	
}
