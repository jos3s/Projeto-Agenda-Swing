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
	private JPanel jpEmail=new JPanel();
	private JPanel jpBotoes=new JPanel();
	private JLabel jlNome= new JLabel("Nome: ");
	private JLabel jlEmail= new JLabel("Email: ");
	private JTextField jtfNome= new JTextField(15);
	private JTextField jtfEmail= new JTextField(15);
	private JButton btSalvar= new JButton("Salvar");
	private JButton btCancelar=new JButton("Cancelar");
	
	private Dados dados;

	public JanelaNovo(Dados dados) {
		this.dados=dados;
		renderizaJanela();
	}
	
	protected void renderizaJanela() {
		configuraJanela();
		cancelar();
		salvar();
	}
	
	@Override
	protected JPanel configuraPainel() {
		jpNome.setLayout(new FlowLayout());
		jpNome.add(jlNome);
		jpNome.add(jtfNome);
		jpEmail.setLayout(new FlowLayout());
		jpEmail.add(jlEmail);
		jpEmail.add(jtfEmail);
		jpBotoes.setLayout(new FlowLayout());
		jpBotoes.add(btCancelar);
		jpBotoes.add(btSalvar);
		
		painel.setLayout(new FlowLayout());
		painel.add(jpNome);
		painel.add(jpEmail);
		painel.add(jpBotoes);
		return painel;
	}

	@Override
	protected void configuraJanela() {
		add(configuraPainel());
		setSize(500,150);
		setTitle("Novo Contato");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	protected void exibirMensagem(String msg, String titulo, int opc) {
		switch(opc) {
			case 1:
				JOptionPane.showMessageDialog(null,msg,titulo,JOptionPane.INFORMATION_MESSAGE);
				break;
			case 2:
				JOptionPane.showMessageDialog(null,msg,titulo,JOptionPane.ERROR_MESSAGE);
				break;
		}
		
	}
	
	protected void salvar() {
		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvarArq();
			}
		});
	}
	
	private void salvarArq() {
		if(!jtfNome.getText().trim().isEmpty() && !jtfEmail.getText().trim().isEmpty() && Contato.eUmEmailValido(jtfEmail.getText())){
			String txt=jtfNome.getText()+"; "+jtfEmail.getText();
			dados.escreve(txt);
			exibirMensagem("Clique no bot�o 'Atualizar' para ver a altera��o","Informa��o",1);
			dispose();
		}else if(jtfNome.getText().trim().isEmpty()){
			exibirMensagem("O campo 'Nome' n�o pode estar vazio", "Erro no preenchimento dos dados",2);
		}else if(!jtfNome.getText().trim().isEmpty() && jtfEmail.getText().trim().isEmpty()){
			String txt=jtfNome.getText()+"; ";
			dados.escreve(txt);
			exibirMensagem("Clique no bot�o 'Atualizar' para ver a altera��o","Informa��o",1);
			dispose();
		}else if(!jtfNome.getText().trim().isEmpty() && !jtfEmail.getText().trim().isEmpty() && !Contato.eUmEmailValido(jtfEmail.getText())) {
			exibirMensagem("O email fornecido n�o � um email v�lido", "Erro no preenchimento dos dados",2);
		}
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
