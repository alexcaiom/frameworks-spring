package br.com.waiso.autenticacao.controller.usuario.profissional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.profissional.ExperienciaProfissional;
import br.com.waiso.autenticacao.controller.Controlador;

@Controller
@RequestMapping("/experiencia_profissional")
public class ExperienciaProfissionalCRUD extends Controlador<ExperienciaProfissional> {

}
