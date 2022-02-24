package com.gugawag.pdist.ejbs;


import com.gugawag.pdist.model.Mensagem;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless(name = "mensagemService")
@Remote
public class MensagemService {

    @EJB
    private MensagemDAO mensagemDAO;

    public List<Mensagem> listar() {
        return mensagemDAO.listar();
    }

    public void inserir(long id, String texto) {
        Mensagem novaMens = new Mensagem(id, texto);
        mensagemDAO.inserir(novaMens);

        if (id == 4L) {
            novaMens.setTexto(texto + " alterado");
        }
    }


}
