package br.com.ifpb.gpsback.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.ifpb.gpsback.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpb.gpsback.model.Task;
import br.com.ifpb.gpsback.model.Usuario;
import br.com.ifpb.gpsback.repository.TaskRepository;
import br.com.ifpb.gpsback.repository.UsuarioRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;


	@PostMapping("criarusuario")
	public Usuario create(@RequestBody Usuario usuario) {
		return usuarioService.create(usuario);
	}

	@GetMapping("listarusuarios")
	public List<Usuario> findAll() {
		return usuarioService.findAll();
	}

	@GetMapping(path = { "lerusuario/{idusu}" })
	public List<Usuario> findById(@PathVariable long idusu) {
		return usuarioService.findById(idusu);
	}

	@PutMapping(value = "atualizarusuario/{idusu}")
	public Usuario update(@PathVariable long idusu, @RequestBody Usuario usuario) {
		return usuarioService.update(idusu, usuario);
	}

	@DeleteMapping(path = { "deletarusuario/{idusu}" })
	public ResponseEntity<?> delete(@PathVariable long idusu) {
		return usuarioService.delete(idusu);
	}

	@PostMapping("/login")
	public Usuario login(@RequestBody Usuario usuario) {
		return usuarioService.login(usuario);
	}


}
