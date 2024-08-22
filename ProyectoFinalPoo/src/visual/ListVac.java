package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Consulta;
import logico.Medico;
import logico.Paciente;
import logico.Vacuna;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListVac extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JButton btnModi;
    private JButton btnCancelar;
    private JTable tabla;
    private Vacuna select = null;
    private JButton btnElim;
    private static DefaultTableModel model;
    private static Object row[];
    private static Paciente miPaciente = null;
    private JButton btnNewButton;
    static String connectionUrl =
            "jdbc:sqlserver://192.168.100.118:1433;"
                    + "database=clinica_stanley_eduardo;"
                    + "user=s.gomez;" // Your username
                    + "password=Headphone1130Jack;" // Your password
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=30;";

    public ListVac(Paciente paciente) {
        Clinica.getInstance().setVacunaCodigo("");
        miPaciente = paciente;
        if (miPaciente != null)
            setTitle("Historial de Vacunas de " + miPaciente.getPersona().getNombre());
        else {
            setTitle("Listado de Vacunas");
        }
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
                    btnElim.setEnabled(true);
                    btnModi.setEnabled(true);
                    select = Clinica.getInstance().buscarVacuna(tabla.getValueAt(index, 0).toString());
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

        btnModi = new JButton("Modificar Datos");
        btnModi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModVac vacunaMod = new ModVac(select);
                vacunaMod.setModal(true);
                vacunaMod.setLocationRelativeTo(null);
                vacunaMod.setVisible(true);
                loadVacunas();
            }
        });
        btnNewButton = new JButton("Seleccionar Vacuna (Solo Consulta)");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (select != null) {
                    Clinica.getInstance().setVacunaCodigo(select.getId_vacuna());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Disculpe, parece que no hay un valor seleccionado aqui para: \n" + Clinica.getVacunaCodigo() + "\n Por favor, seleccione una Vacuna y intentalo de nuevo.\n", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        buttonPane.add(btnNewButton);

        btnModi.setActionCommand("OK");
        buttonPane.add(btnModi);
        getRootPane().setDefaultButton(btnModi);

        btnElim = new JButton("Eliminar");
        btnElim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (select != null) {
                    int option = JOptionPane.showConfirmDialog(null, "Está Seguro(a) que desea eliminar este empleado?" + select.getNombre(), "Confirmación", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        Clinica.getInstance().eliminarVac(select);
                        btnElim.setEnabled(true);
                        btnModi.setEnabled(true);
                        loadVacunas();
                    }
                }
            }
        });
        btnElim.setActionCommand("Cancel");
        buttonPane.add(btnElim);

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
             Statement statement = connection.createStatement()) {

            String sql = "SELECT v.id_vacuna, v.nombre, v.descripcion " +
                         "FROM vacuna v " +
                         "JOIN vacuna_dosis vd ON v.id_vacuna = vd.id_vacuna";

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                row[0] = resultSet.getInt("id_vacuna");
                row[1] = resultSet.getString("nombre");
                row[2] = resultSet.getString("descripcion");
                model.addRow(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}