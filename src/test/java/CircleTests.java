import java.util.Arrays;


import aluno.GContatos;
import aluno.base.Circulo;
import cliente.CirculoBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CircleTests {
	private static final String AMIGOS = "amigos";
	private static final String TRABALHO = "trabalho";
	private static final String FAMILIA = "familia";
	
	private GContatos gcont;
	private CirculoBase familia, trabalho, amigos;
	
	@BeforeEach
    public void setUp() {
        familia = new Circulo(FAMILIA, 3);
        trabalho = new Circulo(TRABALHO, 3);
        amigos = new Circulo(AMIGOS, 1);

        gcont = new GContatos();
    }
	
	@Test
	public void adicionarCirculos(){
		assertTrue(gcont.createCircle(FAMILIA, 3), "O circulo deve ser adicionado");
		assertTrue(gcont.createCircle(AMIGOS, 1), "O circulo deve ser adicionado");
		assertTrue(gcont.createCircle(TRABALHO, 3), "O circulo deve ser adicionado");
		
		assertEquals(3, gcont.getNumberOfCircles(), "Todos os 3 circulos devem ser adiconados");
	}
	
	@Test
	public void adicionarCirculoLimiteInvalido(){
		assertTrue(gcont.createCircle(TRABALHO, 3), "O circulo deve ser adicionado");
		assertFalse(gcont.createCircle(TRABALHO, -5), "Circulo com limite menor ou igual a zero nao deve ser adicionado");
		
		assertEquals(1, gcont.getNumberOfCircles(), "Apenas um ciruclo devia ter sido adicionado");
	}
	
	@Test
	public void adicionarCirculoDuplicado(){
		assertTrue(gcont.createCircle(TRABALHO, 3), "O circulo deve ser adicionado");
		assertFalse(gcont.createCircle(TRABALHO, 5), "Circulo com id duplicado nao deve ser adicionado");
		
		assertEquals(1, gcont.getNumberOfCircles(), "Apenas um ciruclo devia ter sido adicionado");
	}
	
	@Test
	public void buscandoCirculoExistente(){
		assertTrue(gcont.createCircle(FAMILIA, 3), "O circulo deve ser adicionado");
		assertEquals(gcont.getCircle(FAMILIA), familia, "O circulo recuperado nao foi procurado");
	}
	
	@Test
	public void buscandoCirculoInexistente(){
		assertEquals(gcont.getCircle("inimigos"), null, "Circulo nao existente");
	}
	
	@Test
	public void recuperandoTodosOsCirculos(){
		assertTrue(gcont.createCircle(FAMILIA, 3), "O circulo deve ser adicionado");
		assertTrue(gcont.createCircle(AMIGOS, 1), "O circulo deve ser adicionado");
		assertTrue(gcont.createCircle(TRABALHO, 3), "O circulo deve ser adicionado");
		
		assertEquals(Arrays.asList(amigos, familia, trabalho), gcont.getAllCircles(), "Lista de circulos incorreta");
	}
	
	@Test
	public void removendoCirculoExistente(){
		assertTrue(gcont.createCircle(FAMILIA, 3), "O circulo deve ser adicionado");
		assertTrue(gcont.createCircle(AMIGOS, 1), "O circulo deve ser adicionado");
		assertTrue(gcont.createCircle(TRABALHO, 3), "O circulo deve ser adicionado");
		
		assertTrue(gcont.removeCircle(AMIGOS), "Circulo nao removido");
		assertEquals(2, gcont.getNumberOfCircles(), "Quantidade de circulos errada");
		assertEquals(Arrays.asList(familia, trabalho), gcont.getAllCircles(), "Lista de circulos incorreta");
	}
	
	@Test
	public void removendoCirculoInexistente(){
		assertTrue(gcont.createCircle(FAMILIA, 3), "O circulo deve ser adicionado");
		assertTrue(gcont.createCircle(AMIGOS, 1), "O circulo deve ser adicionado");
		assertTrue(gcont.createCircle(TRABALHO, 3), "O circulo deve ser adicionado");
		
		assertFalse(gcont.removeCircle("inimigos"), "Circulo nao existe, logo nao pode ser removido");
		assertEquals(3, gcont.getNumberOfCircles(), "Quantidade de circulos errada");
		assertEquals(Arrays.asList(amigos, familia, trabalho), gcont.getAllCircles(), "Lista de circulos incorreta");
	}
	
	@Test
	public void atualizandoCirculoExistente(){
		assertTrue(gcont.createCircle(FAMILIA, 3), "O circulo deve ser adicionado");
		assertTrue(gcont.updateCircle(new Circulo(FAMILIA, 4)), "O circulo deve ser atualizado");
		assertEquals(gcont.getCircle(FAMILIA), new Circulo(FAMILIA, 4), "O circulo nao foi atualizado corretamente");
	}
	
	@Test
	public void atualizandoCirculoLimiteInvalido(){
		assertTrue(gcont.createCircle(FAMILIA, 3), "O circulo deve ser adicionado");
		assertFalse(gcont.updateCircle(new Circulo(FAMILIA, 0)), "O circulo possui limite invalido");
	}
	
	@Test
	public void atualizandoCirculoInexistente(){
		assertFalse(gcont.updateCircle(new Circulo("inimigos", 4)), "Circulo nao existente");
		assertEquals(gcont.getCircle(FAMILIA), null, "Circulo nao foi cadastrado");
	}
}
