package aluno.base;

import cliente.CirculoBase;

public class Circulo extends CirculoBase {

    public Circulo(String id, int limite) {
        super(id, limite);
    }

    @Override
    public int getNumberOfContacts() {
        return 0;
    }
}
