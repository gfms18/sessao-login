package br.ifpe.web4.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.web4.DAO.UsuarioDAO;
import br.ifpe.web4.entity.Usuario;

@Controller
public class UsuarioController {
	@Autowired
	private  UsuarioDAO usuarioDAO;

	@GetMapping("/")
	public String login() {
		return "login";
	}
	@GetMapping("/cadastro")
	public String cadastro() {
		return "cadastro";
	}
	@PostMapping("/salvarusuario")
	public String salvarcadastro(Usuario usuario) {
		this.usuarioDAO.save(usuario);
		return "redirect:/";
	}
	@GetMapping("/adm/home")
	public String home() {
		return "home";
	}
	@PostMapping("/login")
	public String efetuarLogin(String login, String senha,  HttpSession session, RedirectAttributes ra) {
		
		Usuario usuarioLogado = this.usuarioDAO.findByLoginAndSenha(login, senha);
		 
		if (usuarioLogado == null) {
			
			ra.addFlashAttribute("mensagem", "email ou senha invalidos");
			return "redirect:/";
			} else {
				session.setAttribute("usuarioLogado", usuarioLogado);
				return "redirect:/adm/home";
			}
		}
	
	@GetMapping("/sair")
	public String sair(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	@GetMapping("/acessoNegado")
	public String acessoNegado() {
		return "negado";
	}
}
