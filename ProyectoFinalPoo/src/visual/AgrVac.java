package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import logico.Clinica;
import logico.Enfermedad;
import logico.Medico;
import logico.Vacuna;

import javax.swing.JTextPane;


public class AgrVac extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JButton btnAgr;
    private JButton btnCancel;
    private JTextField txtCod;
    private JTextField txtNombre;
    private JTextPane txtDescrip;
    private boolean control;
    private static Vacuna vac = null;
    JButton btnEnfermedad;
    private static final String connectionUrl =
            "jdbc:sqlserver://192.168.100.118:1433;"
            + "database=clinica_stanley_eduardo;"
            + "user=s.gomez;" // Your username
            + "password=Headphone1130Jack;" // Your password
            + "encrypt=true;"
            + "trustServerCertificate=true;"
            + "loginTimeout=30;";
    private JTextField txtEnfermedad;

    /**
     * Launch the application.
     */

    public static void main(String[] args) {
    	try {
    		AgrVac dialog = new AgrVac(null);
    		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    		dialog.setVisible(true);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    /**
     * Create the dialog.
     * @return 
     */

    public AgrVac(Vacuna vac) {
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
        txtCod.setEditable(false);
        txtCod.setText("Vac-" + Clinica.getInstance().codigo);
        txtCod.setBounds(12, 36, 73, 22);
        contentPanel.add(txtCod);
        txtCod.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Nombre");
        lblNewLabel_1.setBounds(188, 13, 56, 16);
        contentPanel.add(lblNewLabel_1);

        txtNombre = new JTextField();
        txtNombre.setBounds(188, 36, 116, 22);
        contentPanel.add(txtNombre);
        txtNombre.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Descripci\u00F3n");
        lblNewLabel_2.setBounds(12, 85, 73, 16);
        contentPanel.add(lblNewLabel_2);

        txtDescrip = new JTextPane();
        txtDescrip.setBounds(12, 112, 292, 45);
        contentPanel.add(txtDescrip);
        
        JLabel lblNewLabel_3 = new JLabel("Enfermedad");
        lblNewLabel_3.setBounds(22, 190, 140, 14);
        contentPanel.add(lblNewLabel_3);
        
        txtEnfermedad = new JTextField();
        txtEnfermedad.setBounds(22, 212, 108, 20);
        contentPanel.add(txtEnfermedad);
        txtEnfermedad.setColumns(10);
        
        btnEnfermedad = new JButton("Buscar Enfermedad");
        btnEnfermedad.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ListaEnfermedades list = new ListaEnfermedades(null);
				list.setModal(true);
				list.setLocationRelativeTo(null);
				list.setVisible(true);
				if(Clinica.getEnfermedadCodigo() != "") {
					txtEnfermedad.setText(Clinica.getEnfermedadCodigo());
					Clinica.getInstance().setEnfermedadCodigo("");
				}
        	}
        });
        btnEnfermedad.setBounds(140, 211, 156, 23);
        contentPanel.add(btnEnfermedad);

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnAgr = new JButton("Agregar Vacuna");
        btnAgr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!control) {
                	Enfermedad enfermedad = Clinica.getInstance().buscarEnfermedad(txtEnfermedad.getText());
                    Vacuna aux = new Vacuna(txtCod.getText(), txtNombre.getText(), txtDescrip.getText(), enfermedad);
                    Clinica.getInstance().insertarVacuna(aux);
                    insertVacunaToDatabase(aux);  // Insert into SQL database
                    JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Registro", JOptionPane.INFORMATION_MESSAGE);
                    clear();
                } else {
                    vac.setNombre(txtNombre.getText());
                    vac.setDescrip(txtDescrip.getText());
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
            txtNombre.setText(vac.getNombre());
            txtDescrip.setText(vac.getDescrip());
        }
    }

    // Method to clear the input fields after insertion
    protected void clear() {
        txtCod.setText("Vac-" + Clinica.getInstance().codigo);
        txtNombre.setText("");
        txtDescrip.setText("");
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
}
/*
 * package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import logico.Clinica;
import logico.Medico;
import logico.Vacuna;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JTextPane;

public class AgrVac extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnAgr;
	private JButton btnCancel;
	private JTextField txtCod;
	private JTextField txtNombre;
	private JTextPane txtDescrip;
	private boolean control;
	private static Vacuna vac=null;

	

	
	public static void main(String[] args) {
		try {
			AgrVac dialog = new AgrVac(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public AgrVac(Vacuna vac) {
		setTitle("Agregar Vacuna al Inventario");
		setBounds(100, 100, 341, 252);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("C\u00F3digo");
			lblNewLabel.setBounds(12, 13, 56, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			txtCod = new JTextField();
			txtCod.setEditable(false);
			txtCod.setText("Vac-"+Clinica.getInstance().codigo);
			txtCod.setBounds(12, 36, 73, 22);
			contentPanel.add(txtCod);
			txtCod.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nombre");
			lblNewLabel_1.setBounds(188, 13, 56, 16);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(188, 36, 116, 22);
			contentPanel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Descripci\u00F3n");
			lblNewLabel_2.setBounds(12, 85, 73, 16);
			contentPanel.add(lblNewLabel_2);
		}
		
		txtDescrip = new JTextPane();
		txtDescrip.setBounds(12, 107, 199, 45);
		contentPanel.add(txtDescrip);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAgr = new JButton("Agregar Vacuna");
				btnAgr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!control) {
							Vacuna aux = new Vacuna(txtCod.getText(),txtNombre.getText(),txtDescrip.getText(),0);
							Clinica.getInstance().insertarVacuna(aux);
						    JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Registro", JOptionPane.INFORMATION_MESSAGE);				  
						    clear();
						}else {
							vac.setNombre(txtNombre.getText());
							vac.setDescrip(txtDescrip.getText());
							Clinica.getInstance().modificarVac(vac);
							dispose();
							ListVac.loadVacunas();
							
						}
						
					}
				});
				btnAgr.setActionCommand("OK");
				buttonPane.add(btnAgr);
				getRootPane().setDefaultButton(btnAgr);
			}
			{
				btnCancel = new JButton("Cancelar");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		loadVacuna();
	}
	
	public void loadVacuna() {
		if(vac != null) {
			txtCod.setText(vac.getId_vacuna());
			txtNombre.setText(vac.getNombre());
			txtDescrip.setText(vac.getDescrip());		}
	}

	protected void clear() {
		txtCod.setText("Vac-"+Clinica.getInstance().codigo);
		txtNombre.setText("");
		txtDescrip.setText("");
		
		
	}
}
*/
