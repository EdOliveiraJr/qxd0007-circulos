package cliente;

public abstract class CirculoBase{
	private String id;
	private int limite;

	public CirculoBase(String id, int limite){
		this.id = id;
		this.limite = limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public String getId() {
		return id;
	}

	public int getLimite() {
		return limite;
	}

	public abstract int getNumberOfContacts();

	// public String toString() {
	// 	return "circulo " + id +"/"+ limite;
	// }
}