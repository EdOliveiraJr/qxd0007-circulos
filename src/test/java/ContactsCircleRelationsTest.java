import aluno.GContatos;
import aluno.base.Circulo;
import aluno.base.Contato;
import cliente.CirculoBase;
import cliente.CirculoNotFoundException;
import cliente.ContatoBase;
import cliente.ContatoNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ContactsCircleRelationsTest {

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
	private GContatos gcont;
	private CirculoBase familia, trabalho, amigos;
	private ContatoBase james, jose, mario, ana, joaquim;

	@BeforeEach
	public void setUp() {
		familia = new Circulo(FAMILIA, 3);
		trabalho = new Circulo(TRABALHO, 3);
		amigos = new Circulo(AMIGOS, 2);

		james = new Contato(JAMES, JAMES_EMAIL);
		jose = new Contato(JOSE, JOSE_EMAIL);
		mario = new Contato(MARIO, MARIO_EMAIL);
		ana = new Contato(ANA, ANA_EMAIL);
		joaquim = new Contato(JOAQUIM, JOAQUIM_EMAIL);

		gcont = new GContatos();
	}


	@Test
	public void adicionarContatoCirculoExistente() throws CirculoNotFoundException, ContatoNotFoundException {
		assertTrue(gcont.createCircle(FAMILIA, 3), "O circulo deve ser adicionado");
		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.tie(JAMES, FAMILIA), "Contato deve ser adicionado ao circulo");
		assertEquals(1, gcont.getCircle(FAMILIA).getNumberOfContacts(), "Numero de contatos no circulo errado");
		assertEquals(Arrays.asList(familia), gcont.getCircles(JAMES), "Lista de circulos do contato esta errada");
		assertEquals(Arrays.asList(james), gcont.getContacts("familia"), "Lista de contatos de um circulo esta errada");
	}

	@Test
	public void adicionarContatoInexistenteCirculoExistente() {
		assertTrue(gcont.createCircle(FAMILIA, 3), "O circulo deve ser adicionado");

		assertThrows(ContatoNotFoundException.class, () -> gcont.tie(JAMES, FAMILIA));
		assertEquals(0, gcont.getCircle(FAMILIA).getNumberOfContacts(), "Numero de contatos no circulo errado");

	}

	@Test
	public void adicionarContatoCirculoInexistente() throws CirculoNotFoundException, Exception {
		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "O contato deve ser adicionado");

		CirculoNotFoundException e = assertThrows(CirculoNotFoundException.class, () -> gcont.tie(JAMES, FAMILIA));
		assertTrue(e.getCirculoNaoEncontrado() == FAMILIA, "A excecao nao retornou o id do circulo que nao existe");

		assertEquals(gcont.getCircle(FAMILIA), null, "Circulo nao existente");
		assertEquals(Collections.EMPTY_LIST, gcont.getCircles(JAMES), "Contato nao esta em nenhum circulo");
	}

	@Test
	public void adicionarContatoDuplicadoCirculoExistente() throws CirculoNotFoundException, ContatoNotFoundException {
		assertTrue(gcont.createCircle(FAMILIA, 3), "O circulo deve ser adicionado");
		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.tie(JAMES, FAMILIA), "Contato deve ser adicionado ao circulo");
		assertFalse(gcont.tie(JAMES, FAMILIA), "Contato ja esta no circulo");
		assertEquals(1, gcont.getCircle(FAMILIA).getNumberOfContacts(), "Numero de contatos no circulo errado");
		assertEquals(Arrays.asList(familia), gcont.getCircles(JAMES), "Lista de circulos do contato esta errada");
		assertEquals(Arrays.asList(james), gcont.getContacts("familia"), "Lista de contatos de um circulo esta errada");
	}

	@Test
	public void adicionarAlemDoLimite() throws CirculoNotFoundException, ContatoNotFoundException {
		assertTrue(gcont.createCircle(FAMILIA, 3), "O circulo deve ser adicionado");

		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.createContact(JOSE, JOSE_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.createContact(ANA, ANA_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.createContact(JOAQUIM, JOAQUIM_EMAIL), "O contato deve ser adicionado");

		assertTrue(gcont.tie(JAMES, FAMILIA), "Contato deve ser adicionado ao circulo");
		assertTrue(gcont.tie(JOSE, FAMILIA), "Contato deve ser adicionado ao circulo");
		assertTrue(gcont.tie(ANA, FAMILIA), "Contato deve ser adicionado ao circulo");
		assertFalse(gcont.tie(JOAQUIM, FAMILIA), "Limite do circulo atingido");
	}

	@Test
	public void adicionarContatoVariosCirculos() throws CirculoNotFoundException, ContatoNotFoundException {
		assertTrue(gcont.createCircle(FAMILIA, 3), "O circulo deve ser adicionado");
		assertTrue(gcont.createCircle(AMIGOS, 2), "O circulo deve ser adicionado");

		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.createContact(MARIO, MARIO_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.createContact(JOSE, JOSE_EMAIL), "O contato deve ser adicionado");

		assertTrue(gcont.tie(JAMES, FAMILIA), "Contato deve ser adicionado ao circulo");
		assertTrue(gcont.tie(MARIO, FAMILIA), "Contato deve ser adicionado ao circulo");
		assertTrue(gcont.tie(JOSE, FAMILIA), "Contato deve ser adicionado ao circulo");

		assertTrue(gcont.tie(JAMES, AMIGOS), "Contato deve ser adicionado ao circulo");

		assertEquals(Arrays.asList(amigos, familia), gcont.getCircles(JAMES), "Lista de circulos do contato esta errada");

		assertEquals(Arrays.asList(james, jose, mario), gcont.getContacts(FAMILIA), "Lista de contatos de um circulo esta errada");
	}

	@Test
	public void removendoContatoDoCirculo() throws CirculoNotFoundException, ContatoNotFoundException {
		assertTrue(gcont.createCircle(FAMILIA, 3), "O circulo deve ser adicionado");
		assertTrue(gcont.createCircle(AMIGOS, 2), "O circulo deve ser adicionado");

		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "O contato deve ser adicionado");

		assertTrue(gcont.tie(JAMES, FAMILIA), "Contato deve ser adicionado ao circulo");
		assertTrue(gcont.tie(JAMES, AMIGOS), "Contato deve ser adicionado ao circulo");

		assertEquals(Arrays.asList(amigos, familia), gcont.getCircles(JAMES), "Lista de circulos do contato esta errada");

		assertTrue(gcont.untie(JAMES, AMIGOS), "Contato deve ser removido ao circulo");

		assertEquals(Arrays.asList(familia), gcont.getCircles(JAMES), "Remocao de contato errada");
	}

	@Test
	public void removendoContatoInexistenteDoCirculo() throws CirculoNotFoundException, ContatoNotFoundException {
		assertTrue(gcont.createCircle(FAMILIA, 3), "O circulo deve ser adicionado");
		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.tie(JAMES, FAMILIA), "Contato deve ser adicionado ao circulo");

		ContatoNotFoundException e = assertThrows(ContatoNotFoundException.class, () -> gcont.untie("margarida", FAMILIA));
		assertTrue(e.getContatoNaoEncontrado() == "margarida", "A excecao nao retornou o id do contato que nao existe");
		assertEquals(1, gcont.getCircle(FAMILIA).getNumberOfContacts(), "Numero de contatos no circulo errado");
	}

	@Test
	public void removendoContatoDoCirculoInexistente() {
		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "O contato deve ser adicionado");
		CirculoNotFoundException e = assertThrows(CirculoNotFoundException.class, () -> gcont.untie(JAMES, FAMILIA));
		assertTrue(e.getCirculoNaoEncontrado() == FAMILIA, "A excecao nao retornou o id do circulo que nao existe");
	}

	@Test
	public void removendoCirculoQuePossuiContatos() throws CirculoNotFoundException, ContatoNotFoundException {

		assertTrue(gcont.createCircle(FAMILIA, 3), "O circulo deve ser adicionado");
		assertTrue(gcont.createCircle(AMIGOS, 2), "O circulo deve ser adicionado");
		assertTrue(gcont.createCircle(TRABALHO, 3), "O circulo deve ser adicionado");

		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.createContact(MARIO, MARIO_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.createContact(JOSE, JOSE_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.createContact(ANA, ANA_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.createContact(JOAQUIM, JOAQUIM_EMAIL), "O contato deve ser adicionado");

		assertTrue(gcont.tie(JAMES, FAMILIA), "Contato deve ser adicionado ao circulo");
		assertTrue(gcont.tie(MARIO, FAMILIA), "Contato deve ser adicionado ao circulo");
		assertTrue(gcont.tie(JOSE, FAMILIA), "Contato deve ser adicionado ao circulo");

		assertTrue(gcont.tie(JAMES, TRABALHO), "Contato deve ser adicionado ao circulo");
		assertTrue(gcont.tie(JOAQUIM, TRABALHO), "Contato deve ser adicionado ao circulo");
		assertTrue(gcont.tie(ANA, TRABALHO), "Contato deve ser adicionado ao circulo");

		assertTrue(gcont.tie(JAMES, AMIGOS), "Contato deve ser adicionado ao circulo");

		assertTrue(gcont.removeCircle(FAMILIA), "O circulo deve ser removido");

		assertEquals(Arrays.asList(amigos, trabalho), gcont.getCircles(JAMES), "Lista de circulos do contato esta errada");
		
		assertEquals(Collections.EMPTY_LIST, gcont.getCircles(JOSE), "Lista de circulos do contato esta errada");
		assertEquals(null, gcont.getCircle("familia"), "Circulo nao existe mais");

	}
	
	@Test
	public void removendoContatosQueEstaEmCirculos() throws CirculoNotFoundException, ContatoNotFoundException {

		assertTrue(gcont.createCircle(FAMILIA, 3), "O circulo deve ser adicionado");
		assertTrue(gcont.createCircle(AMIGOS, 2), "O circulo deve ser adicionado");
		assertTrue(gcont.createCircle(TRABALHO, 3), "O circulo deve ser adicionado");

		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.createContact(JOSE, JOSE_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.createContact(ANA, ANA_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.createContact(MARIO, MARIO_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.createContact(JOAQUIM, JOAQUIM_EMAIL), "O contato deve ser adicionado");

		assertTrue(gcont.tie(JAMES, FAMILIA), "Contato deve ser adicionado ao circulo");
		assertTrue(gcont.tie(MARIO, FAMILIA), "Contato deve ser adicionado ao circulo");
		assertTrue(gcont.tie(JOSE, FAMILIA), "Contato deve ser adicionado ao circulo");

		assertTrue(gcont.tie(JAMES, TRABALHO), "Contato deve ser adicionado ao circulo");
		assertTrue(gcont.tie(JOAQUIM, TRABALHO), "Contato deve ser adicionado ao circulo");
		assertTrue(gcont.tie(ANA, TRABALHO), "Contato deve ser adicionado ao circulo");

		assertTrue(gcont.tie(JAMES, AMIGOS), "Contato deve ser adicionado ao circulo");

		assertTrue(gcont.removeContact(JAMES), "O contato deve ser removido");

		assertEquals(Arrays.asList(jose, mario), gcont.getContacts("familia"), "A lista de contatos do circulo esta errada");
		assertEquals(Arrays.asList(ana, joaquim), gcont.getContacts("trabalho"), "A lista de contatos do circulo esta errada");
		assertEquals(Collections.EMPTY_LIST, gcont.getContacts("amigos"), "A lista de contatos do circulo esta errada");

		ContatoNotFoundException e = assertThrows(ContatoNotFoundException.class, () -> gcont.getCircles(JAMES));
		assertTrue(e.getContatoNaoEncontrado() ==  JAMES, "A excecao nao retornou o id do contato que nao existe");

		assertEquals(null, gcont.getContact(JAMES));

	}

	@Test
	public void circulosEmComum() throws CirculoNotFoundException, ContatoNotFoundException {
		assertTrue(gcont.createCircle(FAMILIA, 3), "O circulo deve ser adicionado");
		assertTrue(gcont.createCircle(AMIGOS, 2), "O circulo deve ser adicionado");
		assertTrue(gcont.createCircle(TRABALHO, 3), "O circulo deve ser adicionado");

		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.createContact(MARIO, MARIO_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.createContact(JOSE, JOSE_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.createContact(ANA, ANA_EMAIL), "O contato deve ser adicionado");
		assertTrue(gcont.createContact(JOAQUIM, JOAQUIM_EMAIL), "O contato deve ser adicionado");

		assertTrue(gcont.tie(JAMES, FAMILIA), "Contato deve ser adicionado ao circulo");
		assertTrue(gcont.tie(MARIO, FAMILIA), "Contato deve ser adicionado ao circulo");

		assertTrue(gcont.tie(JAMES, TRABALHO), "Contato deve ser adicionado ao circulo");
		assertTrue(gcont.tie(JOAQUIM, TRABALHO), "Contato deve ser adicionado ao circulo");
		assertTrue(gcont.tie(ANA, TRABALHO), "Contato deve ser adicionado ao circulo");

		assertTrue(gcont.tie(JAMES, AMIGOS), "Contato deve ser adicionado ao circulo");
		assertTrue(gcont.tie(MARIO, AMIGOS), "Contato deve ser adicionado ao circulo");

		assertEquals(Arrays.asList(trabalho), gcont.getCommomCircle(JAMES, ANA));
		assertEquals(Collections.EMPTY_LIST, gcont.getCommomCircle(JAMES, JOSE));
		assertEquals(Arrays.asList(amigos, familia), gcont.getCommomCircle(JAMES, MARIO));

	}

}
