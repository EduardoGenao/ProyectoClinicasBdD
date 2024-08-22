package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Cita;
import logico.Clinica;
import logico.Medico;
import logico.Paciente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class RegCitas extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtDoctor;
	private JSpinner spnTiempo;
	private JTextField txtTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegCitas frame = new RegCitas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegCitas() {
		setResizable(false);
		setTitle("Registrar Cita");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 663, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel lblNewLabel = new JLabel("Datos del paciente:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(15, 28, 242, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNombre.setBounds(15, 119, 81, 20);
		contentPane.add(lblNombre);
		
		JLabel lblCedula = new JLabel("C\u00E9dula:");
		lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCedula.setBounds(15, 74, 81, 20);
		contentPane.add(lblCedula);
		
		JLabel lblDatosDeLa = new JLabel("Datos de la cita:");
		lblDatosDeLa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDatosDeLa.setBounds(15, 209, 196, 20);
		contentPane.add(lblDatosDeLa);
		
		JLabel lblDoctor = new JLabel("Medico (Cedula):");
		lblDoctor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDoctor.setBounds(15, 245, 137, 20);
		contentPane.add(lblDoctor);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTipo.setBounds(15, 288, 81, 20);
		contentPane.add(lblTipo);
		
		JLabel lblFecha = new JLabel("Tiempo:");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFecha.setBounds(15, 330, 81, 20);
		contentPane.add(lblFecha);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(86, 116, 313, 26);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCedula = new JTextField();
		txtCedula.setEditable(false);
		txtCedula.setColumns(10);
		txtCedula.setBounds(86, 71, 115, 26);
		contentPane.add(txtCedula);
		
		txtDoctor = new JTextField();
		txtDoctor.setEditable(false);
		txtDoctor.setColumns(10);
		txtDoctor.setBounds(100, 245, 255, 26);
		contentPane.add(txtDoctor);
		
		txtTipo = new JTextField();
		txtTipo.setColumns(10);
		txtTipo.setBounds(86, 285, 269, 26);
		contentPane.add(txtTipo);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarPaciente list = new ListarPaciente();
				list.setModal(true);
				list.setLocationRelativeTo(null);
				list.setVisible(true);
				if(Clinica.getInstance().getPacienteCedula() != "")
				{
					String tempPac = Clinica.getInstance().getPacienteCedula();
					Paciente pacienteTemp = Clinica.getInstance().buscarPaciente(tempPac);
					txtCedula.setText(tempPac);
					txtNombre.setText(pacienteTemp.getPersona().getNombre());
					Clinica.getInstance().setPacienteCedula("");
				}
			}
		});
		btnNewButton.setBounds(225, 74, 89, 20);
		contentPane.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(15, 390, 630, 50);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(528, 13, 90, 25);
		panel_1.add(btnCancelar);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(414, 13, 102, 25);
		panel_1.add(btnRegistrar);
		
		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListMed list = new ListMed();
				list.setModal(true);
				list.setLocationRelativeTo(null);
				list.setVisible(true);
				if(Clinica.getInstance().getMedicoCedula() != "") {
					txtDoctor.setText(Clinica.getMedicoCedula());
					Clinica.getInstance().setMedicoCedula("");
					
				}
			}
		});
		btnNewButton_1.setBounds(365, 244, 89, 23);
		contentPane.add(btnNewButton_1);
		
		spnTiempo = new JSpinner();
		spnTiempo.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnTiempo, "dd/MM/yyyy hh:mm a");
		spnTiempo.setEditor(dateEditor);
		spnTiempo.setBounds(86, 330, 269, 20);
		contentPane.add(spnTiempo);
		
		JRadioButton btnAsistencia = new JRadioButton("Asistencia");
		btnAsistencia.setBounds(365, 329, 109, 23);
		contentPane.add(btnAsistencia);
		btnRegistrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(txtCedula.getText().isEmpty() || txtNombre.getText().isEmpty() ||txtDoctor.getText().isEmpty() || txtTipo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Disculpe, parece que faltan algunos datos en la creacion de una nueva consulta.\n Por favor, llene los datos que faltan e intenta la registracion de nuevo.\n", "Datos Ausentes", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					Date tiempo = (Date) spnTiempo.getValue();
					//Cita aux = new Cita(txtNombre.getText(), tiempo, txtDoctor.getText(), txtTipo.getText(),btnAsistencia.isSelected(), txtCedula.getText());
					//Cita aux = new Cita(id_cita, nombre, fecha_cita, asistencia, id_medico, id_tipo_cita, paciente);
					JOptionPane.showMessageDialog(null, "Cita Registrada!\n", "Registracion!", JOptionPane.INFORMATION_MESSAGE); 
					//Clinica.getInstance().RegistrarCita(aux);
					clean();
				}
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
	}
	private void clean() {
		txtNombre.setText("");
		txtCedula.setText("");
		txtDoctor.setText("");
		txtTipo.setText("");
		spnTiempo.setValue(new Date());
		
	}
}




		
