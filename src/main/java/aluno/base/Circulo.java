package aluno.base;

import java.util.ArrayList;
import java.util.List;
import cliente.CirculoBase;

public class Circulo extends CirculoBase {

    List <Contato> contatosNoCirculo;

    public Circulo(String id, int limite) {
        super(id, limite);
        contatosNoCirculo = new ArrayList<>();
    }

    public boolean adicionaNaLista (Contato contato){
        if(contatosNoCirculo.contains(contato)){
            return false;
        }
        contatosNoCirculo.add(contato);
        return true;
    }

    public boolean removerDaLista(Contato contato){
        if(!contatosNoCirculo.contains(contato)){
            return false;
        }
        contatosNoCirculo.remove(contato);
        return true;
    }

    @Override
    public int getNumberOfContacts() {
        return contatosNoCirculo.size();
    }
}
