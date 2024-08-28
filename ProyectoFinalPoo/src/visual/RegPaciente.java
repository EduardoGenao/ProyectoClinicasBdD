package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Buscar_Datos_Test;
import logico.Clinica;
import logico.Paciente;
import logico.Persona;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;

public class RegPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPersonaID = new JTextField();
	private JTextField txtPacienteID= new JTextField();
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtTelefono;
	private JTextField txtDir;
	private JTextField txtNumeroEmer;
	private String sexo = "H";
	JSpinner spnEdad;
	JComboBox<String> cbxSexo;
	private Paciente miPaciente = null;
	private Persona miPersona = null;
	private JTextField txtApellido;
	JComboBox<String> cbxSangre;
	int nuevoPersonaID = 0;
	static String connectionUrl =
            "jdbc:sqlserver://192.168.100.118:1433;"
                    + "database=clinica_stanley_eduardo;"
                    + "user=s.gomez;" //TU USER
                    + "password=Headphone1130Jack;" //TU CLAVE
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=30;";
	

	
	 public static void main(String[] args) {
	        try {
	            RegPaciente dialog = new RegPaciente(null, null);
	            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	            dialog.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	   
	
	public RegPaciente(Paciente paciente, Persona persona) {
		setTitle("Registrar Paciente");
		miPaciente = paciente;	
		miPersona = persona;
		
		setResizable(false);
		if(miPaciente == null) {
			setTitle("Registrar Paciente");
		}
		else {
			setTitle("Modificar Paciente");
		}
		setBounds(100, 100, 960, 302);
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
			
			txtNombre = new JTextField();
			txtNombre.setBounds(26, 42, 148, 20);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Nombre:");
			lblNewLabel.setBounds(26, 27, 117, 14);
			panel.add(lblNewLabel);
			
			txtCedula = new JTextField();
			txtCedula.setColumns(10);
			txtCedula.setBounds(26, 162, 148, 20);
			panel.add(txtCedula);
			
			JLabel lblCedula = new JLabel("Cedula:");
			lblCedula.setBounds(26, 147, 117, 14);
			panel.add(lblCedula);
			
			txtTelefono = new JTextField();
			txtTelefono.setColumns(10);
			txtTelefono.setBounds(461, 42, 168, 20);
			panel.add(txtTelefono);
			
			JLabel lblNumeroDeTelefono = new JLabel("Numero de Telefono:");
			lblNumeroDeTelefono.setBounds(461, 27, 174, 14);
			panel.add(lblNumeroDeTelefono);
			
			txtDir = new JTextField();
			txtDir.setColumns(10);
			txtDir.setBounds(219, 162, 168, 20);
			panel.add(txtDir);
			
			JLabel lblDireccion = new JLabel("Direccion:");
			lblDireccion.setBounds(219, 147, 117, 14);
			panel.add(lblDireccion);
			
			SpinnerDateModel modelo = new SpinnerDateModel();
	        spnEdad = new JSpinner(new SpinnerDateModel(new Date(), null, new Date(), Calendar.DAY_OF_MONTH));
	        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnEdad, "dd/MM/yyyy");
	        spnEdad.setEditor(dateEditor);
	        spnEdad.setBounds(219, 42, 168, 20);
	        panel.add(spnEdad);
			
			JLabel lblNewLabel_1 = new JLabel("Fecha de Nacimiento:");
			lblNewLabel_1.setBounds(219, 27, 168, 14);
			panel.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Sexo:");
			lblNewLabel_2.setBounds(219, 87, 46, 14);
			panel.add(lblNewLabel_2);
			
			JLabel lblNumeroDeCont = new JLabel("Numero de Contacto de ER:");
			lblNumeroDeCont.setBounds(461, 87, 168, 14);
			panel.add(lblNumeroDeCont);
			
			txtNumeroEmer = new JTextField();
			txtNumeroEmer.setColumns(10);
			txtNumeroEmer.setBounds(461, 102, 168, 20);
			panel.add(txtNumeroEmer);
			
			String[] sexos = {"Hombre", "Mujer"};
			cbxSexo = new JComboBox(sexos);
			cbxSexo.setModel(new DefaultComboBoxModel(new String[] {"Hombre", "Mujer"}));
			cbxSexo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(e.getSource()==cbxSexo) {
						if(cbxSexo.getSelectedItem().equals("Hombre")) {
							sexo = "H";
						}
						if(cbxSexo.getSelectedItem().equals("Mujer")) {
							sexo = "M";
						}
						//Para probar que el valor correcto del char estaba funcionando
						//JOptionPane.showMessageDialog(null, "Sexo es " + sexo + "!", "Sexo", JOptionPane.INFORMATION_MESSAGE); 
					}
				}
			});
			cbxSexo.setBounds(219, 102, 168, 20);
			panel.add(cbxSexo);
			
			txtApellido = new JTextField();
			txtApellido.setBounds(26, 102, 148, 20);
			panel.add(txtApellido);
			txtApellido.setColumns(10);
			
			JLabel lblNewLabel_5 = new JLabel("Apellido");
			lblNewLabel_5.setBounds(26, 87, 46, 14);
			panel.add(lblNewLabel_5);
			String personaIDString = Buscar_Datos_Test.getHighestUnoccupiedPersonaId();
			String pacienteIDString = Buscar_Datos_Test.getHighestUnoccupiedPacienteId();
			
			cbxSangre = new JComboBox(new Object[]{});
			cbxSangre.setModel(new DefaultComboBoxModel(new String[] {"A+", "B+", "AB+", "O+", "A-", "B-", "AB-", "O-"}));
			cbxSangre.setBounds(694, 42, 168, 20);
			panel.add(cbxSangre);
			
			JLabel lblNewLabel_8 = new JLabel("Sangre:");
			lblNewLabel_8.setBounds(694, 27, 78, 14);
			panel.add(lblNewLabel_8);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Date fechaNacim = (Date) spnEdad.getValue();
						if (txtNombre.getText().isEmpty() || txtCedula.getText().isEmpty() || txtTelefono.getText().isEmpty() || txtDir.getText().isEmpty() || 
							    txtNumeroEmer.getText().isEmpty() || (sexo != "H" && sexo != "M")) {
							JOptionPane.showMessageDialog(null, "Disculpe, parece que faltan algunos datos en la registracion del paciente. Por favor, llene los datos que faltan e intenta la registracion de nuevo.\n", "Datos Ausentes", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							if (miPaciente == null) {
									SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
									String fechaNacimientoStr = dateFormat.format(fechaNacim);
								
									
									//miPaciente = new Paciente(txtNombre.getText(), txtCedula.getText(), txtDir.getText(), fechaNacim, sexo, txtTelefono.getText(), false, txtContactoEmer.getText(), txtNumeroEmer.getText(), txtAlergias.getText());
									txtPersonaID.setText("5");
									miPersona = new Persona(Integer.parseInt(txtPersonaID.getText()), txtNombre.getText(), txtApellido.getText(), fechaNacim, txtDir.getText(), sexo, txtTelefono.getText(), txtCedula.getText());
									try {
										nuevoPersonaID = Buscar_Datos_Test.createPersona(miPersona);
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									txtPacienteID.setText("5");
									miPaciente = new Paciente(Integer.parseInt(txtPacienteID.getText()), (String) cbxSangre.getSelectedItem(), txtNumeroEmer.getText(), miPersona);
									miPaciente.getPersona().setId_persona(nuevoPersonaID);
									System.out.println("miPaciente Persona id is " + miPaciente.getPersona().getId_persona());
									try {
										Buscar_Datos_Test.createPaciente(miPaciente);
										System.out.println("Paciente creado!");
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									Clinica.getInstance().insertarPaciente(miPaciente);
									JOptionPane.showMessageDialog(null, "Paciente Registrado!\n", "Registracion!", JOptionPane.INFORMATION_MESSAGE); 
									clean();
								}
							else {
									miPaciente.getPersona().setNombre(txtNombre.getText());
									miPaciente.getPersona().setApellido(txtApellido.getText());
									miPaciente.getPersona().setCedula(txtCedula.getText());
									miPaciente.getPersona().setTelefono(txtTelefono.getText());
									miPaciente.getPersona().setDireccion(txtDir.getText());
									miPaciente.getPersona().setFechaDeNacim(fechaNacim);
									miPaciente.setContacto_emergencia(txtNumeroEmer.getText());
									miPaciente.getPersona().setSexo(sexo);
									//miPaciente.setAlergias(txtAlergias.getText());
									
									Clinica.getInstance().modificarPaciente(miPaciente);
									dispose();
									ListarPaciente.loadPacientes(0);
								}
					} 
					
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
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
		loadPaciente();
	
}


	public void loadPaciente() {
		if(miPaciente != null) {
			txtPacienteID.setText(String.valueOf(miPaciente.getIdPaciente()));
			txtPersonaID.setText(String.valueOf(miPaciente.getPersona().getId_persona()));
			txtNombre.setText(miPaciente.getPersona().getNombre());
			txtApellido.setText(miPaciente.getPersona().getApellido());
			txtCedula.setText(miPaciente.getPersona().getCedula());
			txtTelefono.setText(miPaciente.getPersona().getTelefono());
			txtDir.setText(miPaciente.getPersona().getDireccion());
			txtNumeroEmer.setText(miPaciente.getContacto_emergencia());
			
			//txtNumeroEmer.setText(miPaciente.getNumEmergencia());
			//txtAlergias.setText(miPaciente.getAlergias());
			//Falta esta parte de ser arreglado
			if(miPaciente.getPersona().getSexo() =="H")
				cbxSexo.setSelectedItem("Hombre");
			if(miPaciente.getPersona().getSexo() =="M")
				cbxSexo.setSelectedItem("Mujer");
			
			spnEdad.setValue(miPaciente.getPersona().getFechaDeNacim());
			cbxSangre.setSelectedItem(miPaciente.getSangre());
			
		}
		
	}


	private void clean() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtCedula.setText("");
		txtTelefono.setText("");
		txtDir.setText("");
		txtNumeroEmer.setText("");
		cbxSexo.setSelectedItem("");
		spnEdad.setValue(new Date());
		miPaciente = null;
	}
	public static String getHighestUnoccupiedPersonaId() throws SQLException {
	    String query = "SELECT MIN(id_persona + 1) AS next_id "
	                 + "FROM persona p "
	                 + "WHERE NOT EXISTS (SELECT 1 FROM persona WHERE id_persona = p.id_persona + 1)";
	    Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    String nextId = "1"; // Default to "1" if no IDs exist in the table

	    try {
	        conn = DriverManager.getConnection(connectionUrl);
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery(query);
	        if (rs.next()) {
	            nextId = rs.getString("next_id");
	        }
	    } finally {
	        if (rs != null) rs.close();
	        if (stmt != null) stmt.close();
	        if (conn != null) conn.close();
	    }

	    return nextId;
	}

	public static String getHighestUnoccupiedPacienteId() throws SQLException {
	    String query = "SELECT MIN(id_paciente + 1) AS next_id "
	                 + "FROM paciente p "
	                 + "WHERE NOT EXISTS (SELECT 1 FROM paciente WHERE id_paciente = p.id_paciente + 1)";
	    Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    String nextId = "1"; // Default to "1" if no IDs exist in the table

	    try {
	        conn = DriverManager.getConnection(connectionUrl);
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery(query);
	        if (rs.next()) {
	            nextId = rs.getString("next_id");
	        }
	    } finally {
	        if (rs != null) rs.close();
	        if (stmt != null) stmt.close();
	        if (conn != null) conn.close();
	    }

	    return nextId;
	}
	
	
}