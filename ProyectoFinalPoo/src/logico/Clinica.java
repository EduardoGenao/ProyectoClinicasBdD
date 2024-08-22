package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Clinica implements Serializable{
	
	
	/**
	 * 
	 */
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
	private static Cuenta loginAdministrador;
	public static int codigo = 1;
	public static int consultaCodigo = 1;
	public static int codigoEnf = 1;
	private static Clinica clinic = null;

	public static String personaCedula = "";
	public static String pacienteCedula = "";
	public static String secretarioCedula = "";
	public static String medicoCedula = "";
	public static String vacunaCodigo = "";
	public static String enfermedadCodigo = "";
	
	
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
	public void modificarPaciente(Paciente miPaciente) {
		int index = buscarIndexPacByCode(miPaciente.getPersona().getCedula());
		misPacientes.set(index, miPaciente);
	}
	
	public void modiMed(Medico misMedis) {
		int cont = buscarIndexMedByCode(misMedis.getPersona().getCedula());
		misMedicos.set(cont, misMedis);
	}

	public Paciente buscarPaciente(String idPaciente) {
		Paciente temp = null;
		boolean encontrado = false;
		int i=0;
		while (!encontrado && i<misPacientes.size()) {
			if(misPacientes.get(i).getPersona().getCedula().equalsIgnoreCase(idPaciente)){
				temp = misPacientes.get(i);
				encontrado = true;
			}
			i++;
		}
		
		return temp;
	}
	
	
	public Persona buscarPersona(String idPersona) {
		Persona temp = null;
		boolean encontrado = false;
		int i=0;
		while (!encontrado && i<misPacientes.size()) {
			if(misPacientes.get(i).getPersona().getCedula().equalsIgnoreCase(idPersona)){
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



	public void RegistrarCita(Cita aux) {
		misCitas.add(aux);		
		
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
	
	public boolean confirmLogin(String text, String text2) {
		boolean login = false;
		for (Cuenta user : misAdministradores) {
			if(user.getUsuario().equals(text) && user.getContrasena().equals(text2)){
				loginAdministrador = user;
				login = true;
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


	public static String getSecretarioCodigo() {
		return secretarioCedula;
	}


	


	
}


