package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;

public class RegPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtTelefono;
	private JTextField txtDir;
	private JTextField txtContactoEmer;
	private JTextField txtNumeroEmer;
	private String sexo = "H";
	JSpinner spnEdad;
	JComboBox<String> cbxSexo;
	private Paciente miPaciente = null;
	private Persona miPersona = null;
	private JTextArea txtAlergias;
	private JTextField txtApellido;
	private JTextField txtPersonaID;
	private JTextField txtPacienteID;
	JComboBox<String> cbxSangre;
	

	/**
	 * Launch the application.
	 */
	/*
	 public static void main(String[] args) {
	        try {
	            RegPaciente dialog = new RegPaciente(null);
	            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	            dialog.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	   */ 
	/**
	 * Create the dialog.
	 */
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
		setBounds(100, 100, 960, 589);
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
			txtNombre.setBounds(30, 208, 148, 20);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Nombre:");
			lblNewLabel.setBounds(30, 193, 117, 14);
			panel.add(lblNewLabel);
			
			txtCedula = new JTextField();
			txtCedula.setColumns(10);
			txtCedula.setBounds(30, 328, 148, 20);
			panel.add(txtCedula);
			
			JLabel lblCedula = new JLabel("Cedula:");
			lblCedula.setBounds(30, 313, 117, 14);
			panel.add(lblCedula);
			
			txtTelefono = new JTextField();
			txtTelefono.setColumns(10);
			txtTelefono.setBounds(465, 208, 168, 20);
			panel.add(txtTelefono);
			
			JLabel lblNumeroDeTelefono = new JLabel("Numero de Telefono:");
			lblNumeroDeTelefono.setBounds(465, 193, 174, 14);
			panel.add(lblNumeroDeTelefono);
			
			txtDir = new JTextField();
			txtDir.setColumns(10);
			txtDir.setBounds(223, 328, 168, 20);
			panel.add(txtDir);
			
			JLabel lblDireccion = new JLabel("Direccion:");
			lblDireccion.setBounds(223, 313, 117, 14);
			panel.add(lblDireccion);
			
			SpinnerDateModel modelo = new SpinnerDateModel();
	        spnEdad = new JSpinner(new SpinnerDateModel(new Date(), null, new Date(), Calendar.DAY_OF_MONTH));
	        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnEdad, "dd/MM/yyyy");
	        spnEdad.setEditor(dateEditor);
	        spnEdad.setBounds(223, 208, 168, 20);
	        panel.add(spnEdad);
			
			JLabel lblNewLabel_1 = new JLabel("Fecha de Nacimiento:");
			lblNewLabel_1.setBounds(223, 193, 168, 14);
			panel.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Sexo:");
			lblNewLabel_2.setBounds(223, 253, 46, 14);
			panel.add(lblNewLabel_2);
			
			txtContactoEmer = new JTextField();
			txtContactoEmer.setBounds(465, 268, 168, 20);
			panel.add(txtContactoEmer);
			txtContactoEmer.setColumns(10);
			
			JLabel lblNewLabel_3 = new JLabel("Contacto de Emergencia:");
			lblNewLabel_3.setBounds(465, 253, 168, 14);
			panel.add(lblNewLabel_3);
			
			JLabel lblNumeroDeCont = new JLabel("Numero de Contacto de ER:");
			lblNumeroDeCont.setBounds(465, 313, 168, 14);
			panel.add(lblNumeroDeCont);
			
			txtNumeroEmer = new JTextField();
			txtNumeroEmer.setColumns(10);
			txtNumeroEmer.setBounds(465, 328, 168, 20);
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
			cbxSexo.setBounds(223, 268, 168, 20);
			panel.add(cbxSexo);
			
			JLabel lblNewLabel_4 = new JLabel("Alergias:");
			lblNewLabel_4.setBounds(30, 383, 117, 14);
			panel.add(lblNewLabel_4);
			
			txtAlergias = new JTextArea();
			txtAlergias.setBounds(30, 407, 861, 99);
			panel.add(txtAlergias);
			
			txtApellido = new JTextField();
			txtApellido.setBounds(30, 268, 148, 20);
			panel.add(txtApellido);
			txtApellido.setColumns(10);
			
			JLabel lblNewLabel_5 = new JLabel("Apellido");
			lblNewLabel_5.setBounds(30, 253, 46, 14);
			panel.add(lblNewLabel_5);
			
			txtPersonaID = new JTextField();
			String personaIDString = Buscar_Datos_Test.getHighestUnoccupiedPersonaId();
			txtPersonaID.setText(personaIDString);
			txtPersonaID.setEditable(false);
			txtPersonaID.setBounds(30, 37, 148, 20);
			panel.add(txtPersonaID);
			txtPersonaID.setColumns(10);
			
			JLabel lblNewLabel_6 = new JLabel("Persona ID:");
			lblNewLabel_6.setBounds(30, 22, 117, 14);
			panel.add(lblNewLabel_6);
			
			txtPacienteID = new JTextField();
			String pacienteIDString = Buscar_Datos_Test.getHighestUnoccupiedPacienteId();
			txtPacienteID.setText(pacienteIDString);
			txtPacienteID.setEditable(false);
			txtPacienteID.setBounds(223, 37, 148, 20);
			panel.add(txtPacienteID);
			txtPacienteID.setColumns(10);
			
			JLabel lblNewLabel_7 = new JLabel("Paciente ID:");
			lblNewLabel_7.setBounds(223, 22, 93, 14);
			panel.add(lblNewLabel_7);
			
			cbxSangre = new JComboBox(new Object[]{});
			cbxSangre.setModel(new DefaultComboBoxModel(new String[] {"A+", "B+", "AB+", "O+", "A-", "B-", "AB-", "O-"}));
			cbxSangre.setBounds(698, 208, 168, 20);
			panel.add(cbxSangre);
			
			JLabel lblNewLabel_8 = new JLabel("Sangre:");
			lblNewLabel_8.setBounds(698, 193, 78, 14);
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
							    txtContactoEmer.getText().isEmpty() || txtNumeroEmer.getText().isEmpty() || (sexo != "H" && sexo != "M")) {
							JOptionPane.showMessageDialog(null, "Disculpe, parece que faltan algunos datos en la registracion del paciente. Por favor, llene los datos que faltan e intenta la registracion de nuevo.\n", "Datos Ausentes", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							if (miPaciente == null) {
									SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
									String fechaNacimientoStr = dateFormat.format(fechaNacim);
								
									if(txtAlergias.getText().isEmpty())
										txtAlergias.setText("N/A");
									//miPaciente = new Paciente(txtNombre.getText(), txtCedula.getText(), txtDir.getText(), fechaNacim, sexo, txtTelefono.getText(), false, txtContactoEmer.getText(), txtNumeroEmer.getText(), txtAlergias.getText());
									miPersona = new Persona(Integer.parseInt(txtPersonaID.getText()), txtNombre.getText(), txtApellido.getText(), fechaNacim, txtDir.getText(), sexo, txtTelefono.getText(), txtCedula.getText());
									try {
										Buscar_Datos_Test.createPersona(miPersona);
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									miPaciente = new Paciente(Integer.parseInt(txtPacienteID.getText()), (String) cbxSangre.getSelectedItem(), txtNumeroEmer.getText(), miPersona);
									try {
										Buscar_Datos_Test.createPaciente(miPaciente);
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
									//miPaciente.setContactoEmergencia(txtContactoEmer.getText());
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
			//txtNombre.setText(miPaciente.getNombre());
			//txtCedula.setText(miPaciente.getCedula());
			//txtTelefono.setText(miPaciente.getTelefono());
			//txtDir.setText(miPaciente.getDireccion());
			//txtContactoEmer.setText(miPaciente.getContactoEmergencia());
			//txtNumeroEmer.setText(miPaciente.getNumEmergencia());
			//txtAlergias.setText(miPaciente.getAlergias());
			//Falta esta parte de ser arreglado
			//if(miPaciente.getSexo()=='H')
			//	cbxSexo.setSelectedItem("Hombre");
			//if(miPaciente.getSexo()=='M')
			//	cbxSexo.setSelectedItem("Mujer");
			
			//spnEdad.setValue(miPaciente.getFechaDeNacim());
			
		}
		
	}


	private void clean() {
		txtNombre.setText("");
		txtCedula.setText("");
		txtTelefono.setText("");
		txtDir.setText("");
		txtContactoEmer.setText("");
		txtNumeroEmer.setText("");
		cbxSexo.setSelectedItem("");
		spnEdad.setValue(new Date());
		txtAlergias.setText("");
		miPaciente = null;
	}
}