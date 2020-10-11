package tela;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entidades.Contato;
import entidades.Dados;

@SuppressWarnings("serial")
public class JanelaNovo extends Janela {

	private JPanel jpNome=new JPanel();
	private JPanel jpTelefone=new JPanel();
	private JPanel jpBotoes=new JPanel();
	private JLabel jlNome= new JLabel("Nome: ");
	private JLabel jlTelefone= new JLabel("Telefone: ");
	private JTextField jtfNome= new JTextField(15);
	private JTextField jtfTelefone= new JTextField(15);
	private JButton btSalvar= new JButton("Salvar");
	private JButton btCancelar=new JButton("Cancelar");
	
	private Dados dados;
	//public JanelaEdicao() {}
	public JanelaNovo() {
		renderizaJanela();
	}
	public JanelaNovo(Dados dados) {
		this.dados=dados;
		renderizaJanela();
		cancelar();
		salvar();
	}
	
	@Override
	protected JPanel configuraPainel() {
		jpNome.setLayout(new FlowLayout());
		jpNome.add(jlNome);
		jpNome.add(jtfNome);
		jpTelefone.setLayout(new FlowLayout());
		jpTelefone.add(jlTelefone);
		jpTelefone.add(jtfTelefone);
		jpBotoes.setLayout(new FlowLayout());
		jpBotoes.add(btCancelar);
		jpBotoes.add(btSalvar);
		
		painel.setLayout(new FlowLayout());
		painel.add(jpNome);
		painel.add(jpTelefone);
		painel.add(jpBotoes);
		return painel;
	}

	@Override
	protected void configuraJanela() {
		add(configuraPainel());
		setSize(500,150);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	protected void renderizaJanela() {
		configuraJanela();
		cancelar();
	}
	
	protected void salvar() {
		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String txt=jtfNome.getText()+" | "+jtfTelefone.getText();
				dados.escrever(txt);
				JOptionPane.showMessageDialog(null,
						"Clique no botão 'Atualizar' para ver a alteração",
						"Informação",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
	}
	
	protected void cancelar() {
		btCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
