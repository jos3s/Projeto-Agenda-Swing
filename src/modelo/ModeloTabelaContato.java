package modelo;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entidades.Contato;
import entidades.Contatos;

@SuppressWarnings("serial")
public class ModeloTabelaContato extends AbstractTableModel  {
	
	private List<Contato> contatos;
	private List<String> colunas;
	
	public ModeloTabelaContato(Contatos dao) {
		this.colunas=Arrays.asList("Nome","Email");
		this.contatos=dao.getContatos();
	}

	@Override
	public int getColumnCount() {
		return colunas.size();
	}

	@Override
	public int getRowCount() {
		return contatos.size();
	}

	public String getColumnName(int column) {
		return this.colunas.get(column);
	}
	
	@Override
	public Object getValueAt(int indexLinha, int indexColuna) {
		Contato contato=contatos.get(indexLinha);
		if(indexColuna==0) {
			return contato.getNome();
		}else {
			return contato.getEmail();
		}
	}

	public boolean isCellEditable(int linha, int coluna) {
		return true;
	}
}
