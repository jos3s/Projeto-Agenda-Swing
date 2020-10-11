package tela;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import entidades.Contato;
import entidades.Contatos;
import entidades.Dados;
import modelo.ModeloTabelaContato;

@SuppressWarnings("serial")
public class JanelaTabela extends Janela {
	
	private JPanel jpBotoes;
	private JButton btNovo=new JButton("Novo");
	private JButton btEditar=new JButton("Editar");
	private JButton btExcluir=new JButton("Excluir");
	private JButton btSair=new JButton("Sair");
	private JScrollPane scroller;
	private JTable tabela=new JTable();
	
	private Dados dados=new Dados();
	private Contatos dao=new Contatos(dados);
	private ModeloTabelaContato mTContato=new ModeloTabelaContato(dao);
	
	public JanelaTabela() {
		setTitle("Agenda");
		configuraJanela();
		sair();
		excluir(); 
		novo();
		editar();
	}
	
	public JPanel configurarPainelBotoes() {
		jpBotoes=new JPanel(new FlowLayout());
		jpBotoes.add(btSair);
		jpBotoes.add(btExcluir);
		jpBotoes.add(btEditar);
		jpBotoes.add(btNovo);
		return jpBotoes;
	}
	
	@Override
	protected JPanel configuraPainel() {
		painel.setLayout(new FlowLayout());
		painel.add(configurarPainelBotoes());
		painel.add(renderizaTabela());
		return painel;
	}

	@Override
	protected void configuraJanela() {
		add(configuraPainel());
		setSize(500,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	protected void renderizaJanela() {
	}
	
	public JScrollPane renderizaTabela() {
		this.tabela.setModel(this.mTContato);
		scroller = new JScrollPane(this.tabela);
		return scroller;
	}
	
	protected void atualizarTabela() {
		this.dao.atualizaDados(this.dados);
		
		tabela.revalidate();
	}
	
	protected void novo() {
		btNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JanelaNovo jn=new JanelaNovo(dados);
				atualizarTabela();
			}
		});
	}
	
	protected void editar() {
		btEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(dao.size()>0) {
					try {
						int row=tabela.getSelectedRow();
						try {
							new JanelaEdicao(dao, row, dados);
							atualizarTabela();
						}catch(Exception e2) {
							JOptionPane.showMessageDialog(
									null, 
									"Você precisa selecionar uma linha ou coluna da tabela", 
									"Erro de validação", 
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(
								null, 
								"Você precisa selecionar uma linha ou coluna da tabela", 
								"Erro de validação", 
								JOptionPane.ERROR_MESSAGE);
					} 
				}else {
					JOptionPane.showMessageDialog(
							null, 
							"Não é possivel editar, pois não há mais linha na tabela", 
							"Edição", 
							JOptionPane.INFORMATION_MESSAGE);
				}	
				
			}
		});
		
	}
	
	protected void excluir() {
		btExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row=tabela.getSelectedRow();
				if(row>-1) {
					try {
						int opc=JOptionPane.showConfirmDialog(
									null,
									"Deseja realmente excluir? ",
									"Exclusão",
									1,
									JOptionPane.WARNING_MESSAGE);
						if(opc==0) {
							dao.remove(row);
							tabela.revalidate();
							dados.sobrescrever(dao.getContatos());
						}
						tabela.clearSelection();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(
								null, 
								"Você precisa selecionar uma linha ou coluna da tabela", 
								"Erro de validação", 
								JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(
							null, 
							"Você precisa selecionar uma linha ou coluna da tabela", 
							"Erro de validação", 
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
	}
	
	protected void sair() {
		btSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

}
