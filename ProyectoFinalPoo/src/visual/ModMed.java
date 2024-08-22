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

import logico.Clinica;
import logico.Medico;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ModMed extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtCedu;
	private JTextField txtTel;
	private JTextField txtEsp;
	private JTextField txtDir;
	private JSpinner spnEdad;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			ModMed dialog = new ModMed();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ModMed(Medico aux) {
		setBounds(100, 100, 458, 307);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Nombre");
			lblNewLabel.setBounds(12, 13, 56, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(66, 13, 136, 22);
			contentPanel.add(txtNombre);
			txtNombre.setColumns(10);
			txtNombre.setText(aux.getPersona().getNombre());
		}
		{
			JLabel lblNewLabel_1 = new JLabel("C\u00E9dula");
			lblNewLabel_1.setBounds(12, 66, 56, 16);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtCedu = new JTextField();
			txtCedu.setBounds(66, 63, 136, 22);
			contentPanel.add(txtCedu);
			txtCedu.setColumns(10);
			txtCedu.setText(aux.getPersona().getCedula());
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Tel\u00E9fono");
			lblNewLabel_2.setBounds(12, 120, 56, 16);
			contentPanel.add(lblNewLabel_2);
		}
		{
			txtTel = new JTextField();
			txtTel.setBounds(75, 117, 127, 22);
			contentPanel.add(txtTel);
			txtTel.setColumns(10);
			txtTel.setText(aux.getPersona().getTelefono());
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Especialidad");
			lblNewLabel_3.setBounds(12, 172, 70, 16);
			contentPanel.add(lblNewLabel_3);
		}
		{
			txtEsp = new JTextField();
			txtEsp.setBounds(94, 169, 116, 22);
			contentPanel.add(txtEsp);
			txtEsp.setColumns(10);
			txtEsp.setText(String.valueOf(aux.getId_especialidad()));
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Edad");
			lblNewLabel_4.setBounds(253, 13, 48, 16);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Direcci\u00F3n");
			lblNewLabel_5.setBounds(253, 66, 56, 16);
			contentPanel.add(lblNewLabel_5);
		}
		{
			txtDir = new JTextField();
			txtDir.setBounds(253, 98, 176, 60);
			contentPanel.add(txtDir);
			txtDir.setColumns(10);
			txtDir.setText(aux.getPersona().getDireccion());
		}
		{
			spnEdad = new JSpinner();
			spnEdad.setModel(new SpinnerNumberModel(1, 1, 100, 1));
			spnEdad.setBounds(253, 31, 90, 22);
			contentPanel.add(spnEdad);
			//spnEdad.setValue(aux.getedad());
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnMod = new JButton("Modificar Datos");
				btnMod.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						aux.getPersona().setNombre(txtNombre.getText());
						aux.getPersona().setCedula(txtCedu.getText());
						aux.getPersona().setTelefono(txtTel.getText());
						aux.setId_especialidad(Integer.parseInt(txtEsp.getText()));
						//aux.setedad(Integer.valueOf(spnEdad.getValue().toString()));
						aux.getPersona().setDireccion(txtDir.getText());
						dispose();
					}
				});
				btnMod.setActionCommand("OK");
				buttonPane.add(btnMod);
				getRootPane().setDefaultButton(btnMod);
			}
		}
	}

}
