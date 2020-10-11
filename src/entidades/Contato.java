package entidades;

public class Contato {

	private String nome, telefone;
	
	public Contato() {}
	public Contato(String nome, String telefone) {
		this.nome=nome;
		this.telefone=telefone;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
