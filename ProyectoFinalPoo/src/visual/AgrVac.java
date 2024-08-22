package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import logico.Clinica;
import logico.Medico;
import logico.Vacuna;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JTextPane;

public class AgrVac extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnAgr;
	private JButton btnCancel;
	private JTextField txtCod;
	private JTextField txtNombre;
	private JTextPane txtDescrip;
	private boolean control;
	private static Vacuna vac=null;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AgrVac dialog = new AgrVac(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AgrVac(Vacuna vac) {
		setTitle("Agregar Vacuna al Inventario");
		setBounds(100, 100, 341, 252);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("C\u00F3digo");
			lblNewLabel.setBounds(12, 13, 56, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			txtCod = new JTextField();
			txtCod.setEditable(false);
			txtCod.setText("Vac-"+Clinica.getInstance().codigo);
			txtCod.setBounds(12, 36, 73, 22);
			contentPanel.add(txtCod);
			txtCod.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nombre");
			lblNewLabel_1.setBounds(188, 13, 56, 16);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(188, 36, 116, 22);
			contentPanel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Descripci\u00F3n");
			lblNewLabel_2.setBounds(12, 85, 73, 16);
			contentPanel.add(lblNewLabel_2);
		}
		
		txtDescrip = new JTextPane();
		txtDescrip.setBounds(12, 107, 199, 45);
		contentPanel.add(txtDescrip);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAgr = new JButton("Agregar Vacuna");
				btnAgr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!control) {
							Vacuna aux = new Vacuna(txtCod.getText(),txtNombre.getText(),txtDescrip.getText(),0);
							Clinica.getInstance().insertarVacuna(aux);
						    JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Registro", JOptionPane.INFORMATION_MESSAGE);				  
						    clear();
						}else {
							vac.setNombre(txtNombre.getText());
							vac.setDescrip(txtDescrip.getText());
							Clinica.getInstance().modificarVac(vac);
							dispose();
							ListVac.loadVacunas();
							
						}
						
					}
				});
				btnAgr.setActionCommand("OK");
				buttonPane.add(btnAgr);
				getRootPane().setDefaultButton(btnAgr);
			}
			{
				btnCancel = new JButton("Cancelar");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		loadVacuna();
	}
	
	public void loadVacuna() {
		if(vac != null) {
			txtCod.setText(vac.getId_vacuna());
			txtNombre.setText(vac.getNombre());
			txtDescrip.setText(vac.getDescrip());		}
	}

	protected void clear() {
		txtCod.setText("Vac-"+Clinica.getInstance().codigo);
		txtNombre.setText("");
		txtDescrip.setText("");
		
		
	}
}
