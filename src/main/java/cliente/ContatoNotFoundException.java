package cliente;

public class ContatoNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1600335032524758018L;

	private final String id;

	public ContatoNotFoundException(String contatoId) {
		this.id = contatoId;
	}

	public String getContatoNaoEncontrado() {
		return id;
	}
}
