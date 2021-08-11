import aluno.GContatos;
import cliente.CirculoNotFoundException;
import cliente.ContatoNotFoundException;

public class Runner {

    private static final String AMIGOS = "amigos";
    private static final String TRABALHO = "trabalho";
    private static final String FAMILIA = "familia";
    private static final String JOAQUIM_EMAIL = "joaquim@ufc.br";
    private static final String JOAQUIM = "joaquim";
    private static final String ANA_EMAIL = "ana@ufc.br";
    private static final String ANA = "ana";
    private static final String MARIO_EMAIL = "mario@ufc.br";
    private static final String MARIO = "mario";
    private static final String JOSE_EMAIL = "jose@ufc.br";
    private static final String JOSE = "jose";
    private static final String JAMES_EMAIL = "james@ufc.com";
    private static final String JAMES = "james";

    public static void main(final String[] args) throws ContatoNotFoundException, CirculoNotFoundException {
        GContatos gcont = new GContatos();

        gcont.createCircle(FAMILIA, 3);
        gcont.createCircle(AMIGOS, 2);
        gcont.createCircle(TRABALHO, 3);
        System.out.println(gcont.getAllCircles());

        gcont.createContact(JAMES, JAMES_EMAIL);
        gcont.createContact(MARIO, MARIO_EMAIL);
        gcont.createContact(JOSE, JOSE_EMAIL);
        gcont.createContact(ANA, ANA_EMAIL);
        gcont.createContact(JOAQUIM, JOAQUIM_EMAIL);
        System.out.println(gcont.getAllContacts());

        gcont.tie(MARIO, FAMILIA);
        System.out.println(gcont.getCircles(MARIO));

        gcont.tie(JAMES, TRABALHO);
        gcont.tie(JOAQUIM, TRABALHO);
        gcont.tie(ANA, TRABALHO);
        System.out.println(gcont.getContacts(TRABALHO));

        gcont.tie(JAMES, AMIGOS);
        gcont.tie(MARIO, AMIGOS);
        System.out.println(gcont.getContacts(AMIGOS));

        System.out.println(gcont.getCommomCircle(JAMES, ANA));
        System.out.println(gcont.getCommomCircle(JAMES, JOSE));
        System.out.println(gcont.getCommomCircle(JAMES, MARIO));
    }
}
