
/*package visual;

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

	
	public static void main(String[] args) {
		try {
			ListaEnfermedades dialog = new ListaEnfermedades();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
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
*/

package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Enfermedad;
import logico.Paciente;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaEnfermedades extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JButton btnModi;
    private JButton btnCancelar;
    private JTable tabla;
    private Enfermedad select = null;
    private static Paciente miPaciente = null;
    private JButton btnElim;
    private static DefaultTableModel model;
    private static Object row[];
    private JButton btnNewButton;
    static String connectionUrl =
            "jdbc:sqlserver://192.168.100.118:1433;"
                    + "database=clinica_stanley_eduardo;"
                    + "user=s.gomez;" 
                    + "password=Headphone1130Jack;" 
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=30;";
    
    /**
	 * Launch the application.
	 */
    public static void main(String[] args) {
		try {
			ListaEnfermedades dialog = new ListaEnfermedades(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    /**
	 * Create the dialog.
	 * @return 
	 */

    public ListaEnfermedades(Paciente paciente) {
        Clinica.getInstance().setEnfermedadCodigo("");
        miPaciente = paciente;
		if(miPaciente != null)
			setTitle("Historial de Enfermedades de "+ miPaciente.getPersona().getNombre());
		else {
			setTitle("Listado de Enfermedades");
		}
        setBounds(100, 100, 733, 433);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        String[] headers = {"Código", "Nombre", "Descripción", "Permanente"};

        tabla = new JTable();
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = tabla.getSelectedRow();
                if (index >= 0) {
                    //btnElim.setEnabled(true);
                    //btnModi.setEnabled(true);
                    String codigo = tabla.getValueAt(index, 0).toString();
                    System.out.println(codigo);
                    select = Clinica.getInstance().buscarEnfermedad(codigo);
                    System.out.println(select.getId());
                }
            }
        });
        scrollPane.setViewportView(tabla);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(headers);
        tabla.setModel(model);

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
/*
        btnModi = new JButton("Modificar Datos");
        btnModi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModEnf enfermedadMod = new ModEnf(select);
                enfermedadMod.setModal(true);
                enfermedadMod.setLocationRelativeTo(null);
                enfermedadMod.setVisible(true);
                loadEnfermedades();
            }
        });
*/
        btnNewButton = new JButton("Seleccionar Enfermedad (Solo Consulta)");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (select != null) {
                    Clinica.getInstance().setEnfermedadCodigo(select.getId());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Disculpe, parece que no hay un valor seleccionado aqui para: \n" + Clinica.getEnfermedadCodigo() + "\n Por favor, seleccione una Enfermedad y intentalo de nuevo.\n", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        buttonPane.add(btnNewButton);
/*
        btnModi.setActionCommand("OK");
        buttonPane.add(btnModi);
        getRootPane().setDefaultButton(btnModi);

        btnElim = new JButton("Eliminar");
        btnElim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (select != null) {
                    int option = JOptionPane.showConfirmDialog(null, "Está Seguro(a) que desea eliminar esta enfermedad? " + select.getNombre(), "Confirmación", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        Clinica.getInstance().eliminarEnfermedad(select);
                        btnElim.setEnabled(false);
                        btnModi.setEnabled(false);
                        loadEnfermedades();
                    }
                }
            }
        });
        btnElim.setActionCommand("Cancel");
        buttonPane.add(btnElim);
*/
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(btnCancelar);

        loadEnfermedades();
    }

    public static void loadEnfermedades() {
        model.setRowCount(0);
        row = new Object[model.getColumnCount()];

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id_enfermedad, nombre, descripcion, permanente FROM enfermedad")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                row[0] = resultSet.getString("id_enfermedad");
                row[1] = resultSet.getString("nombre");
                row[2] = resultSet.getString("descripcion");
                row[3] = resultSet.getBoolean("permanente") ? "Sí" : "No";
                model.addRow(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data from the database. Please check your connection and try again.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

