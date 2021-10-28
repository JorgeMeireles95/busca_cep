package cep;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.URI;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Sobre extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Sobre dialog = new Sobre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setModal(true);
		setResizable(false);
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/home.png")));
		setBounds(150, 150, 450, 300);//
		// Trocando de 100 para 150 para se posicionar em cima da página original e
		// troca o modal no Design de false para true
		// assim impede que várias páginas sejam abertas ao mesmo tempo
		getContentPane().setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Buscar CEP - vers\u00E3o 1.0");
			lblNewLabel.setBounds(10, 23, 391, 14);
			getContentPane().add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("@Autor Jorge Meireles");
			lblNewLabel_1.setBounds(10, 54, 231, 14);
			getContentPane().add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("WEB Service:");
			lblNewLabel_2.setBounds(10, 120, 85, 14);
			getContentPane().add(lblNewLabel_2);
		}
		{
			JLabel lblWebService = new JLabel("republicvirtual.com.br");
			lblWebService.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					link("https://www.republicavirtual.com.br");
				}
			});
			lblWebService.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblWebService.setForeground(SystemColor.textHighlight);
			lblWebService.setBounds(105, 120, 136, 14);
			getContentPane().add(lblWebService);
		}
		{
			JButton btnGithub = new JButton("");
			btnGithub.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					link("https://github.com/JorgeMeireles95");
				}
			});
			btnGithub.setToolTipText("Github");
			btnGithub.setIcon(new ImageIcon(Sobre.class.getResource("/img/github_icon.png")));
			btnGithub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnGithub.setBorder(null);
			btnGithub.setBackground(SystemColor.control);
			btnGithub.setBounds(39, 183, 48, 48);
			getContentPane().add(btnGithub);
		} // fim do construtor

	}

	// metodo para abrir link externo no navegador de internet
	private void link(String site) {
		Desktop desktop  = Desktop.getDesktop();//Obtem o ambiente de interface grafica do usuario
		try {
			URI uri = new URI(site);
			desktop.browse(uri);
			
			//uri excuta no o navegador padrão da interface gráfica
		}catch(Exception e) {
			System.out.println(e);
		}
		

	}

}
