package br.com.waiso.autenticacao.controller.usuario.academico;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.academico.Escolaridade;
import br.com.waiso.autenticacao.controller.Controlador;

@Controller
@RequestMapping("/escolaridade")
public class EscolaridadeCRUD extends Controlador<Escolaridade> {

}
