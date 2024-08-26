
/*package visual;

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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Paciente;
import logico.Tipo_Cita;
import logico.Vacuna;

public class ListTipoCita extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
    private JButton btnCancelar;
    private JTable tabla;
    private Tipo_Cita select = null;
    private static DefaultTableModel model;
    private static Object row[];
    private static Paciente miPaciente = null;
    private JButton btnSeleccionar;
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
			ListTipoCita dialog = new ListTipoCita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public ListTipoCita() {
		Clinica.getInstance().setTipoCitaCodigo("");;
        setTitle("Listado de Tipos de Citas");
        setBounds(100, 100, 733, 433);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        String[] headers = {"Código", "Nombre", "Descripción"};

        tabla = new JTable();
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = tabla.getSelectedRow();
                if (index >= 0) {
                    String codigo = tabla.getValueAt(index, 0).toString();
                    System.out.println(codigo);
                    //select = Clinica.getInstance().buscarVacuna(tabla.getValueAt(index, 0).toString());
                    select = Clinica.getInstance().getTipoCitaById(tabla.getValueAt(index, 0).toString());
                    System.out.println(select.getId_tipo());
                }
            }
        });
        scrollPane.setViewportView(tabla);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(headers);
        tabla.setModel(model);

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        btnSeleccionar = new JButton("Seleccionar Tipo");
        btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (select != null) {
                    Clinica.getInstance().setTipoCitaCodigo(select.getId_tipo());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Disculpe, parece que no hay un valor seleccionado aqui para: \n" + Clinica.getVacunaCodigo() + "\n Por favor, seleccione una Vacuna y intentalo de nuevo.\n", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        buttonPane.add(btnSeleccionar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(btnCancelar);

        loadVacunas();
    }

    public static void loadVacunas() {
        model.setRowCount(0);
        row = new Object[model.getColumnCount()];

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT v.id_vacuna, v.nombre, v.descripcion " +
                     "FROM vacuna v ")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                row[0] = resultSet.getInt("id_vacuna");
                row[1] = resultSet.getString("nombre");
                row[2] = resultSet.getString("descripcion");
                model.addRow(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data from the database. Please check your connection and try again.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
*/

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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Tipo_Cita;

public class ListTipoCita extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
    private JButton btnCancelar;
    private JTable tabla;
    private Tipo_Cita select = null;
    private static DefaultTableModel model;
    private static Object row[];
    private JButton btnSeleccionar;
    
    static String connectionUrl =
            "jdbc:sqlserver://192.168.100.118:1433;"
                    + "database=clinica_stanley_eduardo;"
                    + "user=s.gomez;" // Update with your actual username
                    + "password=Headphone1130Jack;" // Update with your actual password
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=30;";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListTipoCita dialog = new ListTipoCita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListTipoCita() {
		Clinica.getInstance().setTipoCitaCodigo("");
        setTitle("Listado de Tipos de Citas");
        setBounds(100, 100, 733, 433);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Update headers to match tipo_cita table columns
        String[] headers = {"Código", "Nombre"};

        tabla = new JTable();
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = tabla.getSelectedRow();
                if (index >= 0) {
                    String codigo = tabla.getValueAt(index, 0).toString();
                    System.out.println(codigo);
                    // Fetch selected Tipo_Cita by its ID
                    select = Clinica.getInstance().getTipoCitaById(codigo);
                    System.out.println(select.getId_tipo());
                }
            }
        });
        scrollPane.setViewportView(tabla);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(headers);
        tabla.setModel(model);

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        btnSeleccionar = new JButton("Seleccionar Tipo");
        btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (select != null) {
                    Clinica.getInstance().setTipoCitaCodigo(select.getId_tipo());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Disculpe, parece que no hay un valor seleccionado. Por favor, seleccione un tipo de cita y vuelva a intentarlo.", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        buttonPane.add(btnSeleccionar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(btnCancelar);

        // Load data for Tipo Cita
        loadTipoCitas();
    }

    // Updated method to load data from tipo_cita table
    public static void loadTipoCitas() {
        model.setRowCount(0);
        row = new Object[model.getColumnCount()];

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id_tipo_cita, nombre FROM tipo_cita")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                row[0] = resultSet.getInt("id_tipo_cita"); // Get the id_tipo_cita from SQL
                row[1] = resultSet.getString("nombre");    // Get the nombre from SQL
                model.addRow(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data from the database. Please check your connection and try again.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}