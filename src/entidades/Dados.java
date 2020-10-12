package entidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Dados {
	 private BufferedReader br;
	 private File arq=new File("..\\Projeto Java Swing\\contatos");
	 private String fileName = arq.getAbsolutePath(); 
	 
	 public Dados() {
		 try {
			 this.br = new BufferedReader(new FileReader(fileName));
		 } catch (FileNotFoundException e) {	
			 JOptionPane.showMessageDialog(null,
					 "Arquivo com os dados não pode ser aberto",
					 "Erro na leitura do arquivo",
					 JOptionPane.ERROR_MESSAGE);
			 System.exit(0);
		 }	
	 }
	 
	 /*private void criarFWAndBW() {
		try {
			 this.fw=new FileWriter(this.arquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 this.bw = new BufferedWriter(fw);
	 }*/

	 /*public void criarDados() {
		 escrever("José Ulisses | (87)999999999");
		 escrever("Lucas Emanuel | (87)999999999");
		 escrever("Luis Gustavo | (87)999999999");
		 escrever("Franscisco Eudes | (87)999999999");
		 escrever("Jeferson Ribeiro | (87)999999999");
		 fechar();
	 }*/
	 
	 @SuppressWarnings("resource")
	 public void escreve(String txt) {		
		 try {
			 this.br = new BufferedReader(new FileReader(fileName));
			 StringBuilder sb = new StringBuilder(); 
			 String text;
			 try {
				 while((text = this.br.readLine()) != null){ 
					 sb.append(text+"\r\n");
				 }
			 } catch (IOException e1) {
				e1.printStackTrace();
			 }
			 sb.append(txt);
			 try {
				 br.close();
				 new FileOutputStream(fileName).write(sb.toString().getBytes());
			 } catch (IOException e) {
				 e.printStackTrace();
			 } 
			
		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
		 } 
	 }

	 public ArrayList<Contato> ler() {
		 ArrayList<String> linhas=new ArrayList<>();
		 try {
			this.br = new BufferedReader(new FileReader(fileName));
		 } catch (FileNotFoundException e) {		
			 JOptionPane.showMessageDialog(null,
					 "Arquivo com os dados não pode ser aberto",
					 "Erro na leitura do arquivo",
					 JOptionPane.ERROR_MESSAGE);
			 System.exit(0);
		 }	
		 try {
			 while(this.br.ready()){
				 String linha=br.readLine();
				 linhas.add(linha);
			 }
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 ArrayList<Contato> dados=new ArrayList<Contato>();
		 for(String s:linhas) {
			 String [] linhasDivididas=s.split(";\\s");
			 Contato c;
			 if(linhasDivididas.length>1) {
				 c=new Contato(linhasDivididas[0],linhasDivididas[1]);
			 }else {
				 c=new Contato(linhasDivididas[0]," ");
			 }
			 
			 dados.add(c);
		 }
		 try {
			 br.close();
		 } catch (IOException e) {
			e.printStackTrace();
		 }
		 return dados;
	 }

	 @SuppressWarnings("resource")
	 public void sobrescrever(List<Contato> list) {
	     StringBuilder sb = new StringBuilder(); 
	     for(Contato c: list){ 
	    	 sb.append(c.getNome() + "; "+c.getEmail()+"\r\n");
		 }
	     sb.append("");
		 try {
			 br.close();
		     new FileOutputStream(fileName).write(sb.toString().getBytes());
		 } catch (IOException e) {
	   		 e.printStackTrace();
		 }
 
	 }
	 
}

