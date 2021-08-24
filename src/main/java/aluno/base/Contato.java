package aluno.base;

import cliente.ContatoBase;

public class Contato extends ContatoBase {
    private boolean favorite;

    public Contato(String id, String email) {
        super(id, email);
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean getFavorite(){
        return favorite;
    }

}
