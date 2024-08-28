package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

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
/*
public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JTextField txtContrasena;

	
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

	
	
	public Login() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
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
			lblNewLabel.setBounds(38, 55, 126, 14);
			panel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Contrasena:");
			lblNewLabel_1.setBounds(38, 123, 147, 14);
			panel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnLogin = new JButton("Login");
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
*/


public class Login extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtUsuario;
    private JTextField txtContrasena;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
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

    public Login() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
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
            lblNewLabel.setBounds(38, 55, 126, 14);
            panel.add(lblNewLabel);

            JLabel lblNewLabel_1 = new JLabel("Contrasena:");
            lblNewLabel_1.setBounds(38, 123, 147, 14);
            panel.add(lblNewLabel_1);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnLogin = new JButton("Login");
                btnLogin.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String usuario = txtUsuario.getText();
                        String contrasena = txtContrasena.getText();

                        if (Clinica.getInstance().confirmLogin(usuario, contrasena)) {
                            Principal principal = new Principal();
                            dispose();
                            principal.setVisible(true);
                        } else {
                            // Optionally show a message to the user
                            System.out.println("Login failed. Please check your username and password.");
                        }
                    }
                });
                btnLogin.setActionCommand("OK");
                buttonPane.add(btnLogin);
                getRootPane().setDefaultButton(btnLogin);
            }
            {
                JButton btnCancelar = new JButton("Cancelar");
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