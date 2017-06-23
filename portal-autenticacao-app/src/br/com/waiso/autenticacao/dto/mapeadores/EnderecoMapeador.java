package br.com.waiso.autenticacao.dto.mapeadores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.waiso.autenticacao.dto.EnderecoDTO;
import br.com.massuda.alexander.autenticacao.orm.modelo.Endereco;
import br.com.waiso.autenticacao.servicos.ServicoLocalizacao;

public class EnderecoMapeador extends Mapeador {

	public static EnderecoDTO from (Endereco o) {
		EnderecoDTO e = null;
		if (existe(o)) {
			e = new EnderecoDTO();
			BeanUtils.copyProperties(o, e, "coordenadasGeograficas");
			Double latitude = o.getCoordenadasGeograficas().getLatitude();
			Double longitude = o.getCoordenadasGeograficas().getLongitude();
			String logradouro = ServicoLocalizacao.getInstance().getEnderecoPorCoordenadasGeograficas(latitude, longitude);
			e.setLogradouro(logradouro);
		}
		return e;
	}

	public static List<EnderecoDTO> from(List<Endereco> enderecos) {
		List<EnderecoDTO> dtos = new ArrayList<>();
		for (Endereco endereco : enderecos) {
			dtos.add(from(endereco));
		}
		return dtos;
	}
	
}
