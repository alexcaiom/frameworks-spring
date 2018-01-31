package br.com.waiso.autenticacao.controller.usuario.academico;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.academico.InstituicaoDeEnsino;
import br.com.waiso.autenticacao.controller.Controlador;

@Controller
@RequestMapping("/instituicao_de_ensino")
public class InstituicaoDeEnsinoCRUD extends Controlador<InstituicaoDeEnsino> {

}
