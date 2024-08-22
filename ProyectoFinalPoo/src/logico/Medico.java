package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Medico implements Serializable{
	
	private static final long serialVersionUID = 7125453121549969334L;
	/**
	 * 
	 */
	/*
	private String especialidad;
	private String nombre;
	private String cedula;
	private String direccion;
	private int edad;
	private boolean sexo;
	private String telefono;
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Cita> misCitas;
	
	public Medico(String especialidad,String nombre, String cedula, 
			String direccion,int edad, boolean sexo, String telefono) {
		super();
		this.especialidad = especialidad;
		this.nombre = nombre;
		this.cedula = cedula;
		this.direccion = direccion;
		this.edad = edad;
		this.sexo = sexo;
		this.telefono = telefono;
		misPacientes = new ArrayList<>();
		misCitas = new ArrayList<>();
	}
	
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public ArrayList<Paciente> getMisPacientes() {
		return misPacientes;
	}
	public void setMisPacientes(ArrayList<Paciente> misPacientes) {
		this.misPacientes = misPacientes;
	}
	
	public ArrayList<Cita> getMisCitas() {
		return misCitas;
	}
	public void setMisCitas(ArrayList<Cita> misCitas) {
		this.misCitas = misCitas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getedad() {
		return dead;
	}
	public void setedad(int edad) {
		this.edad = edad;
	}
	public boolean getSexo() {
		return sexo;
	}
	public void setSexo(boolean sexo) {
		this.sexo = sexo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	*/
	private int id_medico;
	private String telefono_trabajo;
	private int id_especialidad;
	private int id_cuenta;
	private Persona persona;
	
	/*
	public Medico(int id_persona, String nombre, String apellido, Date fechaDeNacim, String direccion, String sexo,
			String telefono, String cedula, int id_medico, String telefono_trabajo, int id_especialidad, int id_cuenta,
			Persona persona) {
		super(id_persona, nombre, apellido, fechaDeNacim, direccion, sexo, telefono, cedula);
		this.id_medico = id_medico;
		this.telefono_trabajo = telefono_trabajo;
		this.id_especialidad = id_especialidad;
		this.id_cuenta = id_cuenta;
		this.setPersona(persona);
	}
*/
	
	public Medico(int id_medico, String telefono_trabajo, int id_especialidad, int id_cuenta, Persona persona) {
		super();
		this.id_medico = id_medico;
		this.telefono_trabajo = telefono_trabajo;
		this.id_especialidad = id_especialidad;
		this.id_cuenta = id_cuenta;
		this.persona = persona;
	}
	
	public int getId_medico() {
		return id_medico;
	}

	public void setId_medico(int id_medico) {
		this.id_medico = id_medico;
	}

	public String getTelefono_trabajo() {
		return telefono_trabajo;
	}

	public void setTelefono_trabajo(String telefono_trabajo) {
		this.telefono_trabajo = telefono_trabajo;
	}

	public int getId_especialidad() {
		return id_especialidad;
	}

	public void setId_especialidad(int id_especialidad) {
		this.id_especialidad = id_especialidad;
	}

	public int getId_cuenta() {
		return id_cuenta;
	}

	public void setId_cuenta(int id_cuenta) {
		this.id_cuenta = id_cuenta;
	}

	

	public Object getedad() {
		// TODO Auto-generated method stub
		return null;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	

	

	
}
