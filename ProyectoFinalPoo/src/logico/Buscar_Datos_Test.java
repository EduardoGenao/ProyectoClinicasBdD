package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Buscar_Datos_Test {
	
	static String connectionUrl =
            "jdbc:sqlserver://192.168.100.118:1433;"
                    + "database=clinica_stanley_eduardo;"
                    + "user=s.gomez;" //TU USER
                    + "password=Headphone1130Jack;" //TU CLAVE
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=30;";

	public static void main(String[] args) {
		
	
	    try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            
            String pacienteQuery = "SELECT * FROM paciente";
            String personaQuery = "SELECT * FROM persona";

            // Create statement objects
            Statement pacienteStatement = connection.createStatement();
            Statement personaStatement = connection.createStatement();

            // Execute queries and get results
            ResultSet pacienteResultSet = pacienteStatement.executeQuery(pacienteQuery);
            ResultSet personaResultSet = personaStatement.executeQuery(personaQuery);

            // Print data from paciente table
            System.out.println("Paciente Table:");
            while (pacienteResultSet.next()) {
                int idPaciente = pacienteResultSet.getInt("id_paciente");
                String sangre = pacienteResultSet.getString("sangre");
                String contactoEmergencia = pacienteResultSet.getString("contacto_emergencia");
                int idPersona = pacienteResultSet.getInt("id_persona");
                System.out.println("ID: " + idPaciente + ", Sangre: " + sangre + 
                                   ", Contacto Emergencia: " + contactoEmergencia + 
                                   ", ID Persona: " + idPersona);
            }

          
            System.out.println("\nPersona Table:");
            while (personaResultSet.next()) {
                int idPersona = personaResultSet.getInt("id_persona");
                String nombre = personaResultSet.getString("nombre");
                String apellido = personaResultSet.getString("apellido");
                String fechaDeNacimiento = personaResultSet.getDate("fecha_de_nacimiento").toString();
                String direccion = personaResultSet.getString("direccion");
                String sexo = personaResultSet.getString("sexo");
                String telefonoPersonal = personaResultSet.getString("telefono_personal");
           
                System.out.println("ID: " + idPersona + ", Nombre: " + nombre + 
                                   ", Apellido: " + apellido + 
                                   ", Fecha de Nacimiento: " + fechaDeNacimiento + 
                                   ", Dirección: " + direccion + 
                                   ", Sexo: " + sexo + 
                                   ", Teléfono: " + telefonoPersonal);
            }

           
            pacienteResultSet.close();
            personaResultSet.close();
            pacienteStatement.close();
            personaStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}
	
	
	//****** Esta es una de varias funciones que hay que crear para listar y modificar datos
	public static ArrayList<Paciente> fetchPacientes() {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        String query = "SELECT * FROM paciente";
        String personaQuery = "SELECT * FROM persona WHERE id_persona = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement statement = connection.prepareStatement(query);
        	 PreparedStatement personaStatement = connection.prepareStatement(personaQuery);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Paciente paciente = new Paciente(0, query, query, null);
                paciente.setIdPaciente(resultSet.getInt("id_paciente"));
                paciente.setSangre(resultSet.getString("sangre"));
                paciente.setContacto_emergencia(resultSet.getString("contacto_emergencia"));
                
                int idPersona = resultSet.getInt("id_persona");
                personaStatement.setInt(1, idPersona);
                ResultSet personaResultSet = personaStatement.executeQuery();
                
                if (personaResultSet.next()) {
                    Persona persona = new Persona(
                        personaResultSet.getInt("id_persona"),
                        personaResultSet.getString("nombre"),
                        personaResultSet.getString("apellido"),
                        personaResultSet.getDate("fecha_de_nacimiento"),
                        personaResultSet.getString("direccion"),
                        personaResultSet.getString("sexo"),
                        personaResultSet.getString("telefono_personal"),
                        personaResultSet.getString("cedula") 
                    );

                    paciente.setPersona(persona); 
                }
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacientes;
    }
	
	
	public static void createPersona(Persona persona) throws SQLException {
        String insertPersonaSQL = "INSERT INTO persona (nombre, apellido, fecha_de_nacimiento, direccion, sexo, telefono_personal, cedula) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int generatedId = 0;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement pstmt = connection.prepareStatement(insertPersonaSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, persona.getNombre());
            pstmt.setString(2, persona.getApellido());
            pstmt.setDate(3, new java.sql.Date(persona.getFechaDeNacim().getTime()));
            pstmt.setString(4, persona.getDireccion());
            pstmt.setString(5, persona.getSexo());
            pstmt.setString(6, persona.getTelefono());
            pstmt.setString(7, persona.getCedula());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                    }
                }
            }
        }
        
    }

    public static void createPaciente(Paciente paciente) throws SQLException {
        String insertPacienteSQL = "INSERT INTO paciente (sangre, contacto_emergencia, id_persona) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement pstmt = connection.prepareStatement(insertPacienteSQL)) {

            pstmt.setString(1, paciente.getSangre());
            pstmt.setString(2, paciente.getContacto_emergencia());
            pstmt.setInt(3, paciente.getPersona().getId_persona());

            pstmt.executeUpdate();
        }
    }
    
    
    
	public static ArrayList<Persona> obtenerTodasLasPersonas() {
        ArrayList<Persona> personas = new ArrayList<>();
        String query = "SELECT * FROM persona";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id_persona = resultSet.getInt("id_persona");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                Date fechaDeNacim = resultSet.getDate("fecha_de_nacimiento");
                String direccion = resultSet.getString("direccion");
                String sexo = resultSet.getString("sexo");
                String telefono = resultSet.getString("telefono_personal");
                String cedula = resultSet.getString("cedula");

                Persona persona = new Persona(id_persona, nombre, apellido, fechaDeNacim, direccion, sexo, telefono, cedula);
                personas.add(persona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personas;
    }
	
	public static ArrayList<Medico> fetchMedicos(){
		ArrayList<Medico> medicos = new ArrayList<>();
        String query = "SELECT m.id_medico, m.telefono_trabajo, m.id_especialidad, m.id_cuenta, " +
                       "p.id_persona, p.nombre, p.apellido, p.fecha_de_nacimiento, p.direccion, p.sexo, p.telefono_personal, p.cedula " +
                       "FROM medico m JOIN persona p ON m.id_persona = p.id_persona";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet personaresultSet = statement.executeQuery()) {

            while (personaresultSet.next()) {
            	Persona persona = new Persona(
                        personaresultSet.getInt("id_persona"),
                        personaresultSet.getString("nombre"),
                        personaresultSet.getString("apellido"),
                        personaresultSet.getDate("fecha_de_nacimiento"),
                        personaresultSet.getString("direccion"),
                        personaresultSet.getString("sexo"),
                        personaresultSet.getString("telefono_personal"),
                        personaresultSet.getString("cedula") // Fetch and set cedula
                    );
                Medico medico = new Medico(0, query, 0, 0, persona);
                medico.setId_medico(personaresultSet.getInt("id_medico"));
                medico.setTelefono_trabajo(personaresultSet.getString("telefono_trabajo"));
                medico.setId_especialidad(personaresultSet.getInt("id_especialidad"));
                medico.setId_cuenta(personaresultSet.getInt("id_cuenta"));
                medico.setPersona(persona);

                medicos.add(medico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicos;
	}
	
	public static ArrayList<Enfermedad> fetchEnfermedades() {
        ArrayList<Enfermedad> enfermedades = new ArrayList<>();
        String selectSql = "SELECT * FROM enfermedad";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int idEnfermedad = resultSet.getInt("id_enfermedad");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                boolean permanente = resultSet.getBoolean("permanente");

                Enfermedad enfermedad = new Enfermedad(String.valueOf(idEnfermedad), nombre, descripcion, permanente);
                enfermedades.add(enfermedad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enfermedades;
    }
	
	public static ArrayList<Vacuna> fetchVacunas() {
        ArrayList<Vacuna> vacunas = new ArrayList<>();
        String selectSql = "SELECT * FROM Vacuna";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // Extract data from the ResultSet
                String idVacuna = resultSet.getString("id_vacuna");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descrip");
                int antMiligramos = resultSet.getInt("antMiligramos");

                // Create a new Vacuna object and add it to the list
                Vacuna vacuna = new Vacuna(idVacuna, nombre, descripcion, antMiligramos);
                vacunas.add(vacuna);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vacunas;
    }
	
	 public static ArrayList<Secretario> fetchSecretarios() {
	        ArrayList<Secretario> secretarios = new ArrayList<>();
	        String selectSql = "SELECT * FROM Secretario";
	        String personaQuery = "SELECT * FROM Persona WHERE id_persona = ?";
	        String cuentaQuery = "SELECT * FROM Cuenta WHERE id_cuenta = ?";

	        try (Connection connection = DriverManager.getConnection(connectionUrl);
	             PreparedStatement secretarioStatement = connection.prepareStatement(selectSql);
	             PreparedStatement personaStatement = connection.prepareStatement(personaQuery);
	             PreparedStatement cuentaStatement = connection.prepareStatement(cuentaQuery);
	             ResultSet secretarioResultSet = secretarioStatement.executeQuery()) {

	            while (secretarioResultSet.next()) {
	                int idSecretario = secretarioResultSet.getInt("id_secretario");
	                String telefonoTrabajo = secretarioResultSet.getString("telefono_trabajo");
	                int idPersona = secretarioResultSet.getInt("id_persona");
	                int idCuenta = secretarioResultSet.getInt("id_cuenta");

	                // Fetch Persona data
	                Persona persona = null;
	                personaStatement.setInt(1, idPersona);
	                try (ResultSet personaResultSet = personaStatement.executeQuery()) {
	                    if (personaResultSet.next()) {
	                        persona = new Persona(
	                            personaResultSet.getInt("id_persona"),
	                            personaResultSet.getString("nombre"),
	                            personaResultSet.getString("apellido"),
	                            personaResultSet.getDate("fecha_de_nacimiento"),
	                            personaResultSet.getString("direccion"),
	                            personaResultSet.getString("sexo"),
	                            personaResultSet.getString("telefono_personal"),
	                            personaResultSet.getString("cedula")
	                        );
	                    }
	                }

	                // Fetch Cuenta data
	                Cuenta cuenta = null;
	                cuentaStatement.setInt(1, idCuenta);
	                try (ResultSet cuentaResultSet = cuentaStatement.executeQuery()) {
	                    if (cuentaResultSet.next()) {
	                        cuenta = new Cuenta(
	                            cuentaResultSet.getString("id_cuenta"),
	                            cuentaResultSet.getString("username"),
	                            cuentaResultSet.getString("password")
	                      
	                        );
	                    }
	                }

	               
	                Secretario secretario = new Secretario(idSecretario, telefonoTrabajo, cuenta, persona);
	                secretarios.add(secretario);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return secretarios;
	    }
	 
	 
	 public static ArrayList<Cita> getCitas() {
	        ArrayList<Cita> citas = new ArrayList<>();
	        String query = "SELECT c.id_cita, c.nombre, c.fecha_cita, c.asistencia, c.id_medico, c.id_tipo_cita, c.id_paciente, "
	                     + "m.telefono_trabajo, m.id_especialidad, m.id_cuenta, m.id_persona AS medico_id_persona, "
	                     + "p_sangre.sangre, p_sangre.contacto_emergencia, p_sangre.id_persona AS paciente_id_persona, "
	                     + "p.nombre AS paciente_nombre, p.apellido AS paciente_apellido, p.fecha_de_nacimiento AS paciente_fecha, "
	                     + "p.direccion AS paciente_direccion, p.sexo AS paciente_sexo, p.telefono AS paciente_telefono, p.cedula AS paciente_cedula "
	                     + "FROM Cita c "
	                     + "LEFT JOIN Medico m ON c.id_medico = m.id_medico "
	                     + "LEFT JOIN Paciente p_sangre ON c.id_paciente = p_sangre.id_paciente "
	                     + "LEFT JOIN Persona p ON p.id_persona = p_sangre.id_persona";

	        try (Connection connection = DriverManager.getConnection(connectionUrl);
	             PreparedStatement statement = connection.prepareStatement(query);
	             ResultSet resultSet = statement.executeQuery()) {

	            while (resultSet.next()) {
	                String idCita = resultSet.getString("id_cita");
	                String nombre = resultSet.getString("nombre");
	                Date fechaCita = resultSet.getDate("fecha_cita");
	                boolean asistencia = resultSet.getBoolean("asistencia");
	                

	                int idMedico = resultSet.getInt("id_medico");
	                String telefonoTrabajo = resultSet.getString("telefono_trabajo");
	                int idEspecialidad = resultSet.getInt("id_especialidad");
	                int idCuenta = resultSet.getInt("id_cuenta");
	                int medicoIdPersona = resultSet.getInt("medico_id_persona");
	                
	                Persona medicoPersona = new Persona(
	                    medicoIdPersona,
	                    resultSet.getString("paciente_nombre"),
	                    resultSet.getString("paciente_apellido"),
	                    resultSet.getDate("paciente_fecha"),
	                    resultSet.getString("paciente_direccion"),
	                    resultSet.getString("paciente_sexo"),
	                    resultSet.getString("paciente_telefono"),
	                    resultSet.getString("paciente_cedula")
	                );
	                
	                Medico medico = new Medico(idMedico, telefonoTrabajo, idEspecialidad, idCuenta, medicoPersona);
	                
	        
	                int idPaciente = resultSet.getInt("id_paciente");
	                String sangre = resultSet.getString("sangre");
	                String contactoEmergencia = resultSet.getString("contacto_emergencia");
	                Persona pacientePersona = new Persona(
	                    resultSet.getInt("paciente_id_persona"),
	                    resultSet.getString("paciente_nombre"),
	                    resultSet.getString("paciente_apellido"),
	                    resultSet.getDate("paciente_fecha"),
	                    resultSet.getString("paciente_direccion"),
	                    resultSet.getString("paciente_sexo"),
	                    resultSet.getString("paciente_telefono"),
	                    resultSet.getString("paciente_cedula")
	                );
	                
	                Paciente paciente = new Paciente(idPaciente, sangre, contactoEmergencia, pacientePersona);
	                
	       
	                Cita cita = new Cita(idCita, nombre, fechaCita, asistencia, medico, resultSet.getString("id_tipo_cita"), paciente);
	                citas.add(cita);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return citas;
	    }
	 
	 
	 public static Cuenta getCuentaByUsername(String username) {
	        Cuenta cuenta = null;
	        String query = "SELECT * FROM cuenta WHERE usuario = ?";

	        try (Connection connection = DriverManager.getConnection(connectionUrl);
	             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	            preparedStatement.setString(1, username);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                String idCuenta = String.valueOf(resultSet.getInt("id_cuenta"));
	                String usuario = resultSet.getString("usuario");
	                String contrasena = resultSet.getString("contrasena");

	                cuenta = new Cuenta(idCuenta, usuario, contrasena);
	                Clinica.getInstance().regAdmin(cuenta);
	            }

	            resultSet.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return cuenta;
	    }
	
	 
	   public static void loadCuentas() {
	        Connection conn = null;
	        Statement stmt = null;
	        ResultSet rs = null;

	        try {
	          
	            
	            conn = DriverManager.getConnection(connectionUrl);

	            String query = "SELECT id_cuenta, usuario, contrasena FROM cuenta";
	            stmt = conn.createStatement();
	            rs = stmt.executeQuery(query);

	            while (rs.next()) {
	                String id = rs.getString("id_cuenta");
	                String usuario = rs.getString("usuario");
	                String contrasena = rs.getString("contrasena");

	                Cuenta cuenta = new Cuenta(id, usuario, contrasena);
	                Clinica.getInstance().regAdmin(cuenta);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (stmt != null) stmt.close();
	                if (conn != null) conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	
	public static void insertEnfermedad(String nombre, String descripcion, boolean permanente) {
        String insertSql = "INSERT INTO enfermedad (nombre, descripcion, permanente) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            
          
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, descripcion);
            preparedStatement.setBoolean(3, permanente);

           
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	
	
	public static String getHighestUnoccupiedPersonaId() {
        String query = "SELECT MIN(t1.id_persona + 1) AS next_id "
                     + "FROM persona t1 "
                     + "LEFT JOIN persona t2 ON t1.id_persona + 1 = t2.id_persona "
                     + "WHERE t2.id_persona IS NULL";

        String highestUnoccupiedId = null;

        try (Connection conn = DriverManager.getConnection(connectionUrl);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                highestUnoccupiedId = rs.getString("next_id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return highestUnoccupiedId;
    }
	
	public static String getHighestUnoccupiedPacienteId() {
        String query = "SELECT MIN(t1.id_paciente + 1) AS next_id "
                     + "FROM paciente t1 "
                     + "LEFT JOIN paciente t2 ON t1.id_paciente + 1 = t2.id_paciente "
                     + "WHERE t2.id_paciente IS NULL";

        String highestUnoccupiedId = null;

        try (Connection conn = DriverManager.getConnection(connectionUrl);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                highestUnoccupiedId = rs.getString("next_id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return highestUnoccupiedId;
    }
	

	    // Function to insert a new consultation into the consulta table
	    public static void createConsulta(String descripcion, String fechaConsulta, String tratamiento, boolean asistencia,
	                                      int idEnfermedad, int idVacunaDosis, int idMedico, int idSecretario, int idPaciente) {

	        // Database connection details

	        // SQL Insert statement
	        String sql = "INSERT INTO consulta (descripcion, fecha_consulta, tratamiento, asistencia, id_enfermedad, id_vacuna_dosis, id_medico, id_secretario, id_paciente) " +
	                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        

	        try (Connection conn = DriverManager.getConnection(connectionUrl);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	            // Set parameters
	            pstmt.setString(1, descripcion);
	            pstmt.setTimestamp(2, java.sql.Timestamp.valueOf(fechaConsulta));
	            pstmt.setString(3, tratamiento);
	            pstmt.setBoolean(4, asistencia);
	            pstmt.setInt(5, idEnfermedad);
	            pstmt.setInt(6, idVacunaDosis);
	            pstmt.setInt(7, idMedico);
	            pstmt.setInt(8, idSecretario);
	            pstmt.setInt(9, idPaciente);

	            // Execute insert
	            int rowsAffected = pstmt.executeUpdate();
	            System.out.println("Rows inserted: " + rowsAffected);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    
	}

	
	
	
	


