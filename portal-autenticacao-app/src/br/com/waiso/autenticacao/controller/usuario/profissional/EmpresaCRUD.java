package br.com.waiso.autenticacao.controller.usuario.profissional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.massuda.alexander.autenticacao.orm.modelo.usuario.profissional.Empresa;
import br.com.waiso.autenticacao.controller.Controlador;

@Controller
@RequestMapping("/empresa")
public class EmpresaCRUD extends Controlador<Empresa> {

}
