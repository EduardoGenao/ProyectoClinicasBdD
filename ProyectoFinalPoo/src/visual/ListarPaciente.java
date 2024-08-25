package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logico.Clinica;
import logico.Paciente;
import logico.Persona;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.TitledBorder;

public class ListarPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static DefaultTableModel modelo;
	private static Object[] fila;
	private static int seleccion;
	private JComboBox cbxCondicion;
	private static JButton btnSeleccionar;
	private static JButton btnModificar;
	private static JButton btnConsultaHistorial;
	static JLabel lblNewLabel_1;
	private static Paciente seleccionado = null;
	
	static String connectionUrl =
            "jdbc:sqlserver://192.168.100.118:1433;"
                    + "database=clinica_stanley_eduardo;"
                    + "user=s.gomez;" //TU USER
                    + "password=Headphone1130Jack;" //TU CLAVE
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=30;";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarPaciente dialog = new ListarPaciente();
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
	public ListarPaciente() {
		Clinica.getInstance().setPacienteCedula("");
		setTitle("Listado de Pacientes");
		setBounds(100, 100, 1190, 487);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 40, 1144, 311);
			panel.add(scrollPane);
			
			lblNewLabel_1 = new JLabel("Cantidad de Pacientes en Lista: " + Clinica.getInstance().cantPacientes(seleccion));
			lblNewLabel_1.setBounds(10, 13, 304, 14);
			panel.add(lblNewLabel_1);
			
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
							btnModificar.setEnabled(true);
							btnConsultaHistorial.setEnabled(true);
							btnSeleccionar.setEnabled(true);
							String cedula =  table.getValueAt(index, 2).toString();
							System.out.println(cedula);
							seleccionado = Clinica.getInstance().buscarPaciente(cedula);
							System.out.println(seleccionado.getPersona().getCedula());
						}
					}
				}
			});
			modelo = new DefaultTableModel();
			String[] headers = {"id_paciente", "id_persona", "Cedula", "Nombre", "Apellido", "Nacimiento","Sexo","Sangre", "Direccion", "Telefono",  "Telefono de Contacto Em."};
			table.setModel(modelo);
			modelo.setColumnIdentifiers(headers);
			loadPacientes(0);
			scrollPane.setViewportView(table);
			
			cbxCondicion = new JComboBox();
			cbxCondicion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seleccion = cbxCondicion.getSelectedIndex();
					loadPacientes(seleccion);
				}
			});
			cbxCondicion.setModel(new DefaultComboBoxModel(new String[] {"<Todos>", "Hombres", "Mujeres"}));
			cbxCondicion.setBounds(995, 9, 159, 20);
			panel.add(cbxCondicion);
			
			JLabel lblNewLabel = new JLabel("Filtros de Busqueda:");
			lblNewLabel.setBounds(845, 12, 138, 14);
			panel.add(lblNewLabel);
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegPaciente pacienteMod = new RegPaciente(seleccionado, seleccionado.getPersona());
						pacienteMod.setModal(true);
						pacienteMod.setLocationRelativeTo(null);
						pacienteMod.setVisible(true);
					}
				});
				
				btnConsultaHistorial = new JButton("Ver Consulta Historial");
				btnConsultaHistorial.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						ListConsultas list = new ListConsultas(seleccionado);
						list.setModal(true);
						list.setLocationRelativeTo(null);
						list.setVisible(true);
					}
				});
				
				btnSeleccionar = new JButton("Seleccionar Paciente");
				btnSeleccionar.setEnabled(false);
				btnSeleccionar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(seleccionado != null) {
							Clinica.getInstance().setPacienteCedula(seleccionado.getPersona().getCedula());
							dispose();
						}
						else
							JOptionPane.showMessageDialog(null, "Disculpe, parece que no hay un valor seleccionado aqui para: \n" + Clinica.getPacienteCedula() + "\n Por favor, seleccione un paciente y intentalo de nuevo.\n", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				});
				buttonPane.add(btnSeleccionar);
				btnConsultaHistorial.setEnabled(false);
				buttonPane.add(btnConsultaHistorial);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				btnModificar.setEnabled(false);
				buttonPane.add(btnCancelar);
			}
		}
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Historial M\u00E9dico");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Enfermedades");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaEnfermedades list = new ListaEnfermedades(seleccionado);
				list.setModal(true);
				list.setLocationRelativeTo(null);
				list.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Vacunas");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListVac list = new ListVac(seleccionado);
				list.setModal(true);
				list.setLocationRelativeTo(null);
				list.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		loadPacientes(seleccion);
	}

	public static void loadPacientes(int seleccion) {
		modelo.setRowCount(0);
		fila = new Object[modelo.getColumnCount()];
		/*
		try (Connection connection = DriverManager.getConnection(connectionUrl)) {
		
		String pacienteQuery = "SELECT * FROM paciente";
        String personaQuery = "SELECT * FROM persona";
		
		Statement pacienteStatement = connection.createStatement();
        Statement personaStatement = connection.createStatement();
		
		ResultSet pacienteResultSet = pacienteStatement.executeQuery(pacienteQuery);
        ResultSet personaResultSet = personaStatement.executeQuery(personaQuery);
        
        
        /*
        switch (seleccion){
        case 0:
        	while (pacienteResultSet.next()) {
                int idPaciente = pacienteResultSet.getInt("id_paciente");
                String sangre = pacienteResultSet.getString("sangre");
                String contactoEmergencia = pacienteResultSet.getString("contacto_emergencia");
                int idPersona = pacienteResultSet.getInt("id_persona");
                System.out.println("ID: " + idPaciente + ", Sangre: " + sangre + 
                                   ", Contacto Emergencia: " + contactoEmergencia + 
                                   ", ID Persona: " + idPersona);
                fila[0] = idPaciente;
                fila[1] = sangre;
                fila[2] = contactoEmergencia;
                fila[3] = idPersona;
                modelo.addRow(fila);
            }
        	break;
        default:
        	break;
        }
		}
        	catch (SQLException e) {
                e.printStackTrace();
            }
		*/
		
		switch(seleccion){
		case 0:
			/*
			while (pacienteResultSet.next()) {
                fila[0] = pacienteResultSet.getInt("id_paciente");
                fila[1] = pacienteResultSet.getString("sangre");
                String contactoEmergencia = pacienteResultSet.getString("contacto_emergencia");
                int idPersona = pacienteResultSet.getInt("id_persona");
                System.out.println("ID: " + idPaciente + ", Sangre: " + sangre + 
                                   ", Contacto Emergencia: " + contactoEmergencia + 
                                   ", ID Persona: " + idPersona);
            }
			fila[0] = aux.getIdPaciente();
			fila[1] = aux.getPersona().getNombre();
			fila[2] = aux.getPersona().getSexo();
			fila[3] = fechaNacimientoStr;
			*/
			for(Paciente aux : Clinica.getInstance().getMisPacientes()) {
					fila[0] = aux.getIdPaciente();
					fila[1] = aux.getPersona().getId_persona();
					fila[2] = aux.getPersona().getCedula();
					fila[3] = aux.getPersona().getNombre();
					fila[4] = aux.getPersona().getApellido();
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String fechaNacimientoStr = dateFormat.format(aux.getPersona().getFechaDeNacim());
					fila[5] = fechaNacimientoStr;
					fila[6] = aux.getPersona().getSexo();
					fila[7] = aux.getSangre();
					fila[8] = aux.getPersona().getDireccion();
					fila[9] = aux.getPersona().getTelefono();
					fila[10] = aux.getContacto_emergencia();
					
					modelo.addRow(fila);
			}
			break;
		case 1: 
			for(Paciente aux : Clinica.getInstance().getMisPacientes()) {
				if (aux.getPersona().getSexo() == "H") {
					fila[0] = aux.getIdPaciente();
					fila[1] = aux.getPersona().getId_persona();
					fila[2] = aux.getPersona().getCedula();
					fila[3] = aux.getPersona().getNombre();
					fila[4] = aux.getPersona().getApellido();
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String fechaNacimientoStr = dateFormat.format(aux.getPersona().getFechaDeNacim());
					fila[5] = fechaNacimientoStr;
					fila[6] = aux.getPersona().getSexo();
					fila[7] = aux.getSangre();
					fila[8] = aux.getPersona().getDireccion();
					fila[9] = aux.getPersona().getTelefono();
					fila[10] = aux.getContacto_emergencia();
					
					modelo.addRow(fila);
				}
			}
			break;
		case 2:
			for(Paciente aux : Clinica.getInstance().getMisPacientes()) {
				if (aux.getPersona().getSexo() == "M") {
					fila[0] = aux.getIdPaciente();
					fila[1] = aux.getPersona().getId_persona();
					fila[2] = aux.getPersona().getCedula();
					fila[3] = aux.getPersona().getNombre();
					fila[4] = aux.getPersona().getApellido();
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String fechaNacimientoStr = dateFormat.format(aux.getPersona().getFechaDeNacim());
					fila[5] = fechaNacimientoStr;
					fila[6] = aux.getPersona().getSexo();
					fila[7] = aux.getSangre();
					fila[8] = aux.getPersona().getDireccion();
					fila[9] = aux.getPersona().getTelefono();
					fila[10] = aux.getContacto_emergencia();
					
					modelo.addRow(fila);
				}
			}
			break;
		default:
			break;
		}
		
		table.setModel(modelo);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(80);
		columnModel.getColumn(1).setPreferredWidth(80);
		columnModel.getColumn(2).setPreferredWidth(150);
		columnModel.getColumn(3).setPreferredWidth(150);
		columnModel.getColumn(4).setPreferredWidth(150);
		columnModel.getColumn(5).setPreferredWidth(80);
		columnModel.getColumn(6).setPreferredWidth(60);
		columnModel.getColumn(7).setPreferredWidth(60);
		columnModel.getColumn(8).setPreferredWidth(150);
		columnModel.getColumn(9).setPreferredWidth(150);
		columnModel.getColumn(10).setPreferredWidth(150);
		//lblNewLabel_1.setText("Cantidad de Pacientes en Lista: " + Clinica.getInstance().cantPacientes(seleccion));
		
	}
}

