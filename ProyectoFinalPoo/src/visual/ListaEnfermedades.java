package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Consulta;
import logico.Enfermedad;
import logico.Paciente;

import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;


public class ListaEnfermedades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private Object row[];
	private Enfermedad selected = null;
	private static Paciente miPaciente = null;
	private static JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			ListaEnfermedades dialog = new ListaEnfermedades();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	/**
	 * Create the dialog.
	 * @param paciente 
	 */
	public ListaEnfermedades(Paciente paciente) {
		Clinica.getInstance().setEnfermedadCodigo("");
		miPaciente = paciente;
		if(miPaciente != null)
			setTitle("Historial de Enfermedades de "+ miPaciente.getPersona().getNombre());
		else {
			setTitle("Listado de Enfermedades");
		}
		setBounds(100, 100, 588, 476);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					{
						String headers[]= {"ID","Nombre","Permanente"};
					    model = new DefaultTableModel();
						model.setColumnIdentifiers(headers);
						table = new JTable();
						table.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent arg0) {
								int value = table.getSelectedRow();
								if(value>=0) {
									btnNewButton.setEnabled(true);
									selected = Clinica.getInstance().buscarEnfermedad(table.getValueAt(value, 0).toString());
								}
							}
						});
						table.setModel(model);
						scrollPane.setViewportView(table);
					}
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setBackground(SystemColor.menu);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnVolver = new JButton("Volver");
				btnVolver.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					btnNewButton = new JButton("Seleccionar Enfermedad (Solo Consulta)");
					btnNewButton.setEnabled(false);
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(selected != null) {
								Clinica.getInstance().setEnfermedadCodigo(selected.getId());
								dispose();
							}
							else
								JOptionPane.showMessageDialog(null, "Disculpe, parece que no hay un valor seleccionado aqui para: \n" + Clinica.getEnfermedadCodigo() + "\n Por favor, seleccione una enfermedad y intentalo de nuevo.\n", "Error", JOptionPane.INFORMATION_MESSAGE);
						}
					});
					buttonPane.add(btnNewButton);
				}
				buttonPane.add(btnVolver);
			}
		}
		loadTable();
	}
	
	
	private void loadTable() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		if(miPaciente == null) {
				for(Enfermedad aux : Clinica.getInstance().getMisEnfermedades())
				{
					 row[0] = aux.getId();
					 row[1] = aux.getNombre();
					 if(aux.isPermanente() == true) {
						 row[2] = "Si";
					 }
					 else
						 row[2] = "No";
					 
					 model.addRow(row);
				}
		}
		else {
			for(Consulta aux : Clinica.getInstance().getMisConsultas()) {
				if(aux.getDiagnostico() == null)
					break;
				else {
				if(aux.getPaciente().getPersona().getCedula() == miPaciente.getPersona().getCedula() && aux.getDiagnostico() != null) {
					 row[0] = aux.getDiagnostico().getId();
					 row[1] = aux.getDiagnostico().getNombre();
					 if(aux.getDiagnostico().isPermanente() == true) {
						 row[2] = "Si";
					 }
					 else
						 row[2] = "No";
					 
					 model.addRow(row);
				}
				}
			}
			}
		
		}

}