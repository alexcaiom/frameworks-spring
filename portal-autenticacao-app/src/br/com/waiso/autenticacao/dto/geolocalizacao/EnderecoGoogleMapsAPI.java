package br.com.waiso.autenticacao.dto.geolocalizacao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="results")
public class EnderecoGoogleMapsAPI implements Serializable {

	@XmlElement(name="address_components")
	public List<ComponenteDoEndereco> componentes;
	@XmlElement(name="formatted_address")
	public String enderecoFormatado;
	@XmlElement(name="geometry")
	public Geometria geometria;
	@XmlElement(name="place_id")
	public String id;
	@XmlElement(name="types")
	public String[] tipos;
	
	public String toString() {
		return "EnderecoGoogleMapsAPI [componentes=" + componentes 
				+ ", enderecoFormatado=" + enderecoFormatado 
				+ ", geometria=" + geometria 
				+ ", id=" + id 
				+ ", tipos=" + Arrays.toString(tipos) + "]";
	}
	
}
