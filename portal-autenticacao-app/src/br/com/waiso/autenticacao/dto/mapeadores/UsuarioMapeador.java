package br.com.waiso.autenticacao.dto.mapeadores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.waiso.autenticacao.dto.UsuarioDTO;
import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.Usuario;

public class UsuarioMapeador extends Mapeador {

	public static UsuarioDTO from(Usuario o) {
		UsuarioDTO u = new UsuarioDTO();
		if (existe(o)) {
			BeanUtils.copyProperties(o, u, "dataDeNascimento", "dataDeCriacao");
			u.setDataCriacao(o.getDataDeCriacao().getTime());
			if (existe(o.getDataDeNascimento())) {
				u.setDataNascimento(o.getDataDeNascimento().getTime());
			}
		}
		return u;
	}

	public static List<UsuarioDTO> from(List<Usuario> usuarios) {
		List<UsuarioDTO> dtos = new ArrayList<>();
		for (Usuario usuario : usuarios) {
			dtos.add(from(usuario));
		}
		return dtos;
	}

}
