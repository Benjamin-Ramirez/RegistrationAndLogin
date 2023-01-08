package com.codingdojo.benjamin.controladores;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.benjamin.modelos.User;
import com.codingdojo.benjamin.servicios.AppService;

@Controller
public class ControladorUsuario {
	
	@Autowired
	private AppService servicio;
	
	@GetMapping("/")
	public String index(@ModelAttribute("nuevoUsuario") User user) {
		return "index.jsp";
	}
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session) {
		
		User usuario_en_sesion = (User)session.getAttribute("user_session");
		
		if(usuario_en_sesion == null) {
			return "redirect:/";
		}
		
		return "dashboard.jsp";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("user_session");
		return "redirect:/";
	}
	
	
	
	@PostMapping("/register")
	public String create(@Valid @ModelAttribute("nuevoUsuario") User nuevoUsuario,
						 BindingResult result, HttpSession session) {
		
		servicio.register(nuevoUsuario, result);
		if(result.hasErrors()) {
			return "index.jsp";
		}else {
			session.setAttribute("user_session", nuevoUsuario);
			return "redirect:/dashboard";
		}
	}
	@PostMapping("/login")
	public String login (@RequestParam(value="email") String email, 
						@RequestParam(value="password") String password, 
						RedirectAttributes redirectAttributes,
						HttpSession session) {
		User usuario_login = servicio.login(email, password);
		
		if(usuario_login == null) {
			redirectAttributes.addFlashAttribute("error_login", "El correo/password son incorrectos");
				return "redirect:/";
			}else {
				session.setAttribute("user_session", usuario_login);
				return "redirect:/dashboard";
			}
		}
		
		
		
	
	
	
	}
	
	
	


