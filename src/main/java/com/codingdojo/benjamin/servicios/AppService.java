package com.codingdojo.benjamin.servicios;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.benjamin.modelos.User;
import com.codingdojo.benjamin.repositorios.RepositorioUsuarios;

@Service
public class AppService {

	@Autowired
	private RepositorioUsuarios repositorio_usuarios;
	
	public User register(User nuevoUsuario, BindingResult result) {
		
		String nuevoEmail = nuevoUsuario.getEmail();
		User existeUsuario = repositorio_usuarios.findByEmail(nuevoEmail);
		if(existeUsuario!=null) {
			result.rejectValue("email", "Unique", "El correo ya esta registrado en nuestra base de datos");
		}
		if (! nuevoUsuario.getPassword().equals(nuevoUsuario.getConfirm())) {
			result.rejectValue("confirm", "Matches", "Las contraseñas no coinciden");
		}
		if(!result.hasErrors()) {
			String contra_encr = BCrypt.hashpw(nuevoUsuario.getPassword(), BCrypt.gensalt());
			nuevoUsuario.setPassword(contra_encr);
			return repositorio_usuarios.save(nuevoUsuario);
		}else {
			return null;
		}

	}
	
	public User login(String email, String password) {
		
		User existUsuario = repositorio_usuarios.findByEmail(email);
		if(existUsuario == null) {
			return null;
		}
		if(BCrypt.checkpw(password, existUsuario.getPassword())) {
			return existUsuario;
		}else {
			return null;
		}
		
	}
	
	
	
	
	
	
	
	
	
}
