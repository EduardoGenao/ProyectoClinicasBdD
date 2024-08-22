package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Cuenta;
import logico.Clinica;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class RegAdmin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JTextField txtContrasena;
	private JTextField txtConfirmacion;
	private JTextField txt_id_cuenta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegAdmin dialog = new RegAdmin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegAdmin() {
		setTitle("Registrar Cuenta");
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
			{
				txtUsuario = new JTextField();
				txtUsuario.setBounds(30, 104, 147, 20);
				panel.add(txtUsuario);
				txtUsuario.setColumns(10);
			}
			{
				txtContrasena = new JTextField();
				txtContrasena.setBounds(30, 169, 147, 20);
				panel.add(txtContrasena);
				txtContrasena.setColumns(10);
			}
			{
				txtConfirmacion = new JTextField();
				txtConfirmacion.setBounds(237, 169, 147, 20);
				panel.add(txtConfirmacion);
				txtConfirmacion.setColumns(10);
			}
			{
				JLabel lblNewLabel = new JLabel("Usuario:");
				lblNewLabel.setBounds(30, 89, 172, 14);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Contrasena:");
				lblNewLabel_1.setBounds(30, 154, 147, 14);
				panel.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Confirmar Contrasena:");
				lblNewLabel_2.setBounds(237, 154, 147, 14);
				panel.add(lblNewLabel_2);
			}
			
			JLabel lblIdcuenta = new JLabel("id_cuenta:");
			lblIdcuenta.setBounds(30, 30, 172, 14);
			panel.add(lblIdcuenta);
			
			txt_id_cuenta = new JTextField();
			txt_id_cuenta.setColumns(10);
			txt_id_cuenta.setBounds(30, 45, 147, 20);
			panel.add(txt_id_cuenta);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCrear = new JButton("Registrar");
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!(txtUsuario.getText().isEmpty() || txtContrasena.getText().isEmpty() || txtConfirmacion.getText().isEmpty())
					            && txtContrasena.getText().equals(txtConfirmacion.getText())) {
							Cuenta admin = new Cuenta(txt_id_cuenta.getText(), txtUsuario.getText(), txtContrasena.getText());
							Clinica.getInstance().regAdmin(admin);
							JOptionPane.showMessageDialog(null, "Admin Creado!\n", "Admin", JOptionPane.INFORMATION_MESSAGE);
							clean();
						}
						else
							JOptionPane.showMessageDialog(null, "Disculpe, parece que faltan algunos datos en la registracion del administrador.\n Por favor, llene los datos que faltan e intenta la registracion de nuevo.\n", "Datos Ausentes", JOptionPane.INFORMATION_MESSAGE);
						}
				});
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
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
	
	private void clean() {
		txtUsuario.setText("");
		txtContrasena.setText("");
		txtConfirmacion.setText("");
	}
}
