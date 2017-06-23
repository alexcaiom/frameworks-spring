package br.com.massuda.alexander.autenticacao.dto.geolocalizacao;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="viewport")
public class ViewPort implements Serializable {

	@XmlElement(name="northeast")
	private VisaoNordeste nordeste;
	@XmlElement(name="northwest")
	private VisaoNoroeste noroeste;
	
	@Override
	public String toString() {
		return "ViewPort [nordeste=" + nordeste + ", noroeste=" + noroeste
				+ "]";
	}
	
	
}
