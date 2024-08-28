package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;

import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;


public class ListCitas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private Object row[];
	String connectionUrl = "jdbc:sqlserver://192.168.100.118:1433;"
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
			ListCitas dialog = new ListCitas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListCitas() {
		setBounds(100, 100, 1007, 476);
		setLocationRelativeTo(null);
		setTitle("Listado de Citas");
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
						String headers[] = {"Numero de cita", "Paciente", "Doctor", "Secretario", "Tipo", "Fecha", "Asistencia"};
					    model = new DefaultTableModel();
						model.setColumnIdentifiers(headers);
						table = new JTable();
						table.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent arg0) {
								if(table.getSelectedRow()>-1) {
									//okButton.setEnabled(true);
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
				buttonPane.add(btnVolver);
			}
		}
		loadTable();
	}
	private void loadTable() {
	    model.setRowCount(0);  // Clear the table
	    
	    row = new Object[model.getColumnCount()];

	    try (Connection conn = DriverManager.getConnection(connectionUrl)) {
	        String query = "SELECT c.id_cita, " +
	                       "pPersona.nombre AS paciente_nombre, pPersona.apellido AS paciente_apellido, " +
	                       "mPersona.nombre AS doctor_nombre, mPersona.apellido AS doctor_apellido, " +
	                       "sPersona.nombre AS secretario_nombre, sPersona.apellido AS secretario_apellido, " +
	                       "tc.nombre AS tipo, c.fecha_cita, c.asistencia " +  
	                       "FROM cita c " +
	                       "JOIN paciente p ON c.id_paciente = p.id_paciente " +
	                       "JOIN persona pPersona ON p.id_persona = pPersona.id_persona " + 
	                       "JOIN medico m ON c.id_medico = m.id_medico " +
	                       "JOIN persona mPersona ON m.id_persona = mPersona.id_persona " + 
	                       "JOIN secretario s ON c.id_secretario = s.id_secretario " +
	                       "JOIN persona sPersona ON s.id_persona = sPersona.id_persona " + 
	                       "JOIN tipo_cita tc ON c.id_tipo_cita = tc.id_tipo_cita";

	        PreparedStatement stmt = conn.prepareStatement(query);
	        ResultSet rs = stmt.executeQuery();

	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");

	        while (rs.next()) {
	            row[0] = rs.getInt("id_cita");
	            row[1] = rs.getString("paciente_nombre") + " " + rs.getString("paciente_apellido");
	            row[2] = rs.getString("doctor_nombre") + " " + rs.getString("doctor_apellido");
	            row[3] = rs.getString("secretario_nombre") + " " + rs.getString("secretario_apellido");
	            row[4] = rs.getString("tipo");
	            row[5] = dateFormat.format(rs.getTimestamp("fecha_cita"));
	            row[6] = rs.getBoolean("asistencia") ? "Yes" : "No";  

	            model.addRow(row);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}