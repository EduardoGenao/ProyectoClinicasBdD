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
	JButton btnInfo;
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
								btnInfo.setEnabled(true);
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
				btnInfo = new JButton("Mas Informacion");
				btnInfo.setEnabled(false);
				btnInfo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String diagnostico;
						String vacuna;
						if(seleccionado.getDiagnostico() == null) 
							diagnostico = "N/A";
						else
							diagnostico = seleccionado.getDiagnostico().getNombre();
						
						if(seleccionado.getVacuna_Dosis() == null) 
							vacuna = "N/A";
						else
							vacuna = seleccionado.getVacuna_Dosis().getVacuna().getNombre();
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
						String fechaOcurrida = dateFormat.format(seleccionado.getFechaConsulta());
						JOptionPane.showMessageDialog(null, "Codigo: " + seleccionado.getId_consulta() + "\nPaciente Cedula: " + seleccionado.getPaciente().getPersona().getCedula() + "\nPaciente Nombre: " + seleccionado.getPaciente().getPersona().getNombre() + "\nMedico Cedula: " + seleccionado.getMedico().getPersona().getCedula() + "\nMedico Nombre: " + seleccionado.getMedico().getPersona().getNombre() + "\nDescripcion: " + seleccionado.getDescripcion() + "\nFecha: " + fechaOcurrida + "\nDiagnostico: " + diagnostico + "\nTratamiento: " + seleccionado.getTratamiento() + "\nVacuna: " + vacuna, "Datos de Consulta Seleccionada", JOptionPane.INFORMATION_MESSAGE);
					}
				});
				btnInfo.setActionCommand("OK");
				buttonPane.add(btnInfo);
				getRootPane().setDefaultButton(btnInfo);
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
	}

	public static void loadConsultas(int seleccion) {
	    modelo.setRowCount(0);
	    fila = new Object[modelo.getColumnCount()];

	    // Your database connection details
	    String connectionUrl = "jdbc:sqlserver://192.168.100.118:1433;"
                + "database=clinica_stanley_eduardo;"
                + "user=s.gomez;" //TU USER
                + "password=Headphone1130Jack;" //TU CLAVE
                + "encrypt=true;"
                + "trustServerCertificate=true;"
                + "loginTimeout=30;";

	    try (Connection connection = DriverManager.getConnection(connectionUrl)) {
	        String sql = "SELECT c.id_consulta, p.nombre AS paciente_nombre, m.nombre AS medico_nombre, c.descripcion, c.fecha_consulta, "
	                   + "e.nombre AS diagnostico_nombre, c.tratamiento, v.nombre AS vacuna_nombre "
	                   + "FROM consulta c "
	                   + "JOIN paciente pa ON c.id_paciente = pa.id_paciente "
	                   + "JOIN persona p ON pa.id_persona = p.id_persona "
	                   + "JOIN medico me ON c.id_medico = me.id_medico "
	                   + "JOIN persona m ON me.id_persona = m.id_persona "
	                   + "LEFT JOIN enfermedad e ON c.id_enfermedad = e.id_enfermedad "
	                   + "LEFT JOIN vacuna_dosis vd ON c.id_vacuna_dosis = vd.id_vacuna_dosis "
	                   + "LEFT JOIN vacuna v ON vd.id_vacuna = v.id_vacuna "
	                   + "WHERE pa.id_paciente = ?"; // Filter by id_paciente or change to pa.id_persona if needed

	        PreparedStatement pstmt = connection.prepareStatement(sql);
	        pstmt.setInt(1, miPaciente.getIdPaciente()); // Set the paciente ID parameter

	        ResultSet rs = pstmt.executeQuery();

	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");

	        while (rs.next()) {
	            fila[0] = rs.getInt("id_consulta");
	            fila[1] = rs.getString("paciente_nombre");
	            fila[2] = rs.getString("medico_nombre");
	            fila[3] = rs.getString("descripcion");
	            fila[4] = dateFormat.format(rs.getTimestamp("fecha_consulta"));
	            fila[5] = rs.getString("diagnostico_nombre") != null ? rs.getString("diagnostico_nombre") : "N/A";
	            fila[6] = rs.getString("tratamiento");
	            fila[7] = rs.getString("vacuna_nombre") != null ? rs.getString("vacuna_nombre") : "N/A";

	            modelo.addRow(fila);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
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
