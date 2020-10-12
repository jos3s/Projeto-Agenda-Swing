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
	private JButton btAtualizar=new JButton("Atualizar");
	private JScrollPane scroller;
	private JTable tabela=new JTable();
	
	private Dados dados=new Dados();
	private Contatos dao=new Contatos(dados);
	private ModeloTabelaContato mTContato=new ModeloTabelaContato(dao);
	
	public JanelaTabela() {
		renderizaJanela();
	}
	
	@Override
	protected void renderizaJanela() {
		configuraJanela();
		sair();
		excluir(); 
		novo();
		editar();
		atualizar();
	}
	
	protected JPanel configurarPainelBotoes() {
		jpBotoes=new JPanel(new FlowLayout());
		jpBotoes.add(btSair);
		jpBotoes.add(btAtualizar);
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
		setTitle("Agenda");
		setSize(500,525);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	protected JScrollPane renderizaTabela() {
		this.tabela.setModel(this.mTContato);
		scroller = new JScrollPane(this.tabela);
		return scroller;
	}
	
	@Override
	public void exibirMensagem(String msg, String titulo, int opc) {
		switch(opc) {
			case 1:
				JOptionPane.showMessageDialog(null,msg,titulo,JOptionPane.INFORMATION_MESSAGE);
				break;
			case 2:
				JOptionPane.showMessageDialog(null,msg,titulo,JOptionPane.ERROR_MESSAGE);
				break;
		}
		
	}
	
	protected void atualizarTabela() {
		this.dao.atualizaDados(this.dados);
		this.mTContato=new ModeloTabelaContato(this.dao);
		this.tabela.setModel(mTContato);
		this.scroller=new JScrollPane(this.tabela);
		tabela.revalidate();
	}
	
	protected void novo() {
		btNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new JanelaNovo(dados);
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
							
						}catch(Exception e2) {
							exibirMensagem("Você precisa selecionar uma linha ou coluna da tabela","Erro de validação", 2);
						}
					} catch (Exception e2) {
						exibirMensagem("Você precisa selecionar uma linha ou coluna da tabela", "Erro de validação",2);
					} 
				}else {
					exibirMensagem("Não é possivel editar, pois não há mais linha na tabela","Edição",1);
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
				}else if(tabela.getRowCount()>0) {
					exibirMensagem("Você precisa selecionar uma linha da tabela", "Erro de validação",2);
				}else {
					exibirMensagem("Não há mais linha na tabela", "Erro de validação",2);
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

	public void atualizar() {
		btAtualizar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				painel.removeAll();
				atualizarTabela();
				configuraJanela();
			}
		});
	}

}
