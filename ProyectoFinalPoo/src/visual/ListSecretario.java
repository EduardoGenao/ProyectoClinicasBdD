
/*package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ListSecretario extends JDialog {

	private final JPanel contentPanel = new JPanel();

	
	public static void main(String[] args) {
		try {
			ListSecretario dialog = new ListSecretario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public ListSecretario() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}

*/

/*
package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logico.Clinica;
import logico.Paciente;
import logico.Persona;
import logico.Secretario;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ListSecretario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static DefaultTableModel modelo;
	private static Object[] fila;
	private static int seleccion;
	private JComboBox cbxCondicion;
	static JLabel lblNewLabel_1;
	private static Secretario seleccionado = null;

	
	public static void main(String[] args) {
		try {
			ListSecretario dialog = new ListSecretario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public ListSecretario() {
		Clinica.getInstance().setSecretarioCedula("");
		setTitle("Secretarios");
        setBounds(100, 100, 1086, 439);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        contentPanel.add(panel);
                        panel.setLayout(null);
                
                        JScrollPane scrollPane = new JScrollPane();
                        scrollPane.setBounds(7, 39, 1043, 313);
                        panel.add(scrollPane);
                        table = new JTable(modelo) {
                            @Override
                            public boolean isCellEditable(int fila, int columna) {
                                return false;
                            }
                        };
                        scrollPane.setViewportView(table);
        
                JLabel lblNewLabel = new JLabel("Filtros de Busqueda:");
                lblNewLabel.setBounds(872, 17, 98, 14);
                panel.add(lblNewLabel);
        
                lblNewLabel_1 = new JLabel("Cantidad de Personas en Lista: 0");
                lblNewLabel_1.setBounds(7, 14, 296, 14);
                panel.add(lblNewLabel_1);

        modelo = new DefaultTableModel();
        String[] headers = {"id_persona", "id_secretario", "Cedula", "Nombre", "Apellido", "Nacimiento", "Sexo", "Direccion", "Telefono_Personal", "Telefono_Trabajo"};
        modelo.setColumnIdentifiers(headers);
        
                cbxCondicion = new JComboBox();
                cbxCondicion.setBounds(980, 14, 70, 20);
                cbxCondicion.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        seleccion = cbxCondicion.getSelectedIndex();
                        loadSecretarios(seleccion);
                    }
                });
                cbxCondicion.setModel(new DefaultComboBoxModel(new String[]{"<Todos>", "Hombres", "Mujeres"}));
                panel.add(cbxCondicion);

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancelar.setActionCommand("Cancel");
        buttonPane.add(btnCancelar);

        loadSecretarios(seleccion);
    }

    public static void loadSecretarios(int seleccion) {
        modelo.setRowCount(0);
        fila = new Object[modelo.getColumnCount()];

        switch (seleccion) {
            case 0:
                for (Secretario aux : Clinica.getInstance().getMisSecretarios()) {
                    fila[0] = aux.getPersona().getId_persona();
                    fila[1] = aux.getId_secretario();
                    fila[2] = aux.getPersona().getCedula();
                    fila[3] = aux.getPersona().getNombre();
                    fila[4] = aux.getPersona().getApellido();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String fechaNacimientoStr = dateFormat.format(aux.getPersona().getFechaDeNacim());
                    fila[5] = fechaNacimientoStr;
                    fila[6] = aux.getPersona().getSexo();
                    fila[7] = aux.getPersona().getDireccion();
                    fila[8] = aux.getPersona().getTelefono();
                    fila[9] = aux.getTelefono_trabajo();

                    modelo.addRow(fila);
                }
                break;
            
            //case 1: 
                // Implementación para Hombres
                //break;
            //case 2:
                // Implementación para Mujeres
                //break;
            
            //default:
                //break;
        }

        table.setModel(modelo);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(150);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(150);
        columnModel.getColumn(6).setPreferredWidth(80);
        columnModel.getColumn(7).setPreferredWidth(120);
        columnModel.getColumn(8).setPreferredWidth(150);
        columnModel.getColumn(9).setPreferredWidth(150);
        // lblNewLabel_1.setText("Cantidad de Pacientes en Lista: " + Clinica.getInstance().cantPacientes(seleccion));
    }
}


*/

package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logico.Clinica;
import logico.Secretario;

import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListSecretario extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private static JTable table;
    private static DefaultTableModel modelo;
    private static Object[] fila;
    private static int seleccion;
    private JComboBox<String> cbxCondicion;
    static JLabel lblNewLabel_1;
    JButton btnSeleccionar;
    private static Secretario seleccionado = null;

    public static void main(String[] args) {
        try {
            ListSecretario dialog = new ListSecretario();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ListSecretario() {
        setTitle("Secretarios");
        setBounds(100, 100, 1086, 439);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        contentPanel.add(panel);
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(7, 39, 1043, 313);
        panel.add(scrollPane);
        table = new JTable(modelo) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (table.getSelectedRow() >= 0) {
					int index = table.getSelectedRow();
					if(index >= 0) {
						btnSeleccionar.setEnabled(true);
						String cedula =  table.getValueAt(index, 2).toString();
						System.out.println(cedula);
						seleccionado = Clinica.getInstance().buscarSecretario(cedula);
						System.out.println(seleccionado.getPersona().getCedula());
					}
				}
        	}
        });
        scrollPane.setViewportView(table);

        JLabel lblNewLabel = new JLabel("Filtros de Busqueda:");
        lblNewLabel.setBounds(872, 17, 98, 14);
        panel.add(lblNewLabel);

        lblNewLabel_1 = new JLabel("Cantidad de Personas en Lista: 0");
        lblNewLabel_1.setBounds(7, 14, 296, 14);
        panel.add(lblNewLabel_1);

        modelo = new DefaultTableModel();
        String[] headers = {"id_persona", "id_secretario", "Cedula", "Nombre", "Apellido", "Nacimiento", "Sexo", "Direccion", "Telefono_Personal", "Telefono_Trabajo"};
        modelo.setColumnIdentifiers(headers);

        cbxCondicion = new JComboBox<>();
        cbxCondicion.setBounds(980, 14, 70, 20);
        cbxCondicion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seleccion = cbxCondicion.getSelectedIndex();
                loadSecretarios(seleccion);
            }
        });
        cbxCondicion.setModel(new DefaultComboBoxModel<>(new String[]{"<Todos>", "Hombres", "Mujeres"}));
        panel.add(cbxCondicion);

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        btnSeleccionar = new JButton("Seleccionar Secretario");
        btnSeleccionar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				if(seleccionado != null) {
					Clinica.getInstance().setSecretarioCedula(seleccionado.getPersona().getCedula());
					dispose();
				}
				else
					JOptionPane.showMessageDialog(null, "Disculpe, parece que no hay un valor seleccionado aqui para: \n" + Clinica.getPacienteCedula() + "\n Por favor, seleccione un paciente y intentalo de nuevo.\n", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
        });

        buttonPane.add(btnSeleccionar);
        btnCancelar.setActionCommand("Cancel");
        buttonPane.add(btnCancelar);

        loadSecretarios(seleccion);
    }

    public static void loadSecretarios(int seleccion) {
        modelo.setRowCount(0); // Clear the table
        fila = new Object[modelo.getColumnCount()];

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String connectionUrl =
                "jdbc:sqlserver://192.168.100.118:1433;"
                        + "database=clinica_stanley_eduardo;"
                        + "user=s.gomez;" // Your username
                        + "password=Headphone1130Jack;" // Your password
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
                        + "loginTimeout=30;";
        
        try {
            // Establish the connection
            connection = DriverManager.getConnection(connectionUrl);

            // Create the statement
            statement = connection.createStatement();

            // Define the base query to retrieve Secretario and Persona data
            String query = "SELECT s.id_secretario, p.id_persona, p.nombre, p.apellido, p.fecha_de_nacimiento, " +
                           "p.direccion, p.sexo, p.telefono_personal, p.cedula, s.telefono_trabajo " +
                           "FROM secretario s " +
                           "JOIN persona p ON s.id_persona = p.id_persona " +
                           "JOIN cuenta c ON s.id_cuenta = c.id_cuenta";

            // Adjust the query based on the selection
            if (seleccion == 1) {  // For "Hombres"
                query += " WHERE p.sexo = 'H'";
            } else if (seleccion == 2) {  // For "Mujeres"
                query += " WHERE p.sexo = 'M'";
            }

            // Execute the query
            resultSet = statement.executeQuery(query);

            // Process the results and populate the JTable
            while (resultSet.next()) {
                fila[0] = resultSet.getInt("id_persona");    // Persona ID
                fila[1] = resultSet.getInt("id_secretario"); // Secretario ID
                fila[2] = resultSet.getString("cedula");     // Cedula from Persona
                fila[3] = resultSet.getString("nombre");     // Nombre from Persona
                fila[4] = resultSet.getString("apellido");   // Apellido from Persona
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String fechaNacimientoStr = dateFormat.format(resultSet.getDate("fecha_de_nacimiento"));
                fila[5] = fechaNacimientoStr;                // Fecha de Nacimiento from Persona
                
                fila[6] = resultSet.getString("sexo");       // Sexo from Persona
                fila[7] = resultSet.getString("direccion");  // Direccion from Persona
                fila[8] = resultSet.getString("telefono_personal"); // Telefono Personal from Persona
                fila[9] = resultSet.getString("telefono_trabajo");  // Telefono Trabajo from Secretario

                modelo.addRow(fila);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data from the database", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Set the table model and configure the table
        table.setModel(modelo);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80);  // Persona ID
        columnModel.getColumn(1).setPreferredWidth(80);  // Secretario ID
        columnModel.getColumn(2).setPreferredWidth(120); // Cedula
        columnModel.getColumn(3).setPreferredWidth(150); // Nombre
        columnModel.getColumn(4).setPreferredWidth(150); // Apellido
        columnModel.getColumn(5).setPreferredWidth(150); // Fecha de Nacimiento
        columnModel.getColumn(6).setPreferredWidth(80);  // Sexo
        columnModel.getColumn(7).setPreferredWidth(120); // Direccion
        columnModel.getColumn(8).setPreferredWidth(150); // Telefono Personal
        columnModel.getColumn(9).setPreferredWidth(150); // Telefono Trabajo

        lblNewLabel_1.setText("Cantidad de Secretarios en Lista: " + modelo.getRowCount());
    }
}

