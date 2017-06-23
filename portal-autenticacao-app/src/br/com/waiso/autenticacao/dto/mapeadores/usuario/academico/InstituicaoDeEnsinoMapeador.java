package br.com.waiso.autenticacao.dto.mapeadores.usuario.academico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.waiso.autenticacao.dto.mapeadores.EnderecoMapeador;
import br.com.waiso.autenticacao.dto.mapeadores.Mapeador;
import br.com.waiso.autenticacao.dto.usuario.academico.InstituicaoDeEnsinoDTO;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.academico.InstituicaoDeEnsino;

public class InstituicaoDeEnsinoMapeador extends Mapeador {

	public static List<InstituicaoDeEnsinoDTO> from (List<InstituicaoDeEnsino> lista) {
		List<InstituicaoDeEnsinoDTO> listaDTO = new ArrayList<>();
		for (InstituicaoDeEnsino instituicaoDeEnsino : lista) {
			listaDTO.add(from(instituicaoDeEnsino));
		}
		return listaDTO;
	}

	public static InstituicaoDeEnsinoDTO from(InstituicaoDeEnsino instituicaoDeEnsino) {
		InstituicaoDeEnsinoDTO dto = new InstituicaoDeEnsinoDTO();
		BeanUtils.copyProperties(instituicaoDeEnsino, dto, "endereco");
		dto.setEndereco(EnderecoMapeador.from(instituicaoDeEnsino.getEndereco()));
		return dto;
	}
	
}
