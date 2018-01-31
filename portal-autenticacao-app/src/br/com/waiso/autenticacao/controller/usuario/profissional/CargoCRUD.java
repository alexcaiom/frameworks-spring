package br.com.waiso.autenticacao.controller.usuario.profissional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.profissional.Cargo;
import br.com.waiso.autenticacao.controller.Controlador;

@Controller
@RequestMapping("/cargo")
public class CargoCRUD extends Controlador<Cargo> {

}
