package entidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contato {

	private String nome, email;
	
	public Contato() {}
	public Contato(String nome, String email) {
		this.nome=nome;
		this.email=email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public static boolean eUmEmailValido(String email) {
	    boolean eUmEmailValido = false;
	    if (email != null && email.length() > 0) {
	        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(email);
	        if (matcher.matches()) {
	        	eUmEmailValido = true;
	        }
	    }
	    return eUmEmailValido;
	}
}
