package cliente;

public abstract class ContatoBase{
	private String id;
	private String email;

	public ContatoBase(String id, String email) {
		this.id = id;
		this.email = email;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	// @Override
	// public String toString() {
	// 	return "contato " + id +"/"+ email;
	// }
}
