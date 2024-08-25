package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import logico.Clinica;
import logico.Enfermedad;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;


public class RegistrarEnfermedad extends JFrame {

	private JPanel contentPane;
	private JTextField textNombreEnfermedad;
	private JTextField txtDescripcion;
	ArrayList<String> Sintomas = new ArrayList<>(100);
	private static JRadioButton rdbSi;
	private static JRadioButton rdbNo;
	private JButton btnNewButton_1;
	private JTextField txtCodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarEnfermedad frame = new RegistrarEnfermedad();
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
	public RegistrarEnfermedad() {
		setTitle("Registrar Enfermemdad");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBackground(SystemColor.menu);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Datos de enfermedad", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(SystemColor.text);
		panel_2.setBackground(SystemColor.menu);
		panel_2.setBounds(1, 11, 774, 290);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 96, 69, 20);
		panel_2.add(lblNewLabel_1);
		
		textNombreEnfermedad = new JTextField();
		textNombreEnfermedad.setBounds(90, 93, 664, 26);
		panel_2.add(textNombreEnfermedad);
		textNombreEnfermedad.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Sintomas:");
		lblNewLabel_2.setBounds(10, 144, 86, 20);
		panel_2.add(lblNewLabel_2);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(90, 141, 664, 26);
		panel_2.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		btnNewButton_1 = new JButton("Agregar Sintoma");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 Sintomas.add(txtDescripcion.getText());
			 JOptionPane.showMessageDialog(contentPane, txtDescripcion.getText() + " agregado.");
			 txtDescripcion.setText("");
			}
		});
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setBounds(599, 183, 155, 20);
		panel_2.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("Permanente");
		lblNewLabel_3.setBounds(40, 199, 104, 20);
		panel_2.add(lblNewLabel_3);
		
		rdbSi = new JRadioButton("Si");
		rdbSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbSi.isSelected())
					rdbNo.setSelected(false);
			}
		});
		rdbSi.setBackground(SystemColor.menu);
		rdbSi.setBounds(15, 215, 69, 29);
		panel_2.add(rdbSi);
		
		rdbNo = new JRadioButton("No");
		rdbNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbNo.isSelected())
					rdbSi.setSelected(false);
			}
		});
		rdbNo.setBackground(SystemColor.menu);
		rdbNo.setBounds(98, 215, 155, 29);
		panel_2.add(rdbNo);
		
		
		if(!rdbSi.isSelected() && !rdbNo.isSelected())
			rdbNo.setSelected(true);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setText("Enf-"+Clinica.getInstance().codigoEnf);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(90, 49, 91, 26);
		panel_2.add(txtCodigo);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(10, 52, 69, 20);
		panel_2.add(lblCodigo);
		
		JButton btnNewButton_2 = new JButton("Registrar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCodigo.getText().isEmpty() || textNombreEnfermedad.getText().isEmpty() || Sintomas.isEmpty() ) {
					JOptionPane.showMessageDialog(null, "Debe completar todos los campos","Intentelo nuevamente", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Enfermedad Agregada.\n", "Sistema", JOptionPane.INFORMATION_MESSAGE); 
					Enfermedad aux = new Enfermedad(txtCodigo.getText(), textNombreEnfermedad.getText(), txtDescripcion.getText(), rdbSi.isSelected());
					Clinica.getInstance().insertarEnfermedad(aux);
					clear();
				}
			}
		});
		btnNewButton_2.setBounds(575, 316, 97, 25);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Volver");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_3.setBounds(682, 316, 97, 25);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton = new JButton("Ver Lista de Sintomas Agregadas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder sintomasUnidos = new StringBuilder();
		        int i = 0;
		        while (i < Sintomas.size()) {
		            String sintomaRecibido = Sintomas.get(i) + "\n";
		            if (sintomaRecibido == null || sintomaRecibido.isEmpty()) {
		                break; 
		            }
		            sintomasUnidos.append(sintomaRecibido);
		            i++;
		        }
		        String listaFinal = sintomasUnidos.toString();
				JOptionPane.showMessageDialog(null, "Sintomas:\n\n" + listaFinal,"Listado de Sintomas", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setBounds(11, 317, 263, 23);
		panel.add(btnNewButton);
	}

	private void clear() {
		txtCodigo.setText("Enf-"+ Clinica.getInstance().codigoEnf);
		textNombreEnfermedad.setText("");
		txtDescripcion.setText("");
		Sintomas = new ArrayList<>();
		rdbSi.setSelected(false);
		rdbNo.setSelected(true);
	}
}
