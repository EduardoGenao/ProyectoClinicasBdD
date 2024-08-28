package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import logico.Clinica;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import java.awt.SystemColor;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	static Socket sfd = null;
	static DataInputStream EntradaSocket;
	static DataOutputStream SalidaSocket;

	
	
	 // Launch the application.
	 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() { 
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 **/
	public Principal() {
		setForeground(SystemColor.textHighlight);
		setBackground(new Color(0, 120, 215));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream clinica2;
				ObjectOutputStream clinicaWrite;
				try {
					clinica2 = new FileOutputStream("clinica.dat");
					clinicaWrite = new ObjectOutputStream(clinica2);
					clinicaWrite.writeObject(Clinica.getInstance());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		setTitle("Menu de Clinica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("Button.shadow"));
		menuBar.setForeground(UIManager.getColor("Button.shadow"));
		setJMenuBar(menuBar);
		JMenu mnNewMenu = new JMenu("Paciente");
		mnNewMenu.setForeground(new Color(255, 128, 0));
		mnNewMenu.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		menuBar.add(mnNewMenu);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height-35);
		setLocationRelativeTo(null);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Registrar Paciente");
		mntmNewMenuItem.setFont(new Font("Segoe Print", Font.BOLD, 14));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				RegPaciente reg = new RegPaciente(null, null);
				reg.setModal(true);
				reg.setLocationRelativeTo(null);
				reg.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Listar Pacientes");
		mntmNewMenuItem_5.setFont(new Font("Segoe Print", Font.BOLD, 14));
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarPaciente list = new ListarPaciente();
				list.setModal(true);
				list.setLocationRelativeTo(null);
				list.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_1 = new JMenu("Medico");
		mnNewMenu_1.setForeground(new Color(255, 128, 0));
		mnNewMenu_1.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Registrar Medico");
		mntmNewMenuItem_1.setFont(new Font("Segoe Print", Font.BOLD, 14));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegMed reg = new RegMed(null);
				reg.setModal(true);
				reg.setLocationRelativeTo(null);
				reg.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Listar Medicos");
		mntmNewMenuItem_11.setFont(new Font("Segoe Print", Font.BOLD, 14));
		mntmNewMenuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListMed list = new ListMed();
				list.setModal(true);
				list.setLocationRelativeTo(null);
				list.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_11);
		
		JMenu mnNewMenu_7 = new JMenu("Secretario");
		menuBar.add(mnNewMenu_7);
		
		JMenuItem mntmNewMenuItem_13 = new JMenuItem("Listar Secretario");
		mntmNewMenuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListSecretario list = new ListSecretario();
				list.setModal(true);
				list.setLocationRelativeTo(null);
				list.setVisible(true);
			}
		});
		mnNewMenu_7.add(mntmNewMenuItem_13);
		
		JMenu mnNewMenu_5 = new JMenu("Enfermedad");
		mnNewMenu_5.setForeground(new Color(255, 128, 0));
		mnNewMenu_5.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		menuBar.add(mnNewMenu_5);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Agregar Enfermedad");
		mntmNewMenuItem_4.setFont(new Font("Segoe Print", Font.BOLD, 14));
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarEnfermedad frame = new RegistrarEnfermedad();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Listar Enfermedad");
		mntmNewMenuItem_10.setFont(new Font("Segoe Print", Font.BOLD, 14));
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaEnfermedades dialog = new ListaEnfermedades(null);
				dialog.setModal(true);
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem_10);
		
		JMenu mnNewMenu_2 = new JMenu("Cita");
		mnNewMenu_2.setForeground(new Color(255, 128, 0));
		mnNewMenu_2.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Hacer una Cita");
		mntmNewMenuItem_2.setFont(new Font("Segoe Print", Font.BOLD, 14));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegCitas citas = new RegCitas();
				//citas.setModal(true);
				citas.setLocationRelativeTo(null);
				citas.setVisible(true);
				
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Listar Citas Pendientes");
		mntmNewMenuItem_3.setFont(new Font("Segoe Print", Font.BOLD, 14));
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListCitas dialog = new ListCitas();
				dialog.setVisible(true);
				
				
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_3 = new JMenu("Consulta");
		mnNewMenu_3.setForeground(new Color(255, 128, 0));
		mnNewMenu_3.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Hacer una Consulta");
		mntmNewMenuItem_6.setFont(new Font("Segoe Print", Font.BOLD, 14));
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegConsulta reg = new RegConsulta();
				reg.setModal(true);
				reg.setLocationRelativeTo(null);
				reg.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Listar Consultas");
		mntmNewMenuItem_7.setFont(new Font("Segoe Print", Font.BOLD, 14));
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListConsultas list = new ListConsultas(null);
				list.setModal(true);
				list.setLocationRelativeTo(null);
				list.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_7);
		
		JMenu mnNewMenu_4 = new JMenu("Vacuna");
		mnNewMenu_4.setForeground(new Color(255, 128, 0));
		mnNewMenu_4.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Agregar Vacuna");
		mntmNewMenuItem_8.setFont(new Font("Segoe Print", Font.BOLD, 14));
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgrVac vacunas = new AgrVac(null);
				vacunas.setModal(true);
				vacunas.setLocationRelativeTo(null);
				vacunas.setVisible(true);
			}
		});

		mnNewMenu_4.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Listar Vacunas");
		mntmNewMenuItem_9.setFont(new Font("Segoe Print", Font.BOLD, 14));
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListVac vacunas = new ListVac(null);
				vacunas.setModal(true);
				vacunas.setLocationRelativeTo(null);
				vacunas.setVisible(true);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_9);
		
		JMenu menu = new JMenu("Admin");
		menu.setForeground(new Color(128, 64, 0));
		menu.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("Registrar");
		menuItem.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegAdmin reg = new RegAdmin();
				reg.setModal(true);
				reg.setLocationRelativeTo(null);
				reg.setVisible(true);
			}
		});
		menu.add(menuItem);
		
		JMenu mnNewMenu_6 = new JMenu("Respaldo");
		mnNewMenu_6.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		mnNewMenu_6.setForeground(new Color(128, 64, 0));
		menuBar.add(mnNewMenu_6);
		
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("Crear Respaldo");
		mntmNewMenuItem_12.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
			    {
				  
				  DataInputStream archivoOriginal = new DataInputStream(new FileInputStream("clinica.dat"));
			      sfd = new Socket("127.0.0.1",7000);
			      //EntradaSocket = new DataInputStream(new BufferedInputStream(sfd.getInputStream()));
			      SalidaSocket = new DataOutputStream(new BufferedOutputStream(sfd.getOutputStream()));
			      int byteLeido;
			      try {
			    	  while((byteLeido = archivoOriginal.read()) != -1)
				    	  SalidaSocket.write(byteLeido);
			      }finally {
			    	  archivoOriginal.close();
				      SalidaSocket.flush(); 
				}
			      
			    }
			    catch (UnknownHostException uhe)
			    {
			      System.out.println("No se puede acceder al servidor.");
			      System.exit(1);
			    }
			    catch (IOException ioe)
			    {
			      System.out.println("Comunicaci�n rechazada." + ioe.getMessage());
			      System.exit(1);
			    }
			}
		});
		mnNewMenu_6.add(mntmNewMenuItem_12);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		ImageIcon image = new ImageIcon(getClass().getResource("/white_background_image.jpeg"));
		lblNewLabel.setIcon(image);
		lblNewLabel.setBounds(0, 0, 1256, 606);
		panel.add(lblNewLabel);
		
	}
}