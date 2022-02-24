package br.com.ifpb.gpsback.services;

import br.com.ifpb.gpsback.model.Usuario;
import br.com.ifpb.gpsback.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> findById(long idusu) {
        List<Usuario> usuarios = new ArrayList<>();
        usuarioRepository.findById(idusu).map(u -> usuarios.add(u));
        return usuarios;
    }

    public Usuario update(long idusu, Usuario usuario) {
        Usuario usuarioDb = this.findById(idusu).get(0);
        usuarioDb.setEmail(usuario.getEmail());
        usuarioDb.setName(usuario.getName());
        usuarioDb.setPassword(usuario.getPassword());
        Usuario usuarioAtualizado = usuarioRepository.save(usuarioDb);
        return usuarioAtualizado;

    }

    public ResponseEntity<?> delete(long idusu) {
        return usuarioRepository.findById(idusu).map(record -> {
            usuarioRepository.deleteById(idusu);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    public Usuario login(Usuario usuario) {
        Usuario usuarioEmail = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioEmail.getPassword().equals(usuario.getPassword())) {
            return usuarioEmail;
        }
        return null;
    }

}
