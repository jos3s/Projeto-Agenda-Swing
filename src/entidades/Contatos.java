package entidades;

import java.util.List;

public class Contatos {
	 private List<Contato> contatos;
	 
	 public Contatos(Dados dd) {
		 this.contatos=dd.ler();
	 }
	 public Contatos(List<Contato> contatos) {
		 this.contatos=contatos;
	 }
	 
	 public List<Contato> getContatos() {
		return contatos;
	 }
	 
	 public void setContatos(List<Contato> contatos) {
		 this.contatos=contatos;
	 }

	 public Contato getContato(int index) {
		 return this.contatos.get(index);
	 }
	 
	 public void remove(int index) {
		 this.contatos.remove(index);
	 }
	 
	 public void newContato(Contato c) {
		 this.contatos.add(c);
	 }

	 public void replaceContato(int index,Contato c) {
		 this.contatos.set(index, c);
	 }
	 
	 public int size() {
		 return this.contatos.size();
	 }

	 public void atualizaDados(Dados dd) {
		 this.contatos=dd.ler();
	 }
	
	 public void atualizarContato(int index,String nome, String email) {
		 this.contatos.get(index).setNome(nome);
		 this.contatos.get(index).setEmail(email);
	 }
}
