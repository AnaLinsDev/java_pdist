package br.edu.ifpb.progdist.hellospring.service;

import br.edu.ifpb.progdist.hellospring.dao.UsuariosDAO;
import br.edu.ifpb.progdist.hellospring.model.Usuario;

import java.util.List;

public class UsuariosService {

    private UsuariosDAO usuariosDAO = new UsuariosDAO();

    public UsuariosService() {}

    public List<Usuario> getUsuarios() {
        return usuariosDAO.getUsuarios();
    }

    public Usuario getUsuarioPorCodigo(int codigo) {
        Usuario usuario = null;

        for(Usuario usu : this.getUsuarios()){
            if ( usu.getCodigo() == codigo ) { usuario = usu; };
        }

        return usuario;
    }

    public void inserirUsuario(Usuario usuario){
        this.usuariosDAO.inserirUsuario(usuario);
    }
}
