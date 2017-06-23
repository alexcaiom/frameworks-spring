package br.com.waiso.autenticacao.dto.geolocalizacao;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="geometry")
public class Geometria implements Serializable {

	@XmlElement(name="location")
	private Localizacao localizacao;
	@XmlElement(name="location_type")
	private String tipo;
	@XmlElement(name="viewport")
	private ViewPort viewport;
	
	@Override
	public String toString() {
		return "Geometria [location=" + localizacao + ", location_type="
				+ tipo + ", viewport=" + viewport + "]";
	}
}
