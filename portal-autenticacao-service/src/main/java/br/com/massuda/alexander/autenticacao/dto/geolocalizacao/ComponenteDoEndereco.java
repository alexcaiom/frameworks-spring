package br.com.massuda.alexander.autenticacao.dto.geolocalizacao;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="address_components")
public class ComponenteDoEndereco {

	@XmlElement(name="long_name")
	private String nomeComprido;
	@XmlElement(name="short_name")
	private String abreviacao;
	@XmlElement(name="types")
	private String[] tipos;
	
	@Override
	public String toString() {
		return "ComponenteDoEndereco [nomeComprido=" + nomeComprido + ", abreviacao="
				+ abreviacao + ", tipos=" + Arrays.toString(tipos) + "]";
	}
	
}
