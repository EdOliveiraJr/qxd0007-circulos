package aluno.base;

import java.util.ArrayList;
import java.util.List;
import cliente.CirculoBase;
import cliente.ContatoBase;

public class Circulo extends CirculoBase {

    List <ContatoBase> contatosNoCirculo;

    public Circulo(String id, int limite) {
        super(id, limite);
        contatosNoCirculo = new ArrayList<>();
    }

    @Override
    public boolean adicionaNaLista (ContatoBase contato){
        if(contatosNoCirculo.contains(contato)){
            return false;
        }
        contatosNoCirculo.add(contato);
        return true;
    }

    @Override
    public boolean removerDaLista(ContatoBase contato){
        if(!contatosNoCirculo.contains(contato)){
            return false;
        }
        contatosNoCirculo.remove(contato);
        return true;
    }

    @Override    
    public List <ContatoBase> getContatosNoCirculo(){
        return contatosNoCirculo;
    }

    @Override
    public int getNumberOfContacts() {
        return contatosNoCirculo.size();
    }
}
