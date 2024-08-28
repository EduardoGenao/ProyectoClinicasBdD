package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import logico.Cuenta;
import logico.Buscar_Datos_Test;
import logico.Clinica;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.SystemColor;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JTextField txtContrasena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream clinica;
				FileOutputStream clinica2;
				ObjectInputStream clinicaRead;
				ObjectOutputStream clinicaWrite;
				try {
					clinica = new FileInputStream("clinica.dat");
					clinicaRead = new ObjectInputStream(clinica);
					Clinica temp = (Clinica)clinicaRead.readObject();
					Clinica.setClinic(temp);
					clinica.close();
					clinicaRead.close();
				} catch(FileNotFoundException e) {
					try {
						clinica2 = new FileOutputStream("clinica.dat");
						clinicaWrite = new ObjectOutputStream(clinica2);
						Cuenta aux = new Cuenta("999", "Messi", "GOAT");
						Clinica.getInstance().regAdmin(aux);
						Buscar_Datos_Test.loadCuentas();
						clinicaWrite.writeObject(Clinica.getInstance());
						clinica2.close();
						clinicaWrite.close();
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
					}
				} catch (IOException e) {
					
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				try {
					Login dialog = new Login();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
				
		});
	}

	
	/**
	 * Create the dialog.
	 */
	public Login() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 228, 181));
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			txtUsuario = new JTextField();
			txtUsuario.setBounds(38, 70, 182, 20);
			panel.add(txtUsuario);
			txtUsuario.setColumns(10);
			
			txtContrasena = new JPasswordField();
			txtContrasena.setBounds(38, 136, 182, 20);
			panel.add(txtContrasena);
			txtContrasena.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Usuario:");
			lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 20));
			lblNewLabel.setBounds(38, 49, 126, 20);
			panel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Contrasena:");
			lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 20));
			lblNewLabel_1.setBounds(38, 117, 147, 20);
			panel.add(lblNewLabel_1);
			
			JLabel lblImagen = new JLabel("New label");
			ImageIcon image = new ImageIcon(getClass().getResource("/IconoPersona.png"));
			lblImagen.setIcon(image);
			lblImagen.setBounds(270, 11, 124, 160);
			panel.add(lblImagen);
			
			JLabel lblLogin = new JLabel("Login");
			lblLogin.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
			lblLogin.setBounds(299, 171, 70, 23);
			panel.add(lblLogin);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 250, 205));
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnLogin = new JButton("Login");
				btnLogin.setFont(new Font("Segoe Print", Font.BOLD, 14));
				btnLogin.setBackground(SystemColor.activeCaptionBorder);
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(Clinica.getInstance().confirmLogin(txtUsuario.getText(), txtContrasena.getText()) && txtUsuario.getText()!= "" && txtContrasena.getText()!="") {
							Principal principal = new Principal();
							dispose();
							principal.setVisible(true);
						}
					}
				});
				btnLogin.setActionCommand("OK");
				buttonPane.add(btnLogin);
				getRootPane().setDefaultButton(btnLogin);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setBackground(SystemColor.activeCaptionBorder);
				btnCancelar.setFont(new Font("Segoe Print", Font.BOLD, 14));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
}