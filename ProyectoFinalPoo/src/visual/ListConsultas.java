package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logico.Clinica;
import logico.Consulta;
import logico.Paciente;
import logico.Persona;
import logico.Secretario;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import javax.swing.border.TitledBorder;

public class ListConsultas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static DefaultTableModel modelo;
	private static Object[] fila;
	private static int seleccion;
	private static Consulta seleccionado = null;
	private static Paciente miPaciente = null;
	private static Secretario miSecretario = null;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		try {
			ListConsultas dialog = new ListConsultas(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public ListConsultas(Paciente seleccionado2) {
		setTitle("Listado de Consultas");
		miPaciente = seleccionado2;
		if(miPaciente != null) 
			setTitle("Consulta Historial de "+ miPaciente.getPersona().getNombre());
		else
			setTitle("Listado de Consultas");
		setBounds(100, 100, 1048, 491);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 36, 1002, 350);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane);
				{
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
								//btnModificar.setEnabled(true);
								//btnInfo.setEnabled(true);
								seleccionado = Clinica.getInstance().buscarConsulta(table.getValueAt(index, 0).toString());
							}
						}
					});
					modelo = new DefaultTableModel();
					String[] headers = {"Codigo", "Paciente", "Medico", "Desc.", "Tiempo", "Diagnostico", "Tratamiento", "Vacuna"};
					modelo.setColumnIdentifiers(headers);
					loadConsultas(0);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
	}

	public static void loadConsultas(int seleccion) {
	    modelo.setRowCount(0); // Clear the table
	    fila = new Object[modelo.getColumnCount()]; // Create a new row

	    // Your database connection details
	    String connectionUrl = "jdbc:sqlserver://192.168.100.118:1433;"
	            + "database=clinica_stanley_eduardo;"
	            + "user=s.gomez;" //TU USER
	            + "password=Headphone1130Jack;" //TU CLAVE
	            + "encrypt=true;"
	            + "trustServerCertificate=true;"
	            + "loginTimeout=30;";

	    try (Connection connection = DriverManager.getConnection(connectionUrl)) {
	        String sql;
	        PreparedStatement pstmt;

	        // Check if miPaciente is null
	        if (miPaciente != null) {
	            // If a specific patient is selected, filter by their ID
	            sql = "SELECT c.id_consulta, p.nombre AS paciente_nombre, m.nombre AS medico_nombre, c.descripcion, c.fecha_consulta, "
	                + "ISNULL(e.nombre, 'N/A') AS diagnostico_nombre, "
	                + "c.tratamiento, "
	                + "ISNULL(v.nombre, 'N/A') AS vacuna_nombre " // Handle NULLs for vacuna_nombre
	                + "FROM consulta c "
	                + "JOIN paciente pa ON c.id_paciente = pa.id_paciente "
	                + "JOIN persona p ON pa.id_persona = p.id_persona "
	                + "JOIN medico me ON c.id_medico = me.id_medico "
	                + "JOIN persona m ON me.id_persona = m.id_persona "
	                + "LEFT JOIN enfermedad e ON c.id_enfermedad = e.id_enfermedad "
	                + "LEFT JOIN vacuna_dosis vd ON c.vacuna_dosis = vd.id_vacuna_dosis "
	                + "LEFT JOIN vacuna v ON vd.id_vacuna = v.id_vacuna "
	                + "WHERE pa.id_paciente = ?";
	            
	            pstmt = connection.prepareStatement(sql);
	            pstmt.setInt(1, miPaciente.getIdPaciente());

	        } else {
	            // If no specific patient is selected, retrieve all consultations
	            sql = "SELECT c.id_consulta, p.nombre AS paciente_nombre, m.nombre AS medico_nombre, c.descripcion, c.fecha_consulta, "
	                + "ISNULL(e.nombre, 'N/A') AS diagnostico_nombre, "
	                + "c.tratamiento, "
	                + "ISNULL(v.nombre, 'N/A') AS vacuna_nombre " // Handle NULLs for vacuna_nombre
	                + "FROM consulta c "
	                + "JOIN paciente pa ON c.id_paciente = pa.id_paciente "
	                + "JOIN persona p ON pa.id_persona = p.id_persona "
	                + "JOIN medico me ON c.id_medico = me.id_medico "
	                + "JOIN persona m ON me.id_persona = m.id_persona "
	                + "LEFT JOIN enfermedad e ON c.id_enfermedad = e.id_enfermedad "
	                + "LEFT JOIN vacuna_dosis vd ON c.vacuna_dosis = vd.id_vacuna_dosis "
	                + "LEFT JOIN vacuna v ON vd.id_vacuna = v.id_vacuna";
	            
	            pstmt = connection.prepareStatement(sql);
	        }

	        ResultSet rs = pstmt.executeQuery();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");

	        while (rs.next()) {
	            fila[0] = rs.getInt("id_consulta");
	            fila[1] = rs.getString("paciente_nombre");
	            fila[2] = rs.getString("medico_nombre");
	            fila[3] = rs.getString("descripcion");
	            fila[4] = dateFormat.format(rs.getTimestamp("fecha_consulta"));
	            fila[5] = rs.getString("diagnostico_nombre");
	            fila[6] = rs.getString("tratamiento");
	            fila[7] = rs.getString("vacuna_nombre"); // This will be "N/A" if the original value was NULL

	            modelo.addRow(fila);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }

	    table.setModel(modelo);
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    table.getTableHeader().setReorderingAllowed(false);
	    TableColumnModel columnModel = table.getColumnModel();
	    columnModel.getColumn(0).setPreferredWidth(110);
	    columnModel.getColumn(1).setPreferredWidth(120);
	    columnModel.getColumn(2).setPreferredWidth(120);
	    columnModel.getColumn(3).setPreferredWidth(220);
	    columnModel.getColumn(4).setPreferredWidth(120);
	    columnModel.getColumn(5).setPreferredWidth(120);
	    columnModel.getColumn(6).setPreferredWidth(220);
	    columnModel.getColumn(7).setPreferredWidth(120);
	}
}
