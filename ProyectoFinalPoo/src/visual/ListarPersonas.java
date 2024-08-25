package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logico.Clinica;
import logico.Paciente;
import logico.Persona;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ListarPersonas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static DefaultTableModel modelo;
	private static Object[] fila;
	private static int seleccion;
	private JComboBox cbxCondicion;
	static JLabel lblNewLabel_1;
	private static Persona seleccionado = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarPersonas dialog = new ListarPersonas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarPersonas() {
		setTitle("Personas");
        setBounds(100, 100, 1086, 439);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        contentPanel.add(panel);
                        panel.setLayout(null);
                
                        JScrollPane scrollPane = new JScrollPane();
                        scrollPane.setBounds(7, 39, 1043, 313);
                        panel.add(scrollPane);
                        table = new JTable(modelo) {
                            @Override
                            public boolean isCellEditable(int fila, int columna) {
                                return false;
                            }
                        };
                        scrollPane.setViewportView(table);
        
                JLabel lblNewLabel = new JLabel("Filtros de Busqueda:");
                lblNewLabel.setBounds(872, 17, 98, 14);
                panel.add(lblNewLabel);
        
                lblNewLabel_1 = new JLabel("Cantidad de Personas en Lista: 0");
                lblNewLabel_1.setBounds(7, 14, 296, 14);
                panel.add(lblNewLabel_1);

        modelo = new DefaultTableModel();
        String[] headers = {"id_persona", "Cedula", "Nombre", "Apellido", "Nacimiento", "Sexo", "Direccion", "Telefono"};
        modelo.setColumnIdentifiers(headers);
        
                cbxCondicion = new JComboBox();
                cbxCondicion.setBounds(980, 14, 70, 20);
                cbxCondicion.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        seleccion = cbxCondicion.getSelectedIndex();
                        loadPersonas(seleccion);
                    }
                });
                cbxCondicion.setModel(new DefaultComboBoxModel(new String[]{"<Todos>", "Hombres", "Mujeres"}));
                panel.add(cbxCondicion);

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancelar.setActionCommand("Cancel");
        buttonPane.add(btnCancelar);

        loadPersonas(seleccion);
    }

    public static void loadPersonas(int seleccion) {
        modelo.setRowCount(0);
        fila = new Object[modelo.getColumnCount()];

        switch (seleccion) {
            case 0:
                for (Persona aux : Clinica.getInstance().getMisPersonas()) {
                    fila[0] = aux.getId_persona();
                    fila[1] = aux.getCedula();
                    fila[2] = aux.getNombre();
                    fila[3] = aux.getApellido();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String fechaNacimientoStr = dateFormat.format(aux.getFechaDeNacim());
                    fila[4] = fechaNacimientoStr;
                    fila[5] = aux.getSexo();
                    fila[6] = aux.getDireccion();
                    fila[7] = aux.getTelefono();

                    modelo.addRow(fila);
                }
                break;
            /*
            case 1: 
                // Implementación para Hombres
                break;
            case 2:
                // Implementación para Mujeres
                break;
            */
            default:
                break;
        }

        table.setModel(modelo);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(150);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(80);
        columnModel.getColumn(6).setPreferredWidth(120);
        columnModel.getColumn(7).setPreferredWidth(120);
        // lblNewLabel_1.setText("Cantidad de Pacientes en Lista: " + Clinica.getInstance().cantPacientes(seleccion));
    }
}

