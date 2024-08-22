package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import logico.Buscar_Datos_Test;
import logico.Clinica;
import logico.Consulta;
import logico.Enfermedad;
import logico.Medico;
import logico.Paciente;
import logico.Secretario;
import logico.Vacuna;
import logico.Vacuna_Dosis;
import sun.util.locale.provider.AuxLocaleProviderAdapter;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtConsultaCodigo;
	private JTextField txtPaciente;
	private JTextField txtCedMedico;
	private JTextField txtDiagnostico;
	JSpinner spnFecha;
	private JTextField txtVacuna;
	private JTextArea txtDescripcion;
	private JTextArea txtTratamiento;
	private JTextField txtSecretario;
	private JComboBox cbxAsistencia;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegConsulta dialog = new RegConsulta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegConsulta() {
		setTitle("Registrar Consulta");
		setBounds(100, 100, 769, 649);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			txtConsultaCodigo = new JTextField();
			txtConsultaCodigo.setEditable(false);
			txtConsultaCodigo.setText("Co-" + Clinica.getInstance().consultaCodigo);
			txtConsultaCodigo.setBounds(10, 29, 138, 20);
			panel.add(txtConsultaCodigo);
			txtConsultaCodigo.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Codigo:");
			lblNewLabel.setBounds(10, 11, 46, 14);
			panel.add(lblNewLabel);
			
			txtPaciente = new JTextField();
			txtPaciente.setEditable(false);
			txtPaciente.setBounds(10, 104, 138, 20);
			panel.add(txtPaciente);
			txtPaciente.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Cedula del Paciente:");
			lblNewLabel_1.setBounds(10, 84, 161, 14);
			panel.add(lblNewLabel_1);
			
			txtCedMedico = new JTextField();
			txtCedMedico.setEditable(false);
			txtCedMedico.setBounds(10, 175, 138, 20);
			panel.add(txtCedMedico);
			txtCedMedico.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("Cedula del Medico:");
			lblNewLabel_2.setBounds(10, 159, 138, 14);
			panel.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Descripcion (sintomas, tiempo con sintomas, eventos claves, etc.) :");
			lblNewLabel_3.setBounds(10, 227, 471, 14);
			panel.add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("Diagnostico:");
			lblNewLabel_4.setBounds(10, 356, 161, 14);
			panel.add(lblNewLabel_4);
			
			txtDiagnostico = new JTextField();
			txtDiagnostico.setEditable(false);
			txtDiagnostico.setBounds(10, 383, 138, 20);
			panel.add(txtDiagnostico);
			txtDiagnostico.setColumns(10);
			
			spnFecha = new JSpinner();
			spnFecha.setModel(new SpinnerDateModel(new Date(), null, new Date(), Calendar.DAY_OF_YEAR));
			JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnFecha, "dd/MM/yyyy hh:mm a");
			spnFecha.setEditor(dateEditor);
			spnFecha.setEnabled(false);
			spnFecha.setBounds(177, 29, 181, 20);
			panel.add(spnFecha);
			
			JLabel lblNewLabel_5 = new JLabel("Fecha/Tiempo de Reunion:");
			lblNewLabel_5.setBounds(177, 11, 180, 14);
			panel.add(lblNewLabel_5);
			
			JLabel lblTratamiento = new JLabel("Tratamiento:");
			lblTratamiento.setBounds(10, 439, 161, 14);
			panel.add(lblTratamiento);
			
			txtVacuna = new JTextField();
			txtVacuna.setEditable(false);
			txtVacuna.setColumns(10);
			txtVacuna.setBounds(364, 383, 138, 20);
			panel.add(txtVacuna);
			
			JLabel lblVacuna = new JLabel("Vacuna:");
			lblVacuna.setBounds(364, 356, 161, 14);
			panel.add(lblVacuna);
			
			
			JButton btnBuscPaciente = new JButton("Buscar Paciente");
			btnBuscPaciente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ListarPaciente list = new ListarPaciente();
					list.setModal(true);
					list.setLocationRelativeTo(null);
					list.setVisible(true);
					if(Clinica.getPacienteCedula() != "") {
						txtPaciente.setText(Clinica.getPacienteCedula());
						Clinica.getInstance().setPacienteCedula("");
					}
				}
			});
			btnBuscPaciente.setBounds(177, 103, 138, 23);
			panel.add(btnBuscPaciente);
			
			JButton btnBuscMedico = new JButton("Buscar Medico");
			btnBuscMedico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ListMed list = new ListMed();
					list.setModal(true);
					list.setLocationRelativeTo(null);
					list.setVisible(true);
					if(Clinica.getMedicoCedula() != "") {
						txtCedMedico.setText(Clinica.getMedicoCedula());
						Clinica.getInstance().setMedicoCedula("");
					}
				}
			});
			btnBuscMedico.setBounds(177, 174, 138, 23);
			panel.add(btnBuscMedico);
			
			JButton btnNewButton = new JButton("Buscar Vacuna");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ListVac list = new ListVac(null);
					list.setModal(true);
					list.setLocationRelativeTo(null);
					list.setVisible(true);
					if(Clinica.getVacunaCodigo() != "") {
						txtVacuna.setText(Clinica.getVacunaCodigo());
						Clinica.getInstance().setVacunaCodigo("");
					}
				}
			});
			btnNewButton.setBounds(526, 382, 122, 23);
			panel.add(btnNewButton);
			
			JButton btnBuscarEnfermedades = new JButton("Buscar Enfermedad");
			btnBuscarEnfermedades.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ListaEnfermedades list = new ListaEnfermedades(null);
					list.setModal(true);
					list.setLocationRelativeTo(null);
					list.setVisible(true);
					if(Clinica.getEnfermedadCodigo() != "") {
						txtDiagnostico.setText(Clinica.getEnfermedadCodigo());
						Clinica.getInstance().setEnfermedadCodigo("");
					}
				}
			});
			btnBuscarEnfermedades.setBounds(160, 382, 162, 23);
			panel.add(btnBuscarEnfermedades);
			txtDescripcion = new JTextArea();
			txtDescripcion.setBounds(10, 254, 711, 89);
			panel.add(txtDescripcion);
			
			txtTratamiento = new JTextArea();
			txtTratamiento.setBounds(10, 466, 711, 73);
			panel.add(txtTratamiento);
			
			txtSecretario = new JTextField();
			txtSecretario.setEditable(false);
			txtSecretario.setColumns(10);
			txtSecretario.setBounds(364, 104, 138, 20);
			panel.add(txtSecretario);
			
			JLabel lblNewLabel_6 = new JLabel("Cedula del Secretario:");
			lblNewLabel_6.setBounds(364, 84, 138, 14);
			panel.add(lblNewLabel_6);
			
			cbxAsistencia = new JComboBox();
			cbxAsistencia.setModel(new DefaultComboBoxModel(new String[] {"Si", "No"}));
			cbxAsistencia.setBounds(364, 175, 138, 20);
			panel.add(cbxAsistencia);
			
			JLabel lblNewLabel_7 = new JLabel("Asistencia");
			lblNewLabel_7.setBounds(364, 159, 117, 14);
			panel.add(lblNewLabel_7);
			
			JButton btnNewButton_1 = new JButton("Buscar Secretario");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ListSecretario list = new ListSecretario();
					list.setModal(true);
					list.setLocationRelativeTo(null);
					list.setVisible(true);
					if(Clinica.getSecretarioCodigo() != "") {
						txtDiagnostico.setText(Clinica.getEnfermedadCodigo());
						Clinica.getInstance().setEnfermedadCodigo("");
					}
				}
			});
			btnNewButton_1.setBounds(526, 103, 122, 23);
			panel.add(btnNewButton_1);
		}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCrear = new JButton("Crear");
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Date fechaOcurrida = (Date) spnFecha.getValue();
						Paciente paciente = Clinica.getInstance().buscarPaciente(txtPaciente.getText());
						Medico medico = Clinica.getInstance().buscarMedico(txtCedMedico.getText());
						Secretario secretario = Clinica.getInstance().buscarSecretario(txtSecretario.getText());
						Enfermedad diagnostico = Clinica.getInstance().buscarEnfermedad(txtDiagnostico.getText());
						Vacuna_Dosis vacuna_Dosis = Clinica.getInstance().buscarVacuna_Dosis(txtVacuna.getText());
						if(paciente == null || medico == null || txtDescripcion.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Disculpe, parece que faltan algunos datos en la creacion de una nueva consulta.\n Por favor, llene los datos que faltan e intenta la creacion de nuevo.\n", "Datos Ausentes", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							Consulta nuevaConsulta = new Consulta(txtConsultaCodigo.getText(), paciente, medico, secretario, txtDescripcion.getText(), fechaOcurrida, diagnostico, txtTratamiento.getText(), vacuna_Dosis, (boolean) cbxAsistencia.getSelectedItem());
							
							Date fechaConsulta = nuevaConsulta.getFechaConsulta();

							// Define the desired date format
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

							// Convert Date to String
							String fechaConsultaStr = sdf.format(fechaConsulta);
							
							Buscar_Datos_Test.createConsulta(nuevaConsulta.getDescripcion(), fechaConsultaStr, nuevaConsulta.getTratamiento(), nuevaConsulta.isAsistencia(), Integer.parseInt(nuevaConsulta.getDiagnostico().getId()), nuevaConsulta.getVacuna_Dosis().getVacuna_dosis_id(), nuevaConsulta.getMedico().getId_medico(), nuevaConsulta.getSecretario().getId_secretario(), nuevaConsulta.getPaciente().getIdPaciente());
							//Si el paciente tiene una enfermedad, su estado cambia a true (para decir que esta enfermo)
							//Si no, su estado quede o vuelve a ser false (para decir que no esta enfermo)
							
							//if(diagnostico != null)
								//nuevaConsulta.getPaciente().setEstado(true);					
							//else
								//nuevaConsulta.getPaciente().setEstado(false);
							
							/*
							for(Consulta aux : Clinica.getInstance().getMisConsultas()) {
								if(aux.getDiagnostico().isPermanente() == true && nuevaConsulta.getPaciente().getCedula().equalsIgnoreCase(aux.getPaciente().getCedula()))
								{
									nuevaConsulta.getPaciente().setEstado(true);
									break;
								}
							}
							*/
							Clinica.getInstance().insertarConsulta(nuevaConsulta);
							JOptionPane.showMessageDialog(null, "Consulta Creada!\n", "Creacion!", JOptionPane.INFORMATION_MESSAGE); 
							clean();
						}
					}
				});
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
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
	
	private void clean() {
		txtConsultaCodigo.setText("Co-" + Clinica.getInstance().consultaCodigo);
		txtPaciente.setText("");
		txtCedMedico.setText("");
		txtDescripcion.setText("");
		txtDiagnostico.setText("");
		txtTratamiento.setText("");
		spnFecha.setValue(new Date());
	}
}
