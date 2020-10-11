package tela;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class Janela extends JFrame {
	 protected JPanel painel=new JPanel();
	 
	 protected abstract JPanel configuraPainel();
	 protected abstract void configuraJanela();
	 protected abstract void renderizaJanela();
	 
}
