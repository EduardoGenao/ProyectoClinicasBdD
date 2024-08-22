package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logico.Vacuna;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModVac extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JTextField txtNombre;
	private JTextField txtCode;
	private JTextArea txtDes;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			ModVac dialog = new ModVac();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModVac(Vacuna aux) {
		setTitle("Modificar Vacunas");
		setBounds(100, 100, 288, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Nombre");
			lblNewLabel.setBounds(12, 78, 56, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(12, 107, 116, 22);
			contentPanel.add(txtNombre);
			txtNombre.setColumns(10);
			txtNombre.setText(aux.getNombre());
		}
		
		JLabel lblNewLabel_1 = new JLabel("C\u00F3digo");
		lblNewLabel_1.setBounds(12, 13, 56, 16);
		contentPanel.add(lblNewLabel_1);
		
		txtCode = new JTextField();
		txtCode.setEditable(false);
		txtCode.setBounds(12, 43, 76, 22);
		contentPanel.add(txtCode);
		txtCode.setColumns(10);
		txtCode.setText(aux.getId_vacuna());
		
		JLabel lblNewLabel_2 = new JLabel("Descripci\u00F3n");
		lblNewLabel_2.setBounds(12, 142, 88, 16);
		contentPanel.add(lblNewLabel_2);
		
		txtDes = new JTextArea();
		txtDes.setBounds(12, 160, 191, 40);
		txtDes.setText(aux.getDescrip());
		contentPanel.add(txtDes);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAceptar = new JButton("Aceptar");
				btnAceptar.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						aux.setId_vacuna(txtCode.getText());
						aux.setNombre(txtNombre.getText());
						aux.setDescrip(txtDes.getText());
						dispose();
					}
				});
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(btnCancelar);
			}
		}
	}
}
