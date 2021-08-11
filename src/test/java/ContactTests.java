import aluno.GContatos;
import aluno.base.Contato;
import cliente.ContatoBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTests {

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
	private ContatoBase james, jose, mario, ana, joaquim;

	@BeforeEach
	public void setUp() {
		james = new Contato(JAMES, JAMES_EMAIL);
		jose = new Contato(JOSE, JOSE_EMAIL);
		mario = new Contato(MARIO, MARIO_EMAIL);
		ana = new Contato(ANA, ANA_EMAIL);
		joaquim = new Contato(JOAQUIM, JOAQUIM_EMAIL);

		gcont = new GContatos();
	}

	@Test
	public void adicionarContato() {
		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertEquals(1, gcont.getNumberOfContacts(), "Quantidade de contatos errada");
	}

	@Test
	public void adicionarContatoDuplicado() {
		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertFalse(gcont.createContact(JAMES, "jesus2@ufc.com"), "Contato com id duplicado");
		assertEquals(1, gcont.getNumberOfContacts(), "Quantidade de contatos errada");
	}

	@Test
	public void removendoContato() {
		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertEquals(1, gcont.getNumberOfContacts(), "Quantidade de contatos errada" );
		assertTrue(gcont.removeContact(JAMES), "Contato deve ser removido");
		assertEquals(0, gcont.getNumberOfContacts(), "Quantidade de contatos errada");
	}

	@Test
	public void removendoContatoInexistente() {
		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertEquals(1, gcont.getNumberOfContacts(), "Quantidade de contatos errada");
		assertFalse(gcont.removeContact("ramiro"), "Contato nao cadastrado nao pode ser removido");
		assertEquals(1, gcont.getNumberOfContacts(), "Quantidade de contatos errada");
	}

	@Test
	public void recuperandoContato() {
		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertEquals(1, gcont.getNumberOfContacts(), "Quantidade de contatos errada");
		ContatoBase james = gcont.getContact(JAMES);
		assertEquals(james, this.james, "Contato recuperado diferente do buscado");
	}

	@Test
	public void recuperandoContatoInexistene() {
		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertEquals(1, gcont.getNumberOfContacts(), "Quantidade de contatos errada");
		assertEquals(null, gcont.getContact("ramiro"), "Contato nao existente");
	}

	@Test
	public void recuperandoTodosOsContatos() {
		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertTrue(gcont.createContact(ANA, ANA_EMAIL), "Contato valido, deve ser adicionado");
		assertTrue(gcont.createContact(JOSE, JOSE_EMAIL), "Contato valido, deve ser adicionado");

		assertEquals(Arrays.asList(ana, james, jose), gcont.getAllContacts(), "Lista de contatos errada");
	}

	@Test
	public void atualizandoContato() {
		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertEquals(1, gcont.getNumberOfContacts(), "Quantidade de contatos errada");

		james.setEmail("novo@ufc.br");

		assertTrue(gcont.updateContact(james), "Contato valido, deve ser atualizado");
		ContatoBase james = gcont.getContact(JAMES);
		assertEquals(this.james, james, "Contato nao foi atualizado corretamente");
	}

	@Test
	public void atualizandoInexistente() {
		assertFalse(gcont.updateContact(james), "Contato nao existente, logo nao pode ser atualizado");
	}

	@Test
	public void favoritandoUmContato() {
		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertTrue(gcont.favoriteContact(JAMES), "Contato deve ser marcado como favorito");
		assertTrue(gcont.isFavorited(JAMES), "Contato esta na lista de favoritos");
		assertFalse(gcont.isFavorited(ANA), "Contato nao esta na lista de favoritos");

	}

	@Test
	public void favoritandoUmContatoInexistente() {
		assertFalse(gcont.favoriteContact(JAMES), "Contato nao existe");
		assertFalse(gcont.isFavorited(JAMES), "Contato nao esta na lista de favoritos");
	}

	@Test
	public void desfavoritandoUmContato() {
		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertTrue(gcont.favoriteContact(JAMES), "Contato deve ser marcado como favorito");
		assertTrue(gcont.isFavorited(JAMES), "Contato esta na lista de favoritos");

		assertTrue(gcont.unfavoriteContact(JAMES), "Contato nao removido dos favoritos");
		assertFalse(gcont.isFavorited(JAMES), "Contato nao esta na lista de favoritos");
	}

	@Test
	public void desfavoritandoUmContatoInexistente() {
		assertFalse(gcont.unfavoriteContact(JAMES), "Contato nao existe");
		assertFalse(gcont.isFavorited(JAMES), "Contato nao esta na lista de favoritos");
	}

	@Test
	public void recuperandoTodosOsFavoritos() {
		assertTrue(gcont.createContact(JAMES, JAMES_EMAIL), "Contato valido, deve ser adicionado");
		assertTrue(gcont.createContact(MARIO, MARIO_EMAIL), "Contato valido, deve ser adicionado");
		assertTrue(gcont.createContact(ANA, ANA_EMAIL), "Contato valido, deve ser adicionado");

		assertTrue(gcont.favoriteContact(JAMES), "Contato deve ser marcado como favorito");
		assertTrue(gcont.favoriteContact(ANA), "Contato deve ser marcado como favorito");
		assertTrue(gcont.favoriteContact(MARIO), "Contato deve ser marcado como favorito");

		assertTrue(gcont.isFavorited(ANA), "O contato esta na lista de favoritos");
		assertFalse(gcont.isFavorited(JOSE), "O contato nao esta na lista de favoritos");

		assertEquals(Arrays.asList(ana, james, mario), gcont.getFavorited(), "Lista de favoritos errada");

		assertTrue(gcont.unfavoriteContact(ANA), "Contato deve ser removido dos favoritos");

		assertEquals(Arrays.asList(james, mario), gcont.getFavorited(), "Remocao de favoritos errada");

		assertTrue(gcont.removeContact(MARIO), "Contato deve ser removido dos favoritos");

		assertEquals(Arrays.asList(james), gcont.getFavorited(), "Remocao de favoritos errada");

	}
}
