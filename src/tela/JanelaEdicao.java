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
import entidades.Contatos;
import entidades.Dados;

@SuppressWarnings("serial")
public class JanelaEdicao extends Janela {

	private JPanel jpNome=new JPanel();
	private JPanel jpTelefone=new JPanel();
	private JPanel jpBotoes=new JPanel();
	private JLabel jlNome= new JLabel("Nome: ");
	private JLabel jlTelefone= new JLabel("Email: ");
	private JTextField jtfNome= new JTextField(15);
	private JTextField jftEmail= new JTextField(15);
	private JButton btSalvar= new JButton("Salvar");
	private JButton btCancelar=new JButton("Cancelar");
	
	private Contatos contatos;
	private int index;
	private Dados dados;
	private Contato cont;
	
	//public JanelaEdicao() {}
	public JanelaEdicao(Contatos contatos,int index, Dados dd) {
		this.contatos=contatos;
		this.index=index;
		this.dados=dd;
		this.cont=contatos.getContato(index);
		renderizaJanela();
	}
	
	@Override
	protected void renderizaJanela() {
		configuraJanela();
		cancelar();
		salvar();
	}

	@Override
	protected JPanel configuraPainel() {
		preencheCampos();
		jpNome.setLayout(new FlowLayout());
		jpNome.add(jlNome);
		jpNome.add(jtfNome);
		jpTelefone.setLayout(new FlowLayout());
		jpTelefone.add(jlTelefone);
		jpTelefone.add(jftEmail);
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

	protected void preencheCampos() {
		jtfNome.setText(cont.getNome());
		jftEmail.setText(cont.getEmail());
	}
	
	public void salvar() {
		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		
	}
	
	public void cancelar() {
		btCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public void editar() {
		if(!jtfNome.getText().trim().isEmpty() && !jftEmail.getText().trim().isEmpty() && Contato.eUmEmailValido(jftEmail.getText())){
			contatos.atualizarContato(index, jtfNome.getText(), jftEmail.getText());
			dados.sobrescrever(contatos.getContatos());
			exibirMensagem("Clique no botão 'Atualizar' para ver a alteração","Informação",1);
			dispose();
		}else if(jtfNome.getText().trim().isEmpty()){
			exibirMensagem("O campo 'Nome' não pode estar vazio","Erro no preenchimento dos dados",2);
		}else{
			exibirMensagem("O email fornecido não é válido","Erro no preenchimento dos dados",2);
		}
	}

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

}
