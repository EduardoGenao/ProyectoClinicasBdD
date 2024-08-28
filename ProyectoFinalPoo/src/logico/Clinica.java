package logico;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Clinica implements Serializable{
	
	
	/**
	 * 
	 */
	static String connectionUrl =
            "jdbc:sqlserver://192.168.100.118:1433;"
                    + "database=clinica_stanley_eduardo;"
                    + "user=s.gomez;" //TU USER
                    + "password=Headphone1130Jack;" //TU CLAVE
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=30;";
	private static final long serialVersionUID = -656223844628211410L;
	private ArrayList<Persona> misPersonas;
	private ArrayList<Secretario> misSecretarios;
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Medico> misMedicos;
	private ArrayList<Cita> misCitas;
	private ArrayList<Vacuna> misVacunas;
	private ArrayList<Vacuna_Dosis> misVacuna_Dosis;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Cuenta> misAdministradores;
	private ArrayList<Tipo_Cita> misTipo_Citas;
	private static Cuenta loginAdministrador;
	public static int codigo = 1;
	public static int consultaCodigo = 1;
	public static int codigoEnf = 1;
	public static int citaCodigo = 1;
	public static int tipoCitaCodigo = 1;
	private static Clinica clinic = null;

	public static String personaCedula = "";
	public static String pacienteCedula = "";
	public static String secretarioCedula = "";
	public static String medicoCedula = "";
	public static String vacunaCodigo = "";
	public static String vacunaDosisCodigo = "";
	public static String enfermedadCodigo = "";
	public static String tipoCodigo = "";
	public static int vacuna_dosis_id = 5;
	public static int cant_miligramos = 0;
	public static String vacDosVacunaId = "";
	public static String vacDosConsultaId = "";
	
	
	public static Clinica getInstance() {
		if(clinic==null)
			clinic = new Clinica();
		return clinic;
	}

	
	public Clinica() {
		super();
		this.misPersonas = new ArrayList<Persona>();
		this.misSecretarios = new ArrayList<Secretario>();
		this.misPacientes = new ArrayList<Paciente>();
		this.misMedicos = new ArrayList<Medico>();
		this.misConsultas = new ArrayList<Consulta>();
		this.misCitas = new ArrayList<Cita>();
		this.misEnfermedades = new ArrayList<Enfermedad>();
		this.misVacunas = new ArrayList<Vacuna>();
		this.misVacuna_Dosis = new ArrayList<Vacuna_Dosis>();
		this.misAdministradores = new ArrayList<>();
	}
	

	
	public ArrayList<Paciente> getMisPacientes() {
		return Buscar_Datos_Test.fetchPacientes();
	}

	public void setMisPacientes(ArrayList<Paciente> misPacientes) {
		this.misPacientes = misPacientes;
	}

	public ArrayList<Medico> getMisMedicos() {
		return Buscar_Datos_Test.fetchMedicos();
	}

	public void setMisMedicos(ArrayList<Medico> misMedis) {
		this.misMedicos = misMedis;
	}
	
	public ArrayList<Vacuna> getMisVacunas() {
		return Buscar_Datos_Test.fetchVacunas();
	}


	public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
		this.misVacunas = misVacunas;
	}

	
	
	public ArrayList<Secretario> getMisSecretarios() {
		return Buscar_Datos_Test.fetchSecretarios();
	}


	public void setMisSecretarios(ArrayList<Secretario> misSecretarios) {
		this.misSecretarios = misSecretarios;
	}


	public ArrayList<Vacuna_Dosis> getMisVacuna_Dosis() {
		return misVacuna_Dosis;
	}


	public void setMisVacuna_Dosis(ArrayList<Vacuna_Dosis> misVacuna_Dosis) {
		this.misVacuna_Dosis = misVacuna_Dosis;
	}
	/*
	public void modificarPaciente(Paciente miPaciente) {
		int index = buscarIndexPacByCode(miPaciente.getPersona().getCedula());
		misPacientes.set(index, miPaciente);
	}
	*/
	public void modificarPaciente(Paciente miPaciente) {
	    Connection connection = null;
	    PreparedStatement stmtPersona = null;
	    PreparedStatement stmtPaciente = null;

	    try {
	        // Establish a connection to your SQL Server database
	        connection = DriverManager.getConnection(connectionUrl);

	        // SQL to update persona data
	        String updatePersona = "UPDATE persona SET nombre = ?, apellido = ?, fecha_de_nacimiento = ?, direccion = ?, sexo = ?, telefono_personal = ?, cedula = ? WHERE id_persona = ?";
	        stmtPersona = connection.prepareStatement(updatePersona);
	        stmtPersona.setString(1, miPaciente.getPersona().getNombre());
	        stmtPersona.setString(2, miPaciente.getPersona().getApellido());
	        //stmtPersona.setDate(3, java.sql.Date.valueOf(miPaciente.getPersona().getFechaDeNacim()));
	        java.sql.Date sqlFechaDeNacim = new java.sql.Date(miPaciente.getPersona().getFechaDeNacim().getTime());
	        stmtPersona.setDate(3, sqlFechaDeNacim);
	        stmtPersona.setString(4, miPaciente.getPersona().getDireccion());
	        stmtPersona.setString(5, miPaciente.getPersona().getSexo());
	        stmtPersona.setString(6, miPaciente.getPersona().getTelefono());
	        stmtPersona.setString(7, miPaciente.getPersona().getCedula());
	        stmtPersona.setInt(8, miPaciente.getPersona().getId_persona());

	        // Execute update for persona
	        stmtPersona.executeUpdate();

	        // SQL to update paciente data
	        String updatePaciente = "UPDATE paciente SET sangre = ?, contacto_emergencia = ? WHERE id_paciente = ?";
	        stmtPaciente = connection.prepareStatement(updatePaciente);
	        stmtPaciente.setString(1, miPaciente.getSangre());
	        stmtPaciente.setString(2, miPaciente.getContacto_emergencia());
	        stmtPaciente.setInt(3, miPaciente.getIdPaciente());

	        // Execute update for paciente
	        stmtPaciente.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close resources
	        try {
	            if (stmtPersona != null) stmtPersona.close();
	            if (stmtPaciente != null) stmtPaciente.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void modiMed(Medico misMedis) {
		int cont = buscarIndexMedByCode(misMedis.getPersona().getCedula());
		misMedicos.set(cont, misMedis);
	}
/*
	public Paciente buscarPaciente(String idPaciente) {
		Paciente temp = null;
		boolean encontrado = false;
		int i=0;
		while (!encontrado && i<misPacientes.size()) {
			System.out.println(misPacientes.get(i).getPersona().getCedula());
			if(misPacientes.get(i).getPersona().getCedula().equalsIgnoreCase(idPaciente)){
				temp = misPacientes.get(i);
				encontrado = true;
			}
			i++;
		}
		
		return temp;
	}
	*/
	
	public Paciente buscarPaciente(String cedula) {
	    Paciente paciente = null;

	    // SQL query to join `paciente` and `persona` tables and filter by `cedula`
	    String query = "SELECT p.id_paciente, p.sangre, p.contacto_emergencia, " +
	                   "per.id_persona, per.nombre, per.apellido, per.fecha_de_nacimiento, " +
	                   "per.direccion, per.sexo, per.telefono_personal, per.cedula " +
	                   "FROM paciente p " +
	                   "JOIN persona per ON p.id_persona = per.id_persona " +
	                   "WHERE per.cedula = ?";

	    try (Connection connection = DriverManager.getConnection(connectionUrl);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setString(1, cedula); // Set the cedula parameter
	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            // Extract Persona data
	            int idPersona = resultSet.getInt("id_persona");
	            String nombre = resultSet.getString("nombre");
	            String apellido = resultSet.getString("apellido");
	            Date fechaDeNacimiento = resultSet.getDate("fecha_de_nacimiento");
	            String direccion = resultSet.getString("direccion");
	            String sexo = resultSet.getString("sexo");
	            String telefonoPersonal = resultSet.getString("telefono_personal");
	            String personaCedula = resultSet.getString("cedula"); // Get cedula

	            // Create Persona object
	            Persona persona = new Persona(idPersona, nombre, apellido, fechaDeNacimiento, direccion, sexo, telefonoPersonal, personaCedula);

	            // Extract Paciente data
	            int idPaciente = resultSet.getInt("id_paciente");
	            String sangre = resultSet.getString("sangre");
	            String contactoEmergencia = resultSet.getString("contacto_emergencia");

	            // Create Paciente object
	            paciente = new Paciente(idPaciente, sangre, contactoEmergencia, persona);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return paciente;
	}
	
	
	public Persona buscarPersona(String idPersona) {
		Persona temp = null;
		boolean encontrado = false;
		int i=0;
		while (!encontrado && i<misPersonas.size()) {
			if(misPersonas.get(i).getCedula().equalsIgnoreCase(idPersona)){
				temp = misPersonas.get(i);
				encontrado = true;
			}
			i++;
		}
		
		return temp;
	}
	

	public int cantPacientes(int seleccion) {
		int cant = 0;
		
		for(Paciente aux : misPacientes) {
			switch(seleccion) {
			case 0:
				cant++;
				break;
			/*
			case 1:
				if(aux.isEstado() == false)
					cant++;
				break;
			case 2:
				if(aux.isEstado() == true)
					cant++;
				break;
			
			case 1:
				if(aux.getSexo() == 'H')
					cant++;
				break;
			case 2: 
				if(aux.getSexo() == 'M')
					cant++;
				break;
				*/
			default: 
				break;
			}
		}
		
		return cant;
	}

	
	/*
	public Medico buscarMedico(String cedu) {
		Medico temp = null;
		boolean encontrado = false;
		int i=0;
		while (!encontrado && i<misMedicos.size()) {
			if(misMedicos.get(i).getPersona().getCedula().equalsIgnoreCase(cedu)){
				temp = misMedicos.get(i);
				encontrado = true;
			}
			i++;
		}
		
		return temp;
	}
	*/
	
	public Medico buscarMedico(String cedula) {
	    Medico medico = null;

	    // SQL query to join `medico` and `persona` tables and filter by `cedula`
	    String query = "SELECT m.id_medico, m.telefono_trabajo, m.id_especialidad, m.id_cuenta, " +
	                   "per.id_persona, per.nombre, per.apellido, per.fecha_de_nacimiento, " +
	                   "per.direccion, per.sexo, per.telefono_personal, per.cedula " +
	                   "FROM medico m " +
	                   "JOIN persona per ON m.id_persona = per.id_persona " +
	                   "WHERE per.cedula = ?";

	    try (Connection connection = DriverManager.getConnection(connectionUrl);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setString(1, cedula); // Set the cedula parameter
	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            // Extract Persona data
	            int idPersona = resultSet.getInt("id_persona");
	            String nombre = resultSet.getString("nombre");
	            String apellido = resultSet.getString("apellido");
	            Date fechaDeNacimiento = resultSet.getDate("fecha_de_nacimiento");
	            String direccion = resultSet.getString("direccion");
	            String sexo = resultSet.getString("sexo");
	            String telefonoPersonal = resultSet.getString("telefono_personal");
	            String personaCedula = resultSet.getString("cedula"); // Get cedula

	            // Create Persona object
	            Persona persona = new Persona(idPersona, nombre, apellido, fechaDeNacimiento, direccion, sexo, telefonoPersonal, personaCedula);

	            // Extract Medico data
	            int idMedico = resultSet.getInt("id_medico");
	            String telefonoTrabajo = resultSet.getString("telefono_trabajo");
	            int idEspecialidad = resultSet.getInt("id_especialidad");
	            int idCuenta = resultSet.getInt("id_cuenta");

	            // Create Medico object
	            medico = new Medico(idMedico, telefonoTrabajo, idEspecialidad, idCuenta, persona);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return medico;
	}
	/*
	public Enfermedad buscarEnfermedad(String idEnfermedad) {
		Enfermedad temp = null;
		boolean encontrado = false;
		int i=0;
		while (!encontrado && i<misEnfermedades.size()) {
			if(misEnfermedades.get(i).getId().equalsIgnoreCase(idEnfermedad)){
				temp = misEnfermedades.get(i);
				encontrado = true;
			}
			i++;
		}
		
		return temp;
	}
	*/
	
	
	public Enfermedad buscarEnfermedad(String idEnfermedad) {
	    Enfermedad enfermedad = null;
	    Connection connection = null;
	    Statement statement = null;
	    ResultSet resultSet = null;

	    String connectionUrl =
	        "jdbc:sqlserver://192.168.100.118:1433;" +
	        "database=clinica_stanley_eduardo;" +
	        "user=s.gomez;" + // Your username
	        "password=Headphone1130Jack;" + // Your password
	        "encrypt=true;" +
	        "trustServerCertificate=true;" +
	        "loginTimeout=30;";
	    
	    try {
	        // Establish the connection
	        connection = DriverManager.getConnection(connectionUrl);
	        statement = connection.createStatement();

	        // SQL query to find the Enfermedad by id_enfermedad
	        String sql = "SELECT id_enfermedad, nombre, descripcion, permanente " +
	                     "FROM enfermedad " +
	                     "WHERE id_enfermedad = '" + idEnfermedad + "';";

	        resultSet = statement.executeQuery(sql);

	        // Check if we have a result
	        if (resultSet.next()) {
	            // Retrieve Enfermedad data
	            String id = resultSet.getString("id_enfermedad");
	            String nombre = resultSet.getString("nombre");
	            String descripcion = resultSet.getString("descripcion");
	            boolean permanente = resultSet.getBoolean("permanente");

	            // Create Enfermedad object
	            enfermedad = new Enfermedad(id, nombre, descripcion, permanente);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return enfermedad;
	}
	
	
	
	/*
	public Secretario buscarSecretario(String text) {
		Secretario temp = null;
		boolean encontrado = false;
		int i=0;
		while (!encontrado && i<misSecretarios.size()) {
			if(misSecretarios.get(i).getPersona().getCedula().equalsIgnoreCase(text)){
				temp = misSecretarios.get(i);
				encontrado = true;
			}
			i++;
		}
		
		return temp;
	}
	*/
	
	public Secretario buscarSecretario(String cedula) {
	    Secretario secretario = null;

	    // SQL query to join `secretario` and `persona` tables and filter by `cedula`
	    String query = "SELECT s.id_secretario, s.telefono_trabajo, s.id_cuenta, " +
	                   "per.id_persona, per.nombre, per.apellido, per.fecha_de_nacimiento, " +
	                   "per.direccion, per.sexo, per.telefono_personal, per.cedula " +
	                   "FROM secretario s " +
	                   "JOIN persona per ON s.id_persona = per.id_persona " +
	                   "WHERE per.cedula = ?";

	    try (Connection connection = DriverManager.getConnection(connectionUrl);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setString(1, cedula); // Set the cedula parameter
	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            // Extract Persona data
	            int idPersona = resultSet.getInt("id_persona");
	            String nombre = resultSet.getString("nombre");
	            String apellido = resultSet.getString("apellido");
	            Date fechaDeNacimiento = resultSet.getDate("fecha_de_nacimiento");
	            String direccion = resultSet.getString("direccion");
	            String sexo = resultSet.getString("sexo");
	            String telefonoPersonal = resultSet.getString("telefono_personal");
	            String personaCedula = resultSet.getString("cedula"); // Get cedula

	            // Create Persona object
	            Persona persona = new Persona(idPersona, nombre, apellido, fechaDeNacimiento, direccion, sexo, telefonoPersonal, personaCedula);

	            // Extract Secretario data
	            int idSecretario = resultSet.getInt("id_secretario");
	            String telefonoTrabajo = resultSet.getString("telefono_trabajo");
	            String idCuenta = resultSet.getString("id_cuenta"); // Assuming id_cuenta is a String in the database

	            // Retrieve Cuenta object by id
	            Cuenta cuenta = getCuentaById(idCuenta);

	            // Check if cuenta was found
	            if (cuenta != null) {
	                // Create Secretario object
	                secretario = new Secretario(idSecretario, telefonoTrabajo, cuenta, persona);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return secretario;
	}

	public Cuenta getCuentaById(String idCuenta) {
	    Cuenta cuenta = null;

	    // SQL query to select the account based on the id_cuenta
	    String query = "SELECT id_cuenta, usuario, contrasena " +
	                   "FROM cuenta " +
	                   "WHERE id_cuenta = ?";

	    try (Connection connection = DriverManager.getConnection(connectionUrl);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setString(1, idCuenta); // Set the id_cuenta parameter
	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            // Extract Cuenta data
	            String idCuentaResult = resultSet.getString("id_cuenta");
	            String usuario = resultSet.getString("usuario");
	            String contrasena = resultSet.getString("contrasena");

	            // Create Cuenta object
	            cuenta = new Cuenta(idCuentaResult, usuario, contrasena);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return cuenta;
	}
	
	public Tipo_Cita getTipoCitaById(String idTipo) {
        Tipo_Cita tipoCita = null;

        // SQL query to select the tipo_cita based on the id_tipo_cita
        String query = "SELECT id_tipo_cita, nombre " +
                       "FROM tipo_cita " +
                       "WHERE id_tipo_cita = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, idTipo); // Set the id_tipo_cita parameter
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Extract TipoCita data
                String idTipoResult = resultSet.getString("id_tipo_cita");
                String nombre = resultSet.getString("nombre");

                // Create TipoCita object
                tipoCita = new Tipo_Cita(idTipoResult, nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipoCita;
    }
	
	
	public Consulta buscarConsulta(String idConsulta) {
		Consulta temp = null;
		boolean encontrado = false;
		int i=0;
		while (!encontrado && i<misConsultas.size()) {
			if(misConsultas.get(i).getId_consulta().equalsIgnoreCase(idConsulta)){
				temp =  misConsultas.get(i);
				encontrado = true;
			}
			i++;
		}
		
		return temp;
	}
	
	public void RegistrarCita(Cita cita) {
		//misCitas.add(aux);		
		String sql = "INSERT INTO cita (fecha_cita, asistencia, id_secretario, id_medico, id_paciente, id_tipo_cita) VALUES ( ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(connectionUrl);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setTimestamp(1, new java.sql.Timestamp(cita.getFecha_cita().getTime()));
            pstmt.setBoolean(2, cita.isAsistencia());
            pstmt.setInt(3, cita.getSecretario().getId_secretario());
            pstmt.setInt(4, cita.getId_medico().getId_medico());
            pstmt.setInt(5, cita.getPaciente().getIdPaciente());
            pstmt.setInt(6, Integer.parseInt(cita.getId_tipo_cita().getId_tipo()));

            pstmt.executeUpdate();
            System.out.println("Cita registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	/*
	public Vacuna buscarVacuna(String idVacuna) {
		Vacuna temp = null;
		boolean encontrado = false;
		int i=0;
		while (!encontrado && i<misVacunas.size()) {
			if(misVacunas.get(i).getId_vacuna().equalsIgnoreCase(idVacuna)){
				temp =  misVacunas.get(i);
				encontrado = true;
			}
			i++;
		}
		
		return temp;
	}
	*/
	
	public Vacuna buscarVacuna(String idVacuna) {
	    Vacuna vacuna = null;
	    Connection connection = null;
	    Statement statement = null;
	    ResultSet resultSet = null;
	    
	    String connectionUrl =
	        "jdbc:sqlserver://192.168.100.118:1433;" +
	        "database=clinica_stanley_eduardo;" +
	        "user=s.gomez;" + // Your username
	        "password=Headphone1130Jack;" + // Your password
	        "encrypt=true;" +
	        "trustServerCertificate=true;" +
	        "loginTimeout=30;";
	    
	    try {
	        // Establish the connection
	        connection = DriverManager.getConnection(connectionUrl);
	        statement = connection.createStatement();

	        // SQL query to find the Vacuna by id_vacuna
	        String sql = "SELECT v.id_vacuna, v.nombre, v.descripcion, v.id_enfermedad, e.nombre AS nombre_enfermedad, e.descripcion AS descripcion_enfermedad, e.permanente " +
	                     "FROM vacuna v " +
	                     "LEFT JOIN enfermedad e ON v.id_enfermedad = e.id_enfermedad " +
	                     "WHERE v.id_vacuna = '" + idVacuna + "';";

	        resultSet = statement.executeQuery(sql);

	        // Check if we have a result
	        if (resultSet.next()) {
	            // Retrieve Vacuna data
	            String id = resultSet.getString("id_vacuna");
	            String nombre = resultSet.getString("nombre");
	            String descripcion = resultSet.getString("descripcion");

	            // Retrieve associated Enfermedad data if available
	            String idEnfermedad = resultSet.getString("id_enfermedad");
	            Enfermedad enfermedad = null;
	            if (idEnfermedad != null) {
	                String nombreEnfermedad = resultSet.getString("nombre_enfermedad");
	                String descripcionEnfermedad = resultSet.getString("descripcion_enfermedad");
	                boolean permanente = resultSet.getBoolean("permanente");
	                
	                // Create Enfermedad object
	                enfermedad = new Enfermedad(idEnfermedad, nombreEnfermedad, descripcionEnfermedad, permanente);
	            }

	            // Create Vacuna object
	            vacuna = new Vacuna(id, nombre, descripcion, enfermedad);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return vacuna;
	}
	
	public Vacuna_Dosis buscarVacuna_Dosis(String idVacuna_Dosis) {
		Vacuna_Dosis temp = null;
		boolean encontrado = false;
		int i=0;
		while (!encontrado && i<misVacuna_Dosis.size()) {
			if(misVacunas.get(i).getId_vacuna().equalsIgnoreCase(idVacuna_Dosis)){
				temp =  misVacuna_Dosis.get(i);
				encontrado = true;
			}
			i++;
		}
		
		return temp;
	}
	
	private int buscarIndexPacByCode(String codigo) {
		int aux = -1;
		boolean encontrado = false;
		int i=0;
		while (!encontrado && i<misPacientes.size()) {
			if(misPacientes.get(i).getPersona().getCedula().equalsIgnoreCase(codigo)){
				aux = i;
				encontrado = true;
			}
			i++;
		}
		
		return aux;
	}
	
	private int buscarIndexMedByCode(String cedu) {
		int aux = -1;
		boolean encontrado = false;
		int i=0;
		while (i<misMedicos.size()&& !encontrado) {
			if(misMedicos.get(i).getPersona().getCedula().equalsIgnoreCase(cedu)){
				encontrado = true;
				aux = i;
			}
			i++;
		}
		
		return aux;
	}
	
	private int buscarIndexVacByCode(String code) {
		int aux = -1;
		boolean encontrado = false;
		int i=0;
		while (i<misVacunas.size()&& !encontrado) {
			if(misVacunas.get(i).getId_vacuna().equalsIgnoreCase(code)){
				encontrado = true;
				aux = i;
			}
			i++;
		}
		
		return aux;
	}
	

	public ArrayList<Cita> getMisCitas() {
		return Buscar_Datos_Test.getCitas();
	}


	public void setMisCitas(ArrayList<Cita> misCitas) {
		this.misCitas = misCitas;
	}

	public void insertarPaciente(Paciente miPaciente) {
		misPacientes.add(miPaciente);
	}
	
	public void insertarMedico(Medico misMedis) {
		misMedicos.add(misMedis);
	}
	
	
	public void eliminarMedico(Medico select) {
		int cont;
		cont = misMedicos.indexOf(select);
		misMedicos.remove(cont);
	}
	
	
	public void insertarVacuna(Vacuna misVacs) {
		misVacunas.add(misVacs);
		codigo++;
	}

	public void insertarEnfermedad(Enfermedad miEnfermedad) {
		misEnfermedades.add(miEnfermedad);
		codigoEnf++;
	}
	
	public void insertarConsulta(Consulta miConsulta) {
		misConsultas.add(miConsulta);
		consultaCodigo++;
		vacuna_dosis_id++;
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}


	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}


	public ArrayList<Enfermedad> getMisEnfermedades() {
		return Buscar_Datos_Test.fetchEnfermedades();
	}


	public void setMisEnfermedades(ArrayList<Enfermedad> misEnfermedades) {
		this.misEnfermedades = misEnfermedades;
	}
	
	public int cantPacientesPorEnfermedad(String idEnfermedad) {
		int cant = 0;
		/*
		for(Consulta aux : misConsultas) {
			if(aux.getPaciente().isEstado() == true && aux.getDiagnostico().getId().equalsIgnoreCase(idEnfermedad))
				cant++;
		}
		*/
		return cant;
	}


	public static String getPacienteCedula() {
		return pacienteCedula; 
	}


	public void setPacienteCedula(String pacienteCedula) {
		this.pacienteCedula = pacienteCedula;
	}


	public static String getMedicoCedula() {
		return medicoCedula;
	}


	public void setMedicoCedula(String medicoCedula) {
		this.medicoCedula = medicoCedula;
	}
	
	
	


	public void modificarVac(Vacuna vac) {
		int cont = buscarIndexVacByCode(vac.getId_vacuna());
		misVacunas.set(cont, vac);

	}
	
	public void eliminarVac(Vacuna select) {
		int cont;
		cont = misVacunas.indexOf(select);
		misVacunas.remove(cont);
	}



	
	public static String getVacunaCodigo() {
		return vacunaCodigo;
	}


	public void setVacunaCodigo(String vacunaCodigo) {
		this.vacunaCodigo = vacunaCodigo;

	}

	public static Clinica getClinic() {
		return clinic;
	}

	public static void setClinic(Clinica clinic) {
		Clinica.clinic = clinic;
	}

	public ArrayList<Cuenta> getMisAdministradores() {
		return misAdministradores;
	}


	public void setMisAdministradores(ArrayList<Cuenta> misAdministradores) {
		this.misAdministradores = misAdministradores;
	}
	
	public static Cuenta getLoginAdministrador() {
		return loginAdministrador;
	}


	public static void setLoginAdministrador(Cuenta loginAdministrador) {
		Clinica.loginAdministrador = loginAdministrador;
	}

	public void regAdmin(Cuenta loginAdministrador) {
		misAdministradores.add(loginAdministrador);
	}
	
	/*vacuna
	private void vacunasUsadas(){
		for (Vacuna vac : misVacunas) {
			tipo = vac.getTipo();
			
			if(tipo != tipomax)
			{
				for (Vacuna vacs : misVacunas) {
					if(vacs.getTipo() == tipo)
						cant++;
				}	
			}
			
			if(cant > cantmax)
			{
				cantmax = cant;
				tipomax = tipo;
				cant = 0;
			}
				
	
		}
	}*/
	
	public boolean confirmLogin(String usuario, String contrasena) {
	    boolean login = false;
	    Connection connection = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    
	    try {
	        // Establish a connection (Make sure the connection details are correct)
	        String url = "jdbc:sqlserver://localhost;databaseName=yourDatabase";
	        String user = "yourUsername";
	        String password = "yourPassword";
	        connection = DriverManager.getConnection(connectionUrl);
	        
	        // Query to check if the provided username and password match any record
	        String query = "SELECT COUNT(*) FROM cuenta WHERE usuario = ? AND contrasena = ?";
	        stmt = connection.prepareStatement(query);
	        stmt.setString(1, usuario);
	        stmt.setString(2, contrasena);
	        
	        rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            // If the count is greater than 0, credentials are valid
	            login = rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close resources
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return login;
	}



	public static String getEnfermedadCodigo() {
		return enfermedadCodigo;
	}


	public void setEnfermedadCodigo(String enfermedadCodigo) {
		this.enfermedadCodigo = enfermedadCodigo;
	}


	public ArrayList<Persona> getMisPersonas() {
		return Buscar_Datos_Test.obtenerTodasLasPersonas();
	}


	public void setMisPersonas(ArrayList<Persona> misPersonas) {
		this.misPersonas = misPersonas;
	}


	public void modificarPersona(Persona miPersona) {
		int index = buscarIndexPerByCode(miPersona.getCedula());
		misPersonas.set(index, miPersona);
		
	}


	private int buscarIndexPerByCode(String cedula) {
		int aux = -1;
		boolean encontrado = false;
		int i=0;
		while (!encontrado && i<misPersonas.size()) {
			if(misPersonas.get(i).getCedula().equalsIgnoreCase(cedula)){
				aux = i;
				encontrado = true;
			}
			i++;
		}
		
		return aux;
	}


	public void insertarPersona(Persona miPersona) {
		misPersonas.add(miPersona);
		
	}


	public static String setPersonaCedula() {
		return personaCedula; 
		
	}


	public void setSecretarioCedula(String secretarioCedula) {
		this.secretarioCedula = secretarioCedula;
	}


	public static String getSecretarioCedula() {
		return secretarioCedula;
	}
	
	public static String getVacuna_DosisCodigo() {
		return vacunaDosisCodigo;
	}
	
	public void setVacuna_DosisCodigo(String vacunaDosisCodigo) {
		this.vacunaDosisCodigo = vacunaDosisCodigo;
	}
	
	//public static int cant_miligramos = 0;
	//public static String vacDosVacunaId = "";
	//public static String vacDosConsultaId = "";
	public static int getCantMiligramos() {
		return cant_miligramos;
	}
	
	public void setCantMiligramos(int CantMiligramos) {
		this.cant_miligramos = CantMiligramos;
	}
	public static String getVacDosVacunaId() {
		return vacDosVacunaId;
	}
	
	public void setVacDosVacunaId(String vacDosVacunaId) {
		this.vacDosVacunaId = vacDosVacunaId;
	}
	public static String getVacDosConsultaId() {
		return vacDosConsultaId;
	}
	
	public void setVacDosConsultaId(String vacDosConsultaId) {
		this.vacDosConsultaId = vacDosConsultaId;
	}


	public ArrayList<Tipo_Cita> getMisTipo_Citas() {
		return misTipo_Citas;
	}


	public void setMisTipo_Citas(ArrayList<Tipo_Cita> misTipo_Citas) {
		this.misTipo_Citas = misTipo_Citas;
	}
	
	public static String getTipoCitaCodigo() {
		return tipoCodigo;
	}
	
	public void setTipoCitaCodigo(String tipoCodigo) {
		this.tipoCodigo = tipoCodigo;
	}
	


	
}


