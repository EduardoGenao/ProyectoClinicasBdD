package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Medico;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListMed extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnCancel;
	private static JTable Tabla;
	private Medico selected = null;
	private static DefaultTableModel model;
	private static Object row[];
	private static JButton btnSeleccionar;
	private static RegMed med = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListMed dialog = new ListMed();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListMed() {
		Clinica.getInstance().setMedicoCedula("");
		setTitle("Listar M\u00E9dicos");
		setBounds(100, 100, 843, 329);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				String[] headers = {"Nombre","Apellido", "Cédula","Especialidad","Sexo","Dirección","Teléfono"};
				
				Tabla = new JTable();
				Tabla.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int ind = Tabla.getSelectedRow();
						if (ind >= 0) {
							//btnElim.setEnabled(true);
							//btnModi.setEnabled(true);
							btnSeleccionar.setEnabled(true);
							selected = Clinica.getInstance().buscarMedico(Tabla.getValueAt(ind,2).toString());
							//String cedula = Tabla.getValueAt(ind, 0).toString();
							//selected = Clinica.getInstance().buscarMedico(cedula);
							
							
						}
					}
				});
				
				
				scrollPane.setViewportView(Tabla);
				
				model = new DefaultTableModel();
				model.setColumnIdentifiers(headers);
				Tabla.setModel(model);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				{
					btnSeleccionar = new JButton("Seleccionar Medico");
					btnSeleccionar.setEnabled(false);
					btnSeleccionar.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if(selected != null) {
								Clinica.getInstance().setMedicoCedula(selected.getPersona().getCedula());
								dispose();
							}
							else
								JOptionPane.showMessageDialog(null, "Disculpe, parece que no hay un valor seleccionado aqui para: \n" + Clinica.getMedicoCedula() + "\n Por favor, seleccione un medico y intentalo de nuevo.\n", "Error", JOptionPane.INFORMATION_MESSAGE);
						}
					});
					buttonPane.add(btnSeleccionar);
				}
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
		loadMedicos();
	}

	public static void loadMedicos() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Medico aux : Clinica.getInstance().getMisMedicos()) {
			if(aux != null){
			row[0] = aux.getPersona().getNombre();
			row[1] = aux.getPersona().getApellido();
			row[2] = aux.getPersona().getCedula();
			row[3] = aux.getId_especialidad();
			row[4] = aux.getPersona().getSexo();
			//if(aux.getPersona().getSexo()) {
				//row[3]= "M";
			//}else{
				//row[3] = "F";
			//}
			row[5] = aux.getPersona().getDireccion();
			row[6] = aux.getTelefono_trabajo();
			model.addRow(row);
		  }
	
		
		}
	}

}

