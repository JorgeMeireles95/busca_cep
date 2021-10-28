package cep;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Cep extends JFrame {

	private JPanel contentPane;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JTextField txtCidade;
	@SuppressWarnings("rawtypes")
	private JComboBox cboUf;
	private JLabel lblCheck;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cep frame = new Cep();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Cep() {
		setTitle("Buscar CEP");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cep.class.getResource("/img/home.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 300);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("CEP");
		lblNewLabel.setBounds(8, 34, 30, 17);
		contentPane.add(lblNewLabel);

		txtCep = new JTextField();
		txtCep.setBounds(67, 32, 128, 20);
		contentPane.add(txtCep);
		txtCep.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Endere\u00E7o");
		lblNewLabel_1.setBounds(8, 77, 46, 14);
		contentPane.add(lblNewLabel_1);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(64, 74, 284, 20);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Bairro");
		lblNewLabel_2.setBounds(8, 119, 46, 14);
		contentPane.add(lblNewLabel_2);

		txtBairro = new JTextField();
		txtBairro.setBounds(67, 116, 281, 20);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Cidade");
		lblNewLabel_3.setBounds(8, 168, 46, 14);
		contentPane.add(lblNewLabel_3);

		txtCidade = new JTextField();
		txtCidade.setBounds(67, 165, 197, 20);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("UF");
		lblNewLabel_4.setBounds(315, 168, 36, 14);
		contentPane.add(lblNewLabel_4);

		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "RO", "AC", "AM", "RR", "PA", "AP", "TO", "MA", "PI", "CE", "RN", "PB", "PE", "AL",
						"SE", "BA", "MG", "ES", "RJ", "SP", "PR,", "SC", "RS", "MS", "MT", "GO", "DF" }));
		cboUf.setBounds(361, 164, 63, 22);
		contentPane.add(cboUf);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(10, 227, 89, 23);
		contentPane.add(btnLimpar);

		JButton btnCep = new JButton("Buscar");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCep.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CEP, por favor");
					txtCep.requestFocus();

				} else {
					// Buscar cep.
					buscarCep();

				}

			}
		});
		btnCep.setBounds(381, 227, 89, 23);
		contentPane.add(btnCep);

		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				// Linkar as páginas.
				// A página principal está vendo a página sobre.
				sobre.setVisible(true);
			}
		});
		btnSobre.setToolTipText("Sobre");
		btnSobre.setIcon(new ImageIcon(Cep.class.getResource("/img/interrogation.png")));
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setBorder(null);
		btnSobre.setBackground(SystemColor.control);
		btnSobre.setBounds(434, 11, 48, 48);
		contentPane.add(btnSobre);

		// Uso da biblioteca atxy2k para o campo cep
		RestrictedTextField valida = new RestrictedTextField(txtCep);

		lblCheck = new JLabel("");
		lblCheck.setBounds(218, 18, 48, 48);
		contentPane.add(lblCheck);
		valida.setOnlyNums(true);
		valida.setLimit(8);
	}// fim dp construtor

	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			// ler um conteudo na web
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());

				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());

				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();

				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();

				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {
						lblCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/check_icon.png")));

					} else {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}

				}

			}
			// setar o campo endereco
			txtEndereco.setText(tipoLogradouro + " : " + logradouro);

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
