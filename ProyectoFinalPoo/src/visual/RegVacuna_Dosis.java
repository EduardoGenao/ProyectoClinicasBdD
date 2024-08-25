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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Enfermedad;
import logico.Paciente;
import logico.Vacuna;
import logico.Vacuna_Dosis;

import javax.swing.JSpinner;

public class RegVacuna_Dosis extends JDialog {
	
    private JButton btnAgr;
    private JButton btnCancel;
    private JTextField txtCod;
    private boolean control;
    private static Vacuna vac = null;
    JButton btnVacuna;
    private static final String connectionUrl =
            "jdbc:sqlserver://192.168.100.118:1433;"
            + "database=clinica_stanley_eduardo;"
            + "user=s.gomez;" // Your username
            + "password=Headphone1130Jack;" // Your password
            + "encrypt=true;"
            + "trustServerCertificate=true;"
            + "loginTimeout=30;";
    private JTextField txtVacuna;

	private final JPanel contentPanel = new JPanel();
	private JSpinner spnCantidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegVacuna_Dosis dialog = new RegVacuna_Dosis("");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegVacuna_Dosis(String Consulta) {
		setTitle("Agregar Vacuna al Inventario");
        setBounds(100, 100, 459, 341);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("C\u00F3digo");
        lblNewLabel.setBounds(12, 13, 56, 16);
        contentPanel.add(lblNewLabel);

        txtCod = new JTextField();
        txtCod.setText("Vac_Dos-" + Clinica.getInstance().vacuna_dosis_id);
        txtCod.setEditable(false);
        txtCod.setBounds(12, 36, 73, 22);
        contentPanel.add(txtCod);
        txtCod.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Cantidad de Miligramos:");
        lblNewLabel_2.setBounds(12, 156, 221, 16);
        contentPanel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Vacuna");
        lblNewLabel_3.setBounds(10, 88, 140, 14);
        contentPanel.add(lblNewLabel_3);
        
        txtVacuna = new JTextField();
        txtVacuna.setBounds(12, 113, 108, 20);
        contentPanel.add(txtVacuna);
        txtVacuna.setColumns(10);
        
        btnVacuna = new JButton("Buscar Vacuna");
        btnVacuna.addActionListener(new ActionListener() {
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
        btnVacuna.setBounds(132, 113, 156, 23);
        contentPanel.add(btnVacuna);
        
        spnCantidad = new JSpinner();
        spnCantidad.setBounds(12, 183, 116, 20);
        contentPanel.add(spnCantidad);

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnAgr = new JButton("Agregar Vacuna Dosis");
        btnAgr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!control) {
                	
                	//Vacuna vacuna = Clinica.getInstance().buscarVacuna(txtVacuna.getText());
                    //Vacuna_Dosis aux = new Vacuna_Dosis(txtCod.getText(), Integer.parseInt(spnCantidad.getValue().toString()), vacuna, consulta);
                   // Clinica.getInstance().insertarVacuna(aux);
                    //insertVacunaToDatabase(aux);  // Insert into SQL database
                    //JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Registro", JOptionPane.INFORMATION_MESSAGE);
                	Clinica.getInstance().setVacuna_DosisCodigo(txtCod.getText());
                	Clinica.getInstance().setCantMiligramos(Integer.parseInt(spnCantidad.getValue().toString()));
                	Clinica.getInstance().setVacDosVacunaId(txtVacuna.getText());
                    dispose();
                	clear();
              
                } else {
                    //vac.setNombre(txtNombre.getText());
                    //vac.setDescrip(txtDescrip.getText());
                    Clinica.getInstance().modificarVac(vac);
                    dispose();
                    ListVac.loadVacunas();
                }
            }
        });
        btnAgr.setActionCommand("OK");
        buttonPane.add(btnAgr);
        getRootPane().setDefaultButton(btnAgr);

        btnCancel = new JButton("Cancelar");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancel.setActionCommand("Cancel");
        buttonPane.add(btnCancel);

        loadVacuna();
    }

    // Method to load Vacuna data if editing an existing Vacuna
    public void loadVacuna() {
        if (vac != null) {
            txtCod.setText(vac.getId_vacuna());
            //txtNombre.setText(vac.getNombre());
            //txtDescrip.setText(vac.getDescrip());
        }
    }

    // Method to clear the input fields after insertion
    protected void clear() {
        txtCod.setText("Vac-" + Clinica.getInstance().codigo);
       // txtNombre.setText("");
        //txtDescrip.setText("");
    }
/*
    // Method to insert a Vacuna into the SQL database
    private void insertVacunaToDatabase(Vacuna vacuna) {
        String insertSql = "INSERT INTO Vacuna (id_vacuna, nombre, descrip) VALUES (?, ?, ?)";
        
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {

            // Set parameters for the prepared statement
            preparedStatement.setString(1, vacuna.getId_vacuna());
            preparedStatement.setString(2, vacuna.getNombre());
            preparedStatement.setString(3, vacuna.getDescrip());

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Vacuna successfully inserted into the database.");
            } else {
                System.out.println("Failed to insert Vacuna into the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
*/
    /*
    private void insertVacunaToDatabase(Vacuna vacuna) {
        String insertSql = "INSERT INTO vacuna (nombre, descripcion, id_enfermedad) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {

            // Set parameters for the prepared statement
            preparedStatement.setString(1, vacuna.getNombre());
            preparedStatement.setString(2, vacuna.getDescrip());
            preparedStatement.setInt(3, Integer.parseInt(vacuna.getEnfermedad().getId())); // Assuming Enfermedad class has getIdEnfermedad() method

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Vacuna successfully inserted into the database.");
            } else {
                System.out.println("Failed to insert Vacuna into the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    */
}
